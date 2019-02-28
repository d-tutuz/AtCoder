package discopresentsディスカバリーチャンネルコードコンテスト2017予選;

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

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int H = in.nextInt(), W = in.nextInt();
			long A = in.nextLong(), B = in.nextLong();
			char[][] s = new char[H][W];
			for (int i = 0; i < H; i++) {
				s[i] = in.nextString().toCharArray();
			}

			int sc = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (s[i][j] == 'S') sc++;
				}
			}

			long h = 0, w = 0, hw = 0;

			long tmp = 0, hc = 0;
			for (int i = 0; i < W; i++) {
				for (int j = 0; j < H/2; j++) {
					if (s[j][i] == 'S' && s[H-1-j][i] == 'S') {
						tmp++;
						hc += 2;
					}
				}
			}
			if (sc == hc) tmp--;
			h = Math.max(h, tmp);

			tmp = 0;
			int wc = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W/2; j++) {
					if (s[i][j] == 'S' && s[i][W-1-j] == 'S') {
						tmp++;
						wc += 2;
					}
				}
			}
			if (sc == wc) tmp--;
			w = Math.max(w, tmp);

			tmp = 0;
			int hwc = 0;
			for (int i = 0; i < H/2; i++) {
				for (int j = 0; j < W/2; j++) {
					if (s[i][j] == 'S' && s[H-1-i][W-1-j] == 'S' && s[i][W-1-j] == 'S' && s[H-1-j][i] == 'S') {
						tmp++;
						hwc += 4;
					}
				}
			}
			if (sc == hwc) tmp--;
			hw = Math.max(hw, tmp);

			long ans = A + B;
			ans = Math.max(ans + h * A + hw * B, ans + w * B + hw * A);
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
