package abc099;

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
		long[][] memo;
		int N, C;
		int[][] d,c;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			N = in.nextInt(); C = in.nextInt();
			d = new int[C][C];
			c = new int[N][N];

			for (int i = 0; i < C; i++) {
				for (int j = 0; j < C; j++) {
					d[i][j] = in.nextInt();
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					c[i][j] = in.nextInt();
				}
			}

			memo = new long[3][C+1];
			for (int i = 0; i < 3; i++) {
				Arrays.fill(memo[i], -1);
			}

			long ans = Long.MAX_VALUE/2;
			for (int i = 1; i <= C; i++) {
				for (int j = 1; j <= C; j++) {
					for (int k = 1; k <= C; k++) {
						if (i == j || j == k || k == i) {
							continue;
						}
						ans = Math.min(ans, dfs(0, i) + dfs(1, j) + dfs(2, k));
					}
				}
			}

			out.println(ans);

		}

		long dfs(int mod, int to) {

			if (memo[mod][to] > 0) {
				return memo[mod][to];
			}

			int ret = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((i+1 + j+1) % 3 == mod) {
						ret += d[c[i][j]-1][to-1];
					}
				}
			}

			return memo[mod][to] = ret;
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

}
