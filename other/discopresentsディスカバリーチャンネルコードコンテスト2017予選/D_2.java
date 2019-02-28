package discopresentsディスカバリーチャンネルコードコンテスト2017予選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class D_2 {

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

			int h = in.nextInt(), w = in.nextInt();
			long a = in.nextLong(), b = in.nextLong();
			char[][] s = new char[h][w];
			for (int i = 0; i < h; i++) {
				s[i] = in.nextString().toCharArray();
			}
			int total = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (s[i][j] == 'S') total++;
				}
			}

			int cw = 0, ch = 0, chw = 0;
			for (int i = 0; i < h/2; i++) {
				for (int j = 0; j < w/2; j++) {
					int cnt = 0;
					if (s[i][j] == 'S' && s[i][w-1-j] == 'S') {
						cnt++; cw++;
					}

					if (s[i][w-1-j] == 'S' && s[h-1-i][w-1-j] == 'S') {
						cnt++; ch++;
					}

					if (s[h-1-i][j] == 'S' && s[h-1-i][w-1-j] == 'S') {
						cnt++; cw++;
					}

					if (s[i][j] == 'S' && s[h-1-i][j] == 'S') {
						cnt++; ch++;
					}

					if (cnt == 4) {
						chw++;
						ch -= 2;
						cw -= 2;
					}
				}
			}

			long ans1 = a + b, ans2 = a + b;

			// [南北]
			// 南北に対称になるように石を除くとき
			if (ch > 0 && ch * 2 + chw * 4 != total) ans1 += a;
			ans1 += Math.max(0, (ch-1)) * a;

			// 南北東西に対称になるように石を除くとき
			if (chw > 0 && chw * 4 != total) ans1 += a + b;
			ans1 += Math.max(0, (chw-1)) * (a + b);

			// 南北東西に対称に石を除いていく過程で南北 or 東西に対称になるので大きい値をつかう
			ans1 += chw * Math.max(a, b);

			// [東西]
			if (cw > 0 && cw * 2 + chw * 4 != total) ans2 += b;
			ans2 += Math.max(0, (cw-1)) * b;
			if (chw > 0 && chw * 4 != total) ans2 += a + b;
			ans2 += Math.max(0, (chw-1)) * (a + b);
			ans2 += chw * Math.max(a, b);

			out.println(Math.max(ans1, ans2));
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
