package abc025;

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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };
	static int[] mtw = { -1, 1 };
	static int[] mth = { -5, 5 };


	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int[][] a = new int[5][5];
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					a[i][j] = in.nextInt()-1;
				}
			}

			int[] dp = new int[1 << 25];
			dp[0] = 1;

			for (int state = 0; state < 1 << 25; state++) {

				if (dp[state] == 0) continue;

				// 今書きこもうとしている数字
				int next = Integer.bitCount(state);

				// 盤上の着目している位置
				for (int i = 0; i < 25; i++) {

					// すでに位置 i が埋まっている場合
					if ((state >> i & 1) == 1) continue;

					int y = i / 5;
					int x = i % 5;

					// 位置 i にかかれている数字が -1 または next でない場合は
					// 書き込む対象でないので skip
					if (a[y][x] != -1 && a[y][x] != next) continue;

					// 上下左右の位置が盤上にあることを確認する
					// 上下左右の位置がすでに書き込まれている状態かどうかチェックする
					if (isInside(y-1, x, 5, 5) && isInside(y+1, x, 5, 5)) {
						boolean exists1 = (state >> (5 * (y-1) + x) & 1) == 1;
						boolean exists2 = (state >> (5 * (y+1) + x) & 1) == 1;
						if (exists1 ^ exists2) continue;
					}
					if (isInside(y, x-1, 5, 5) && isInside(y, x+1, 5, 5)) {
						boolean exists1 = (state >> (5 * y + x-1) & 1) == 1;
						boolean exists2 = (state >> (5 * y + x+1) & 1) == 1;
						if (exists1 ^ exists2) continue;
					}

					// 書き込む
					int tmp = state | 1 << (5 * y + x);
					dp[tmp] = (dp[tmp] + dp[state]) % MOD;

				}
			}

			out.println(dp[(1 << 25) - 1]);

		}
	}

    public static boolean isInside(int y, int x, int h, int w) {
        return y >= 0 && x >= 0 && y < h && x < w;
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
