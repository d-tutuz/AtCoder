package abc027;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			char[] s = in.nextString().toCharArray();
			int n = s.length;
			int m = 0;
			for (char c : s) {
				if (c == 'M') m++;
			}

			// dp[i][j] := [i番目の命令][jの位置]
			int[][] dp = new int[n+1][m+1];
			for (int i = 0; i < n+1; i++) {
				Arrays.fill(dp[i], -INF);
			}

			dp[0][m/2] = 0;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= m; j++) {
					if (s[i] == '+') {
						dp[i+1][j] = dp[i][j] + (j-m/2);
					} else if (s[i] == '-') {
						dp[i+1][j] = dp[i][j] - (j-m/2);
					} else if (s[i] == 'M') {
						if (j-1 >= 0 && j+1 <= m) {
							dp[i+1][j] = max(dp[i+1][j], max(dp[i][j+1], dp[i][j-1]));
						} else if (j-1 >= 0) {
							dp[i+1][j] = max(dp[i+1][j], dp[i][j-1]);
						} else if (j+1 <= m) {
							dp[i+1][j] = max(dp[i+1][j], dp[i][j+1]);
						}
					}
					print(dp);
				}
			}

			out.println(dp[n][m/2]);
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

	static void print(int[][] dp) {
		int n = dp.length;
		int m = dp[0].length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(dp[i][j]+" ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

}
