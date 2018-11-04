package abc113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int h = in.nextInt(), w = in.nextInt(), k = in.nextInt();

			long[][] dp = new long[h+1][w+1];
			dp[0][0] = 1;

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					for (int l = -1; l <= 1; l++) {
						int mj = j + l;
						if (mj < 0 || mj >= w) continue;
						dp[i+1][mj] += dp[i][j];
						dp[i+1][mj] %= MOD;

						int count = 0;
						for (int bit = 0; bit < 1 << (w-1); bit++) {
							for (int m = 0; m < w-1; m++) {
								if (j == 0 && m == 0) continue;
								if (j == 1 && (m == 0 || m == 1)) continue;
								if (j == 2 && (m == 1 || m == 2)) continue;
								if (j == 3 && (m == 2 || m == 3)) continue;
								if (j == 4 && (m == 3 || m == 4)) continue;
								if (j == 5 && (m == 4 || m == 5)) continue;
								if (j == 6 && (m == 5 || m == 6)) continue;
								if (j == 7 && m == 6) continue;

								if ((bit >> m & 1) == 1 && (bit >> m-1 & 1) == 1) {
									continue;
								}
								if ((bit >> m & 1) == 1 && (bit >> m+1 & 1) == 1) {
									continue;
								}
								if ((bit >> m & 1) == 1) {
									count++;
								}
							}
						}
						dp[i+1][mj] *= Math.max(count, 1);
						dp[i+1][mj] %= MOD;
					}
				}
			}

			out.println(dp[h][k-1] % MOD);

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
