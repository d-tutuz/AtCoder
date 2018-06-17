package abc038;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.StringTokenizer;

public class D {

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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			List<P> list =new ArrayList<>();
			int n = in.nextInt();
			for (int i = 0; i < n; i++) {
				long w = in.nextLong(), h = in.nextLong();
				list.add(new P(h, w));
			}

			long ans1 = 1;
			list.sort((a, b) -> (int)(b.w - a.w));
			long nowh = list.get(0).h;
			long noww = list.get(0).w;

			for (int i = 1; i < n; i++) {

				long nexth = list.get(i).h;
				long nextw = list.get(i).w;

				if (nowh > nexth && noww > nextw) {
					ans1++;
					nowh = nexth;
					noww = nextw;
				}
			}

			long ans2 = 1;
			list.sort((a, b) -> (int)(b.h - a.h));
			nowh = list.get(0).h;
			noww = list.get(0).w;

			for (int i = 1; i < n; i++) {

				long nexth = list.get(i).h;
				long nextw = list.get(i).w;

				if (nowh > nexth && noww > nextw) {
					ans2++;
					nowh = nexth;
					noww = nextw;
				}
			}


			out.println(max(ans1, ans2));

		}
	}

	static class P {
		long h, w;

		public P(long h, long w) {
			super();
			this.h = h;
			this.w = w;
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
