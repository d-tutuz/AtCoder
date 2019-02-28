package 天下一プログラマーコンテスト2016予選B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class B {

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

			char[] s = in.nextString().toCharArray();
			int n = s.length;

			int[][][] dp = new int[n+1][n+1][n+1];
			for (int i = 0; i < n+1; i++) {
				for (int j = 0; j < n+1; j++) {
					for (int k = 0; k < n+1; k++) {
						dp[i][j][k] = INF;
					}
				}
			}

			// i文字目までみて、最後に書き換えたのがj文字目で、'(' - ')' がk個であるときの最小値
			dp[0][0][0] = 0;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n+1; j++) {
					for (int k = 0; k < n+1; k++) {
						if (dp[i][j][k] == INF) continue;
						if (s[i] == '(') {

							// 何も書き換えない
							dp[i+1][j][k+1] = min(dp[i+1][j][k+1], dp[i][j][k]);

							// i文字目を '(' -> ')' に書き換える
							if (k > 0) dp[i+1][i][k-1] = min(dp[i+1][i][k-1], dp[i][j][k]+1);
						} else {
							// i文字目を ')' -> '(' に書き換える
							dp[i+1][i][k+1] = min(dp[i+1][i][k+1], dp[i][j][k]+1);

							// s[i] = ')' で対応が取れるので dp[i][j][k] の結果で更新できる場合
							if (k > 0) dp[i+1][j][k-1] = min(dp[i+1][j][k-1], dp[i][j][k]);
						}
					}
				}
			}

			long ans = INF;
			for (int j = 0; j < n+1; j++) {
				ans = min(ans, dp[n][j][0] + j);
			}

			out.println(ans);
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

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

	static long max(long... n) {
		long ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.max(ret, n[i]);
		}
		return ret;
	}

	static int max(int... n) {
		int ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.max(ret, n[i]);
		}
		return ret;
	}

	static long min(long... n) {
		long ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.min(ret, n[i]);
		}
		return ret;
	}

	static int min(int... n) {
		int ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.min(ret, n[i]);
		}
		return ret;
	}

}
