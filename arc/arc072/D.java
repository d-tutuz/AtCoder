package arc072;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		int WIN = 1, LOSE = 0;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int H = 20, W = 20;
			int[][] mat = new int[H][W];
			for (int i = 0; i < mat.length; i++) {
				Arrays.fill(mat[i], -1);
			}

			mat[0][0] = LOSE;
			mat[0][1] = LOSE;
			mat[1][0] = LOSE;
			mat[1][1] = LOSE;
			mat[2][0] = WIN;
			mat[0][2] = WIN;

			for (int k = 3; k <= H+W; k++) {
				for (int h = 0; h <= k; h++) {
					int nh = h, nw = k-h;
					boolean win = false;

					for (int i = 1; i <= nh/2; i++) {
						int mh = nh - i*2;
						int mw = nw + i;
						if (mh < 0 || mh >= H || mw < 0 || mw >= W) continue;
						if (mat[mh][mw] == 0) {
							win = true;
							break;
						}
					}

					for (int i = 1; i <= nw/2; i++) {
						int mh = nh + i;
						int mw = nw - i*2;
						if (mh < 0 || mh >= H || mw < 0 || mw >= W) continue;
						if (mat[mh][mw] == 0) {
							win = true;
							break;
						}
					}

					if (nh < 0 || nh >= H || nw < 0 || nw >= W) continue;
					mat[nh][nw] = win ? WIN : LOSE;
				}
			}
			print(mat, out);
		}
	}

	static void print(int[][] a, PrintWriter out) {

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				if (j > 0) out.print(" ");
				out.print(a[i][j]);
			}
			out.print("\n");
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
