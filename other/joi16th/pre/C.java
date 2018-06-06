package joi16th.pre;

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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
			char[][] s = new char[n][m];
			for (int i = 0; i < n; i++) {
				s[i] = in.nextString().toCharArray();
			}

			int[][] a = new int[n][m];
			long ans1 = 0;
			for (int i = 0; i < n; i++) {
				int now = 0;
				for (int j = 0; j < m; j++) {
					if (s[i][j] == '.') {
						a[i][j] = ++now;
					} else if (s[i][j] == '#') {
						now = 0;
					}
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (j + k-1 >= m || a[i][j+k-1] == 0 || a[i][j] == 0) {
						continue;
					}
					if (a[i][j+k-1] == (a[i][j] + k-1)) {
						ans1++;
					}
				}
			}

			int[][] b = new int[n][m];
			long ans2 = 0;
			for (int j = 0; j < m; j++) {
				int now = 0;
				for (int i = 0; i < n; i++) {
					if (s[i][j] == '.') {
						b[i][j] = ++now;
					} else if (s[i][j] == '#') {
						now = 0;
					}
				}
			}

			for (int j = 0; j < m; j++) {
				for (int i = 0; i < n; i++) {
					if (i + k-1 >= n || b[i+k-1][j] == 0 || b[i][j] == 0) {
						continue;
					}
					if (b[i+k-1][j] == (b[i][j] + k-1)) {
						ans2++;
					}
				}
			}

			out.println(ans1+ans2);

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
