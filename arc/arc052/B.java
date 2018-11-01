package arc052;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
		inputStream = new FileInputStream(new File("/workspace/Atcoder/arc/arc052/input1.txt"));
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

			int n = in.nextInt(), q = in.nextInt();
			double[] x = new double[n], r = new double[n], h = new double[n];

			for (int i = 0; i < n; i++) {
				x[i] = in.nextDouble();
				r[i] = in.nextDouble();
				h[i] = in.nextDouble();
			}

			while (q-- > 0) {
				double sum = 0.0;
				long a = in.nextLong(), b = in.nextLong();
				for (int i = 0; i < n; i++) {
					double ah = x[i] + h[i] - a;
					double ar = ah * r[i] / h[i];
					double bh = x[i] + h[i] - b;
					double br = bh * r[i] / h[i];

					if (x[i] + h[i] <= a || b <= x[i]) {
						continue;
					}

					// 円錐の下部のみ
					if (a <= x[i] && b < x[i] + h[i]) {
						sum += r[i] * r[i] * h[i];
						sum -= br * br * bh;

					// 円錐全体が[a, b]で被覆されている
					} else if (a <= x[i] && x[i] + h[i] <= b) {
						sum += r[i] * r[i] * h[i];

					// 円錐が[a, b]で台形になっている
					} else if (x[i] < a && b < x[i] + h[i]) {
						sum += ar * ar * ah;
						sum -= br * br * bh;

					// 円錐の上部のみ
					} else if (x[i] < a && x[i] + h[i] <= b) {
						sum += ar * ar * ah;
					}

				}
				out.println(sum * PI / 3);
			}

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
