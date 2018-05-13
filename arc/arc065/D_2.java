package arc065;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.StringTokenizer;

public class D_2 {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int k = in.nextInt();
			int l = in.nextInt();
			UnionFind u1 = new UnionFind(n);
			UnionFind u2 = new UnionFind(n);
			for (int i = 0; i < k; i++) {
				int p = in.nextInt()-1;
				int q = in.nextInt()-1;
				u1.link(p, q);
			}
			for (int i = 0; i < l; i++) {
				int r = in.nextInt()-1;
				int s = in.nextInt()-1;
				u2.link(r, s);
			}
			Map<Long, Integer> roots = new HashMap<Long, Integer>();
			for (int i = 0; i < n; i++) {
				long code = (long)u1.root(i)<< 32 | u2.root(i);
				roots.merge(code, 1, Integer::sum);
			}
			for (int i = 0; i < n; i++) {
				if (i > 0)out.print(" ");
				long code = (long)u1.root(i) << 32 | u2.root(i);
				out.print(roots.get(code));
			}

		}

		public class UnionFind {
			int[] data;

			public UnionFind(int n) {
				data = new int[n];
				Arrays.fill(data, -1);
			}

			boolean link(int x, int y) {
				x = root(x);
				y = root(y);
				if (x != y) {
					if (data[y] < data[x]) {
						data[y] += data[x];
						data[x] = y;
					} else {
						data[x] += data[y];
						data[y] = x;
					}
				}
				return x != y;
			}

			int root(int x) {
				return data[x] < 0 ? x : (data[x] = root(data[x]));
			}
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
