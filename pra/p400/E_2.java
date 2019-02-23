package p400;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class E_2 {

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		MyInput in = new MyInput(inputStream);
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

		public void solve(int testNumber, MyInput in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt();

			// i 回目の操作で箱 j に赤いボールが r 個、白いボールが b 個入っている可能性があるかどうか
			boolean[][][][] dp = new boolean[m+1][n][n+1][n+1];
			for (int i = 0; i < n; i++) {
				dp[0][i][i == 0 ? 1 : 0][i != 0 ? 1 : 0] = true;
			}

			for (int i = 0; i < m; i++) {
				int x = in.nextInt()-1, y = in.nextInt()-1;

				// 赤いボールと白いボールがある場合
				boolean done = false;
				for (int r = 1; r <= n; r++) {
					for (int w = 1; w <= n; w++) {
						if (dp[i][x][r][w]) {

							// 赤いボールを渡す場合
							for (int r2 = 0; r2 <= n; r2++) {
								for (int w2 = 0; w2 <= n; w2++) {
									if (dp[i][y][r2][w2]) {
										dp[i+1][y][r2+1][w2] = true;
										dp[i][x][r][w] = false;
										dp[i+1][x][r-1][w] = true;
									}
								}
							}

							// 白いボールを渡す場合
							for (int r2 = 0; r2 <= n; r2++) {
								for (int w2 = 0; w2 <= n; w2++) {
									if (dp[i][y][r2][w2]) {
										dp[i+1][y][r2][w2+1] = true;
										dp[i][x][r][w] = false;
										dp[i+1][x][r][w-1] = true;
									}
								}
							}

							done = true;
						}
					}
				}

				// 赤いボールしかない場合
				if (!done) {
					for (int r = 1; r <= n; r++) {
						if (dp[i][x][r][0]) {

							// 赤いボールを渡す場合
							for (int r2 = 0; r2 <= n; r2++) {
								for (int w2 = 0; w2 <= n; w2++) {
									if (dp[i][y][r2][w2]) {
										dp[i+1][y][r2+1][w2] = true;
										dp[i][x][r][0] = false;
										dp[i+1][x][r-1][0] = true;
									}
								}
							}

							done = true;
						}
					}
				}

				// 白いボールしかない場合
				if (!done) {
					for (int w = 1; w <= n; w++) {
						if (dp[i][x][0][w]) {

							// 白いボールを渡す場合
							for (int r2 = 0; r2 <= n; r2++) {
								for (int w2 = 0; w2 <= n; w2++) {
									if (dp[i][y][r2][w2]) {
										dp[i+1][y][r2][w2+1] = true;
										dp[i][x][0][w] = false;
										dp[i+1][x][0][w-1] = true;
									}
								}
							}
						}
					}
				}

				if (!done) new RuntimeException();

				// 直前の状態のマージ(移動しなかった分)
				for (int j = 0; j < n; j++) {
					if (j == y) continue;
					for (int r = 0; r <= n; r++) {
						for (int w = 0; w <= n; w++) {
							dp[i+1][j][r][w] |= dp[i][j][r][w];
						}
					}
				}
			}

			// m回操作後の最終的な状態で赤いボールが入っている可能性がある箱の数を数える
			int ans = 0;
			for (int i = 0; i < n; i++) {
				boolean ok = false;
				top:
				for (int r = 1; r <= n; r++) {
					for (int w = 0; w <= n; w++) {
						if (dp[m][i][r][w]) {
							ok = true;
							break top;
						}
					}
				}
				if (ok) ans++;
			}
			out.println(ans);
		}
	}

	static class MyInput {
		private final BufferedReader in;
		private static int pos;
		private static int readLen;
		private static final char[] buffer = new char[1024 * 8];
		private static char[] str = new char[500 * 8 * 2];
		private static boolean[] isDigit = new boolean[256];
		private static boolean[] isSpace = new boolean[256];
		private static boolean[] isLineSep = new boolean[256];

		static {
			for (int i = 0; i < 10; i++) {
				isDigit['0' + i] = true;
			}
			isDigit['-'] = true;
			isSpace[' '] = isSpace['\r'] = isSpace['\n'] = isSpace['\t'] = true;
			isLineSep['\r'] = isLineSep['\n'] = true;
		}

		public MyInput(InputStream is) {
			in = new BufferedReader(new InputStreamReader(is));
		}

		public int read() {
			if (pos >= readLen) {
				pos = 0;
				try {
					readLen = in.read(buffer);
				} catch (IOException e) {
					throw new RuntimeException();
				}
				if (readLen <= 0) {
					throw new MyInput.EndOfFileRuntimeException();
				}
			}
			return buffer[pos++];
		}

		public int nextInt() {
			int len = 0;
			str[len++] = nextChar();
			len = reads(len, isSpace);
			int i = 0;
			int ret = 0;
			if (str[0] == '-') {
				i = 1;
			}
			for (; i < len; i++)
				ret = ret * 10 + str[i] - '0';
			if (str[0] == '-') {
				ret = -ret;
			}
			return ret;
		}

		public long nextLong() {
			int len = 0;
			str[len++] = nextChar();
			len = reads(len, isSpace);
			int i = 0;
			long ret = 0;
			if (str[0] == '-') {
				i = 1;
			}
			for (; i < len; i++)
				ret = ret * 10 + str[i] - '0';
			if (str[0] == '-') {
				ret = -ret;
			}
			return ret;
		}

		public char nextChar() {
			while (true) {
				final int c = read();
				if (!isSpace[c]) {
					return (char) c;
				}
			}
		}

		public String nextString() {
			return new String(nextChars());
		}

		public char[] nextChars() {
			int len = 0;
			str[len++] = nextChar();
			len = reads(len, isSpace);
			return Arrays.copyOf(str, len);
		}

		public char[][] next2DChars(int h, int w) {
			char[][] s = new char[h][w];
			for (int i = 0; i < h; i++) {
				s[i] = nextChars();
			}
			return s;
		}

		int reads(int len, boolean[] accept) {
			try {
				while (true) {
					final int c = read();
					if (accept[c]) {
						break;
					}
					if (str.length == len) {
						char[] rep = new char[str.length * 3 / 2];
						System.arraycopy(str, 0, rep, 0, str.length);
						str = rep;
					}
					str[len++] = (char) c;
				}
			} catch (MyInput.EndOfFileRuntimeException e) {
			}
			return len;
		}

		public int[] nextIntArray(final int n) {
			final int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt();
			}
			return res;
		}

		public int[] nextIntArray1Index(final int n) {
			final int[] res = new int[n + 1];
			for (int i = 1; i < n + 1; i++) {
				res[i] = nextInt();
			}
			return res;
		}

		public int[] nextIntArrayDec(final int n) {
			final int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt() - 1;
			}
			return res;
		}

		public long[] nextLongArray(final int n) {
			final long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong();
			}
			return res;
		}

		public long[] nextLongArray1Index(final int n) {
			final long[] res = new long[n + 1];
			for (int i = 1; i < n + 1; i++) {
				res[i] = nextLong();
			}
			return res;
		}

		public long[] nextLongArrayDec(final int n) {
			final long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong() - 1;
			}
			return res;
		}

		public double nextDouble() {
			return Double.parseDouble(nextString());
		}

		public double[] nextDoubleArray(int n) {
			double[] res = new double[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextDouble();
			}
			return res;
		}

		static class EndOfFileRuntimeException extends RuntimeException {
		}

	}

}
