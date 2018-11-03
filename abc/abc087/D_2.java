package abc087;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
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

	static int INF = 1 << 30;
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt();
			WeightedUnionFind wuf = new WeightedUnionFind(n);
			for (int i = 0; i < m; i++) {
				int l = in.nextInt()-1, r = in.nextInt()-1, d = in.nextInt();
				if (wuf.same(l, r)) {
					int dist = wuf.diff(l, r);
					if (dist != d) {
						out.println("No");
						return;
					}
				} else {
					wuf.union(r, l, d);
				}
			}
			out.println("Yes");
		}
	}

	public static class WeightedUnionFind {
		int[] par;
		int[] ws;

		public WeightedUnionFind(int n) {
			par = new int[n];
			ws = new int[n];
			for (int i = 0; i < n; i++) {
				par[i] = -1;
			}
		}

		public int find(int x) {
			if (par[x] < 0) {
				return x;
			} else {
				final int parent = find(par[x]);
				ws[x] += ws[par[x]];
				par[x] = parent;
				return parent;
			}
		}

		public int weight(int x) {
			find(x);
			return ws[x];
		}

		public boolean union(int x, int y, int w) {
			w += weight(x);
			w -= weight(y);
			x = find(x);
			y = find(y);

			if (x != y) {
				if (par[y] < par[x]) {
					int tmp = x;
					x = y;
					y = tmp;
					w = -w;
				}
				par[x] += par[y];
				par[y] = x;
				ws[y] = w;

				return true;
			} else {
				return false;
			}
		}

		public boolean same(int x, int y) {
			return find(x) == find(y);
		}

		public Integer diff(int x, int y) {
			if (!same(x, y)) {
				return null;
			} else {
				return this.weight(x) - this.weight(y);
			}
		}

		public int size(int x) {
			return -par[find(x)];
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

		public int[] nextIntArrayDec(int n) {
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt() - 1;
			}
			return res;
		}

		public int[] nextIntArray1Index(int n) {
			int[] res = new int[n + 1];
			for (int i = 0; i < n; i++) {
				res[i + 1] = nextInt();
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

		public long[] nextLongArrayDec(int n) {
			long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong() - 1;
			}
			return res;
		}

		public long[] nextLongArray1Index(int n) {
			long[] res = new long[n + 1];
			for (int i = 0; i < n; i++) {
				res[i + 1] = nextLong();
			}
			return res;
		}

		public double[] nextDoubleArray(int n) {
			double[] res = new double[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextDouble();
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
