package abc054;

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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		int n, Ma, Mb;
		int[] a, b, c;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			n = in.nextInt();
			Ma = in.nextInt();
			Mb = in.nextInt();

			a = new int[n]; b = new int[n]; c = new int[n];

			for (int i = 0; i < n; i++) {
				a[i] = in.nextInt();
				b[i] = in.nextInt();
				c[i] = in.nextInt();
			}
			int maxA = Arrays.stream(a).sum();
			int maxB = Arrays.stream(b).sum();

			int[][][] dp = new int[n+1][maxA+1][maxB+1];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= maxA; j++) {
					for (int k = 0; k <= maxB; k++) {
						dp[i][j][k] = INF;
					}
				}
			}

			dp[0][0][0] = 0;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j <= maxA; j++) {
					for (int k = 0; k <= maxB; k++) {
						if (j >= a[i] && k >= b[i]) {
							dp[i+1][j][k] = Math.min(dp[i][j-a[i]][k-b[i]]+c[i], dp[i][j][k]);
						} else {
							dp[i+1][j][k] = dp[i][j][k];
						}
					}
				}
			}

			int ans = INF;
			for (int sa = 1; sa <= maxA; sa++) {
				for (int sb = 1; sb <= maxB; sb++) {
					if (sa * Mb == sb * Ma) {
						ans = Math.min(ans, dp[n][sa][sb]);
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
