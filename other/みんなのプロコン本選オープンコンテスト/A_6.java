package みんなのプロコン本選オープンコンテスト;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class A_6 {

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

			String src = in.nextString();
			int n = src.length();

			String tar = "oyaho";

			int[][] dp = new int[n+1][5];
			for (int i = 0; i < n+1; i++) {
				Arrays.fill(dp[i], INF);
			}

			for (int i = 0; i < n+1; i++) {
				dp[i][0] = i;
			}

			for (int i = 0; i < 5; i++) {
				dp[0][i] = i;
			}

			for (int i = 1; i < n+1; i++) {
				for (int j = 1; j < 10; j++) {

					// 左
					dp[i][j%5] = min(dp[i][(j-1)%5]+1, dp[i][j%5]);

					// 上
					dp[i][j%5] = min(dp[i-1][j%5]+1, dp[i][j%5]);

					// 左上
					int c = tar.charAt(j%5) == src.charAt(i-1) ? 0 : 1;
					dp[i][j%5] = min(dp[i-1][(j-1)%5]+c, dp[i][j%5]);
				}
			}

			out.println(dp[n][0]);

		}
	}

	// other template
	static int min(int a, int b) {
		return Math.min(a, b);
	}

	static void fill(int[][] a, int value) {
		for (int i = 0; i < a.length; i++) {
			Arrays.fill(a[i], value);
		}
	}

	static void fill(long[][] a, int value) {
		for (int i = 0; i < a.length; i++) {
			Arrays.fill(a[i], value);
		}
	}

	static void fill(char[][] a, char c) {
		for (int i = 0; i < a.length; i++) {
			Arrays.fill(a[i], c);
		}
	}

	// faster input template
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

}
