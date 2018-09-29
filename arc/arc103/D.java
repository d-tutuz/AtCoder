package arc103;

import static java.lang.Math.*;

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

			int n = in.nextInt();
			long[] z = new long[n];
			long[] x = new long[n];
			long[] y = new long[n];

			long max = 0;
			for (int i = 0; i < n; i++) {
				x[i] = in.nextLong();
				y[i] = in.nextLong();
				z[i] = abs(x[i]) + abs(y[i]);
				max = Math.max(max, z[i]);
			}

			if (!check(z)) {
				out.println(-1);
				return;
			}

			out.println(max);
			for (int i = 0; i < max; i++) {
				if (i > 0) {
					out.print(" ");
				}
				out.print(1);
			}
			out.print("\n");

			for (int i = 0; i < n; i++) {
				StringBuilder sb = new StringBuilder();
				long count = max;
				for (int j = 0; j < abs(x[i]); j++) {
					if (x[i] < 0) {
						sb.append("L");
					} else {
						sb.append("R");
					}
					count--;
				}
				for (int j = 0; j < abs(y[i]); j++) {
					if (y[i] < 0) {
						sb.append("D");
					} else {
						sb.append("U");
					}
					count--;
				}
				while (count > 0) {
					if (count % 2 == 0) {
						sb.append("U");
					} else {
						sb.append("D");
					}
					count--;
				}

				out.println(sb.toString());
			}

		}
	}

	static boolean check(long[] z) {

		int n = z.length;
		boolean even = true, odd = true;
		for (int j = 0; j < n; j++) {
			// all add
			if (z[j] % 2 == 1) {
			} else {
				odd = false;
			}

			// all even
			if (z[j] % 2 == 0) {
			} else {
				even = false;
			}
		}

		return (even | odd);

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
