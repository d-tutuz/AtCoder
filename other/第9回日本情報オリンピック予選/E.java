package 第9回日本情報オリンピック予選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class E {

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
	static int MOD = 100000;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		int h, w;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			h = in.nextInt(); w = in.nextInt();

			// (i, j) にいて [前回どの方向からきたか] [前々回どの方向からきたか] の場合の数
			// 0:右、1:上、2:不定
			int[][][][] dp = new int[h][w][3][3];

			dp[1][0][1][2] = 1;
			dp[0][1][0][2] = 1;

			for (int x = 1; x < h+w; x++) {
				for (int i = 0; i < h; i++) {
					int j = x - i;
					if (!isInside(i, j)) continue;

					if (isInside(i, j-1)) {
						dp[i][j][0][0] += dp[i][j-1][0][0] + dp[i][j-1][0][1] + dp[i][j-1][0][2];
						dp[i][j][0][1] += dp[i][j-1][1][1] + dp[i][j-1][1][2];
					}

					if (isInside(i-1, j)) {
						dp[i][j][1][0] += dp[i-1][j][0][0] + dp[i-1][j][0][2];
						dp[i][j][1][1] += dp[i-1][j][1][1] + dp[i-1][j][1][0] + dp[i-1][j][1][2];
					}

					for (int k = 0; k < 2; k++) {
						for (int l = 0; l < 2; l++) {
							dp[i][j][k][l] %= MOD;
						}
					}

				}
			}

			long ans = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					ans += dp[h-1][w-1][i][j];
					ans %= MOD;
				}
			}

			out.println(ans);
		}

		boolean isInside(int i, int j) {
			return 0 <= i && i < h && 0 <= j && j < w;
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
