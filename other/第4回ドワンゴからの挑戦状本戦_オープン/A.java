package 第4回ドワンゴからの挑戦状本戦_オープン;

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

		// 角度
		int pi = 120 * 360;

		// 基準を秒換算して保持
		int day = 12 * 60 * 60;
		int hour = 60 * 60;
		int minute = 60;

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int h = in.nextInt(), m = in.nextInt(), s = in.nextInt();
			int c1 = in.nextInt(), c2 = in.nextInt();

			int dt = 0;

			// 時刻を秒に変換
			int t = h * 60 * 60 + m * 60 + s;
			int max = -1, min = INF;

			while (true) {

				// 秒から時分秒の角度を算出
				int rh = t * pi / day;
				int rm = (t % hour) * pi / hour;
				int rs = (t % minute) * pi / minute;

				t++;
				dt++;

				// 1秒後の時分秒の角度を算出
				int rrh = t * pi / day;
				int rrm = (t % hour) * pi / hour;
				int rrs = (t % minute) * pi / minute;

				// 秒分、分時の針が1秒間で交差した場合
				if (rs <= rm && rrm < rrs) c1--;
				if (rm <= rh && rrh < rrm) c2--;

				// 終了条件
				if (c1 == 0 && c2 == 0 && rrs % pi != rrm % pi && rrm % pi != rrh % pi) {
					max = Math.max(max, dt);
					min = Math.min(min, dt);
				}
				if (c1 < 0 || c2 < 0) {
					break;
				}

				t %= day;
			}

			if (max < 0) {
				out.println(-1);
				return;
			}

			out.printf("%d %d\n", min, max);

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
