package abc060;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class D_3 {

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

	@SuppressWarnings("unchecked")
	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			long W = in.nextLong();
			int[] tw = new int[n];
			long[] tv = new long[n];
			for (int i = 0; i < n; i++) {
				tw[i] = in.nextInt();
				tv[i] = in.nextLong();
			}

			int a = 1, b = 1, c = 1, d = 1;
			long[][] v = new long[4][n+1];
			for (int i = 0; i < n; i++) {
				int diff = tw[i] - tw[0];
				if (diff == 0) {
					v[diff][a++] = tv[i];
				} else if (diff == 1) {
					v[diff][b++] = tv[i];
				} else if (diff == 2) {
					v[diff][c++] = tv[i];
				} else {
					v[diff][d++] = tv[i];
				}
			}
			for (int i = 0; i < 4; i++) {
				revSort(v[i]);
				Arrays.parallelPrefix(v[i], Math::addExact);
			}

			long ans = 0;
			for (int i = 0; i <= n; i++) {
				for (int j = 0; j <= n-i; j++) {
					for (int k = 0; k <= n-i-j; k++) {
						for (int m = 0; m <= n-i-j-k; m++) {
							if (i+j+k+m > n) continue;
							long tmp = 0;
							long base = tw[0];
							if (i*base + j*(base+1) + k*(base+2) + m*(base+3) > W) continue;
							tmp += v[0][i] - v[0][0];
							tmp += v[1][j] - v[1][0];
							tmp += v[2][k] - v[2][0];
							tmp += v[3][m] - v[3][0];
							ans = Math.max(ans, tmp);
						}
					}
				}
			}

			out.println(ans);
		}
	}

	static void revSort(long[] a) {
		long[] tmp = a.clone();
		int n = a.length;
		Arrays.sort(tmp, 1, n);
		for (int i = 1; i < n; i++) {
			a[i] = tmp[n-i];
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
