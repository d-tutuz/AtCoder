package abc047;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class B {

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

			int w = in.nextInt(), h = in.nextInt(), n = in.nextInt();
			boolean[][] s = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				Arrays.fill(s[i], true);
			}

			for (int m = 0; m < n; m++) {
				int x = in.nextInt()-1;
				int y = in.nextInt()-1;
				int a = in.nextInt();

				if (a == 1) {
					for (int i = 0; i < h; i++) {
						for (int j = 0; j <= x; j++) {
							s[i][j] = false;
						}
					}
					continue;
				}
				if (a == 2) {
					for (int i = 0; i < h; i++) {
						for (int j = x+1; j < w; j++) {
							s[i][j] = false;
						}
					}
					continue;
				}
				if (a == 3) {
					for (int i = 0; i <= y; i++) {
						for (int j = 0; j < w; j++) {
							s[i][j] = false;
						}
					}
					continue;
				}
				if (a == 4) {
					for (int i = y+1; i < h; i++) {
						for (int j = 0; j < w; j++) {
							s[i][j] = false;
						}
					}
					continue;
				}
			}

			out.println(calc(s));

		}

		int calc(boolean[][] s) {
			int sum = 0;
			for (int i = 0; i < s.length; i++) {
				for (int j = 0; j < s[0].length; j++) {
					if (s[i][j]) sum++;
				}
			}
			return sum;
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
