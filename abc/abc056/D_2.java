package abc056;

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

			int N = in.nextInt(); int K =in.nextInt();
			int[] a = new int[N];
			for (int i = 0; i < N; i++) {
				a[i] = in.nextInt();
			}
			Arrays.sort(a);

			int l = -1, r = N;
			int m = 0;
			while (r - l > 1) {
				m = (r+l)/2;
				boolean[][] dp = new boolean[N+1][K+1];

				dp[0][0] = true;

				for (int j = 0; j < N; j++) {
					for (int k = 0; k <= K; k++) {

						// jがi番目と同じ場合は選ばない
						if (m == j) {
							dp[j+1][k] |= dp[j][k];
						} else {
							// DPの遷移条件
							if (k-a[j] >= 0) {
								dp[j+1][k] |= dp[j][k-a[j]];
							} else {
								dp[j+1][k] |= dp[j][k];
							}
						}
					}
				}

				// 必要かどうか
				boolean ok = false;
				for (int j = Math.max(0, K-a[m]); j < K; j++) {
					ok |= dp[N][j];
				}
				if (ok) {
					r = m;
				} else {
					l = m;
				}
			}

			out.println(r);
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
