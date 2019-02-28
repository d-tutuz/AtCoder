package 第11回日本情報オリンピック予選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class D {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static int MOD = 10000;

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int N = in.nextInt(), K = in.nextInt();
			int[] def = new int[N+1];
			Arrays.fill(def, -1);
			for (int i = 0; i < K; i++) {
				int a = in.nextInt()-1, b = in.nextInt()-1;
				def[a] = b;
			}

			long[][][] dp = new long[N+1][3][3];
			if (def[0] == -1) {
				for (int j = 0; j < 3; j++) {
					dp[1][j][1] = 1;
				}
			} else {
				dp[1][def[0]][1] = 1;
			}

			for (int i = 1; i < N; i++) {
				if (def[i] == -1) {
					for (int to = 0; to < 3; to++) {
						for (int from = 0; from < 3; from++) {
							if (to == from) continue;
							dp[i+1][to][1] += dp[i][from][1] + dp[i][from][2];
							dp[i+1][to][1] %= MOD;
						}
						dp[i+1][to][2] += dp[i][to][1];
						dp[i+1][to][2] %= MOD;
					}
				} else {
					for (int from = 0; from < 3; from++) {
						if (def[i] == from) continue;
						dp[i+1][def[i]][1] += dp[i][from][1] + dp[i][from][2];
						dp[i+1][def[i]][1] %= MOD;
					}
					dp[i+1][def[i]][2] += dp[i][def[i]][1];
					dp[i+1][def[i]][2] %= MOD;
				}
			}

			long ans = 0;
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					ans += dp[N][j][k];
					ans %= MOD;
				}
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
