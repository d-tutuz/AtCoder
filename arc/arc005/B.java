package arc005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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

			int w = in.nextInt()-1, h = in.nextInt()-1;
			String s = in.nextString();
			char[][] mat = new char[9][9];
			for (int i = 0; i < 9; i++) {
				mat[i] = in.nextString().toCharArray();
			}

			StringBuilder sb = new StringBuilder();
			int k = 4;
			while (k-- > 0) {
				sb.append(mat[h][w]);

				if ("R".equals(s)) {
					if (isInside(h, w+1)) {
						w++;
					} else {
						s = "L";
						w--;
					}
				} else if ("L".equals(s)) {
					if (isInside(h, w-1)) {
						w--;
					} else {
						s = "R";
						w++;
					}
				} else if ("U".equals(s)) {
					if (isInside(h-1, w)) {
						h--;
					} else {
						s = "D";
						h++;
					}
				} else if ("D".equals(s)) {
					if (isInside(h+1, w)) {
						h++;
					} else {
						h--;
						s = "U";
					}
				} else if ("RU".equals(s)) {
					if (isInside(h-1, w+1)) {
						w++;
						h--;
					} else {
						if (isConer(h, w)) {
							w--;
							h++;
							s = "LD";
						} else {
							s = "LU";
							h--;
							w--;
						}
					}
				} else if ("RD".equals(s)) {
					if (isInside(h+1, w+1)) {
						w++;
						h++;
					} else {
						if (isConer(h, w)) {
							h--;
							w--;
							s = "LU";
						} else {
							s = "LD";
							w--;
							h++;
						}
					}
				} else if ("LU".equals(s)) {
					if (isInside(h-1, w-1)) {
						w--;
						h--;
					} else {
						if (isConer(h, w)) {
							w++;
							h++;
							s = "RD";
						} else {
							s = "RU";
							h--;
							w++;
						}
					}
				} else if ("LD".equals(s)) {
					if (isInside(h + 1, w - 1)) {
						w--;
						h++;
					} else {
						if (isConer(h, w)) {
							w++;
							h--;
							s = "RU";
						} else {
							s = "RD";
						}

					}
				}
			}

			out.println(sb.toString());
		}

		boolean isInside(int h, int w) {
			return 0 <= h && h < 9 && 0 <= w && w < 9;
		}

		boolean isConer(int h, int w) {
			return ((h % 8) == 0 && (w % 8) == 0);
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
