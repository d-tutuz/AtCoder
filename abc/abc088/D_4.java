package abc088;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Queue;
import java.util.StringTokenizer;

public class D_4 {

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

			int h = in.nextInt(), w = in.nextInt();
			char[][] s = new char[h][w];
			for (int i = 0; i < h; i++) {
				s[i] = in.nextString().toCharArray();
			}
			long count = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (s[i][j] == '#') count++;
				}
			}

			long ans = 0;

			Queue<P> q = new ArrayDeque<>();
			q.add(new P(0, 0));

			long[][] cost = new long[h][w];
			fill(cost, INF);
			cost[0][0] = 1;

			while (!q.isEmpty()) {
				P p = q.remove();

				int nh = p.h;
				int nw = p.w;

				for (int i = 0; i < 4; i++) {
					int mh = nh + mh4[i];
					int mw = nw + mw4[i];
					if (mh < 0 || mw < 0 || mh >= h || mw >= w || s[mh][mw] == '#') {
						continue;
					}

					if (cost[nh][nw] + 1 < cost[mh][mw]) {
						cost[mh][mw] = cost[nh][nw] + 1;
						q.add(new P(mh, mw));
					}
				}
			}

			out.println(cost[h-1][w-1] == INF ? -1 : h * w - count - cost[h-1][w-1]);
		}
	}

	static void fill(long[][] cost, long v) {
		for (int i = 0; i < cost.length; i++) {
			Arrays.fill(cost[i], v);
		}
	}

	static class P {
		int h, w;

		public P(int h, int w) {
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
