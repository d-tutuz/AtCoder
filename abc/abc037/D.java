package abc037;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Queue;
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

			int h = in.nextInt(), w = in.nextInt();
			int[][] a = new int[h][w];
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					a[i][j] = in.nextInt();
				}
			}

			int[][] dp = new int[h][w];
			for (int i = 0; i < h; i++) {
				Arrays.fill(dp[i], 1);
			}

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					Queue<P> q = new ArrayDeque<>();
					q.add(new P(i, j));

					while (!q.isEmpty()) {

						P p = q.remove();
						int k = p.i;
						int l = p.j;

						for (int m = 0; m < 4; m++) {
							if (k+mh4[m] < 0 || k+mh4[m] >= h || l+mw4[m] < 0 || l+mw4[m] >= w) {
								continue;
							}
							if (a[k][l] < a[k+mh4[m]][l+mw4[m]]) {
								dp[k+mh4[m]][l+mw4[m]] += dp[k][l];
							}
						}

					}
				}
			}

		}
	}
	static class P {
		int i, j;

		public P(int i, int j) {
			super();
			this.i = i;
			this.j = j;
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
