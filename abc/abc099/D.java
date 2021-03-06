package abc099;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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

	static int INF = 1 << 30;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int N = in.nextInt(), C = in.nextInt();
			int[][] d = new int[C][C];
			int[][] c = new int[C][C];

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

			long c1 = Long.MAX_VALUE/2;
			long c2 = Long.MAX_VALUE/2;
			long c3 = Long.MAX_VALUE/2;

			for (int k = 1; k <= C; k++) {
				long tmp1 = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if ((i+j)%3==0) {
							tmp1 += d[c[i][j]-1][k-1];
						}
					}
				}
				c1 = Math.min(c1, tmp1);
			}

			for (int k = 1; k <= C; k++) {
				long tmp2 = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if ((i+j)%3==1) {
							tmp2 += d[c[i][j]-1][k-1];
						}
					}
				}
				c2 = Math.min(c2, tmp2);
			}

			for (int k = 1; k <= C; k++) {
				long tmp3 = 0;
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if ((i+j)%3==2) {
							tmp3 += d[c[i][j]-1][k-1];
						}
					}
				}
				c3 = Math.min(c3, tmp3);
			}


			out.println(c1+c2+c3);

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
