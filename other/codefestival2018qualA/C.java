package codefestival2018qualA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class C {

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

			int N = in.nextInt();
			int x = in.nextInt();
			int tsum = 0;
			int[] t = new int[N];
			long[] a = new long[N];
			for (int i = 0; i < N; i++) {
				a[i] = in.nextLong();
				t[i] = calc(a[i]);
				tsum += t[i];
			}

			long[][] dp1 = new long[N+1][tsum+1];
			long[][] dp2 = new long[N+1][tsum+1];

			dp1[0][0] = 1;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j <= tsum; j++) {
					for (int k = 0; k <= t[i]; k++) {
						if (j + k > tsum) continue;
						if (k == t[i]) {
							dp2[i+1][j+k] += dp1[i][j] + dp2[i][j];
						} else {
							dp1[i+1][j+k] += dp1[i][j];
							dp2[i+1][j+k] += dp2[i][j];
						}
						dp1[i+1][j+k] %= MOD;
						dp2[i+1][j+k] %= MOD;
					}
				}
			}

			int K = Math.min(x, tsum);
			long ans = dp1[N][K];
			for (int i = 0; i <= K; i++) {
				ans += dp2[N][i];
			}
			out.println(ans % MOD);
		}

	}

	static int calc(long l) {
		int count = 0;
		while (l > 0) {
			count++;
			l /= 2;
		}
		return count;
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
