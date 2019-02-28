package 第10回日本情報オリンピック本選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class A {

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

			int m = in.nextInt(), n = in.nextInt();
			int k = in.nextInt();
			char[][] s = new char[m][n];
			for (int i = 0; i < m; i++) {
				s[i] = in.nextString().toCharArray();
			}
			int[][] J = new int[m+1][n+1], O = new int[m+1][n+1], I = new int[m+1][n+1];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (s[i][j] == 'J') {
						J[i+1][j+1]++;
					} else if (s[i][j] == 'O') {
						O[i+1][j+1]++;
					} else if (s[i][j] == 'I') {
						I[i+1][j+1]++;
					}
				}
			}

			for (int i = 1; i <= m; i++) {
				for (int j = 1; j <= n; j++) {
					J[i][j] += J[i-1][j] + J[i][j-1] - J[i-1][j-1];
					O[i][j] += O[i-1][j] + O[i][j-1] - O[i-1][j-1];
					I[i][j] += I[i-1][j] + I[i][j-1] - I[i-1][j-1];
				}
			}

			while (k-- > 0) {
				int a = in.nextInt(), b = in.nextInt(), c = in.nextInt(), d = in.nextInt();
				int JC = f(a, b, c, d, J);
				int OC = f(a, b, c, d, O);
				int IC = f(a, b, c, d, I);
				out.printf("%d %d %d\n", JC, OC, IC);
			}
		}

		int f(int a, int b, int c, int d, int[][] sum) {
			return sum[c][d] - sum[a-1][d] - sum[c][b-1] + sum[a-1][b-1];
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
