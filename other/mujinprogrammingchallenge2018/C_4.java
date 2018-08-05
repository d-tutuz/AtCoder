package mujinprogrammingchallenge2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class C_4 {

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

			int n = in.nextInt(), m = in.nextInt();
			char[][] s = new char[n][m];
			for (int i = 0; i < n; i++) {
				s[i] = in.nextString().toCharArray();
			}

			int[][] u = new int[n][m];
			int[][] d = new int[n][m];
			int[][] l = new int[n][m];
			int[][] r = new int[n][m];

			// 上
			for (int i = 1; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (s[i-1][j] != '#' && s[i][j] == '.') {
						u[i][j] = u[i-1][j] + 1;
					}
				}
			}

			// 下
			for (int i = n-2; i >= 0; i--) {
				for (int j = 0; j < m; j++) {
					if (s[i+1][j] != '#' && s[i][j] == '.') {
						d[i][j] = d[i+1][j] + 1;
					}
				}
			}

			// 左
			for (int i = 0; i < n; i++) {
				for (int j = 1; j < m; j++) {
					if (s[i][j-1] != '#' && s[i][j] == '.') {
						l[i][j] = l[i][j-1] + 1;
					}
				}
			}

			// 右
			for (int i = 0; i < n; i++) {
				for (int j = m-2; j >= 0; j--) {
					if (s[i][j+1] != '#' && s[i][j] == '.') {
						r[i][j] = r[i][j+1] + 1;
					}
				}
			}

			long ans = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (s[i][j] == '.') {
						ans += (u[i][j] + d[i][j]) * (l[i][j] + r[i][j]);
					}
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
