package abc054;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
	static int modP = 1000000007;

	static int maxAB = 100;
	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int N = in.nextInt(), Ma = in.nextInt(), Mb = in.nextInt();

			int[] a = new int[N], b = new int[N], c = new int[N];
			for (int i = 0; i < N; i++) {
				a[i] = in.nextInt();
				b[i] = in.nextInt();
				c[i] = in.nextInt();
			}

			int[][][] dp = new int[N+1][maxAB+1][maxAB+1];
			for (int i = 0; i <= N; i++) {
				for (int j = 0; j <= maxAB; j++) {
					for (int k = 0; k <= maxAB; k++) {
						dp[i][j][k] = INF;
					}
				}
			}
			dp[0][0][0] = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= maxAB; j++) {
					for (int k = 0; k <= maxAB; k++) {
						if (j-a[i]>=0 && k-b[i]>=0) {
							dp[i+1][j][k] = min(dp[i][j-a[i]][k-b[i]] + c[i], dp[i][j][k]);
						} else {
							dp[i+1][j][k] = dp[i][j][k];
						}
					}
				}
			}

			int ans = INF;
			for (int j = 1; j <= maxAB; j++) {
				for (int k = 1; k <= maxAB; k++) {
					if (j*Mb==k*Ma) {
						ans = min(dp[N][j][k], ans);
					}
				}
			}

			out.println(ans == INF ? -1 : ans);



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
