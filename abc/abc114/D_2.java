package abc114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

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

			int n = in.nextInt();
			Map<Integer, Integer> map = new TreeMap<>();
			for (int i = 1; i <= n; i++) {
				int tmp = i;
				for (int j = 2; j <= n; j++) {
					while (tmp % j == 0) {
						map.merge(j, 1, Integer::sum);
						tmp /= j;
					}
				}
			}

			int c2_4 = 0, c2_24 = 0, c2_14 = 0;
			int c4 = 0, c6 = 0, c14 = 0, c16 = 0, c24 = 0, c26 = 0;
			for (int i : map.keySet()) {
				int v = map.get(i);
				if (2 <= v && v < 4) {
					c2_4++;
				}
				if (2 <= v && v < 14) {
					c2_14++;
				}
				if (2 <= v && v < 24) {
					c2_24++;
				}

				if (4 <= v) {
					c4++;
				}
				if (6 <= v) {
					c6++;
				}
				if (14 <= v) {
					c14++;
				}
				if (16 <= v) {
					c16++;
				}
				if (24 <= v) {
					c24++;
				}
				if (26 <= v) {
					c26++;
				}
			}

			long ans = 0;
			ans += f(c4) * (c2_4 + c6);
			ans += f(c14) * (c2_14 + c16);
			ans += c24 * (c2_24 + c26) / 2;

			out.println(ans);

			out.println("---------------");

			for (int i : map.keySet()) {
				out.printf("%d %d\n", i, map.get(i));
			}

		}

		long f(int c) {
			return c * (c-1) / 2;
		}

	}

	static void printArrayLine(int[] a, PrintWriter out) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				out.print(a[i]);
			} else {
				out.print(" " + a[i]);
			}
		}
		out.print("\n");
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
