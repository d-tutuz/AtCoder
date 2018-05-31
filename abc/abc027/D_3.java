package abc027;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class D_3 {

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

	static char[] s;
	static int n, m;
	static int[][] memo;
	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			s = in.nextString().toCharArray();
			n = s.length;
			m = 0;
			for (char c : s) {
				if (c == 'M') m++;
			}

			memo = new int[n][2*n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(memo[i], -INF);
			}

			int ans = dfs(0, 0);
			out.println(ans);

		}

		// i回目 場所j
		int dfs(int i, int j) {

			int ret = 0;
			if (i == n) {
				if (j == 0) {
					return 0;
				} else {
					return -INF;
				}
			}
			if (memo[i][j+n] != -INF) {
				return memo[i][j+n];
			}

			if (s[i] == '+') {
				ret = dfs(i+1, j) + j;
			} else if (s[i] == '-') {
				ret = dfs(i+1, j) - j;
			} else if (s[i] == 'M') {
				ret = Math.max(dfs(i+1, j+1), dfs(i+1, j-1));
			}

			return memo[i][j+n] = ret;
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
				System.out.print(dp[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}

}
