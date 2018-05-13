package kuramoti1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class G_2 {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static int INF = 1 << 30;
	static int modP = 1000000007;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			List<P> ps = new ArrayList<>();
			long[] plist = new long[n+1];
			for (int i = 0; i < n; i++) {
				long keyCount = in.nextLong();
				ps.add(new P(i+1, keyCount));
				plist[i+1] = keyCount;
			}

			Map<Long, Integer> map = new HashMap<>();
			for (P p : ps) {
				map.merge(p.keycount, 1, Integer::sum);
			}

			Collections.sort(ps, (x, y) -> (int)y.keycount-(int)x.keycount);

			long max = Long.MAX_VALUE;
			List<Integer> list = new ArrayList<>();
			for (P p : ps) {
				if (p.keycount < max) {
					max = p.keycount;
					list.add(p.key);
				}
			}

			long[] ans = new long[n+1];
			long count = map.get(plist[list.get(0)]);
			for (int i = 0; i < list.size()-1 ; i++) {
				long sa = plist[list.get(i)] - plist[list.get(i+1)];
				ans[list.get(i)] = sa*count;
				count += map.get(plist[list.get(i+1)]);
			}
			ans[list.get(list.size()-1)] = count;

			for (int i = 1; i < n+1; i++) {
				System.out.println(ans[i]);
			}


		}
	}
	static class P {
		int key; long keycount;

		public P(int key, long keycount) {
			this.key = key;
			this.keycount = keycount;
		}
	}

	static class InputReader {
		BufferedReader in;
		StringTokenizer tok;

		public String nextString() {
			while (!tok.hasMoreTokens()) {
				try {
					tok = new StringTokenizer(in.readLine(), " ");
				} catch (IOException e) {
					throw new InputMismatchException();
				}
			}
			return tok.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(nextString());
		}

		public long nextLong() {
			return Long.parseLong(nextString());
		}

		public double nextDouble() {
			return Double.parseDouble(nextString());
		}

		public int[] nextIntArray(int n) {
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt();
			}
			return res;
		}

		public long[] nextLongArray(int n) {
			long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong();
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}

	}

}
