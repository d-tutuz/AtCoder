package abc037;

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

	static class TaskX {

		int[][] a;
		int h ,w;
		long[][] cnt;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			h = in.nextInt(); w = in.nextInt();
			a = new int[h][w];
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					a[i][j] = in.nextInt();
				}
			}
			cnt = new long[h][w];
			for (int i = 0; i < h; i++) {
				Arrays.fill(cnt[i], 1);
			}

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (cnt[i][j] == 1) {
						cnt[i][j] = func(i, j);
					}
				}
			}

			long ans = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					ans += cnt[i][j] % MOD;
				}
			}

			out.println(ans % MOD);
		}

		long func(int i, int j) {

			if (cnt[i][j] != 1) {
				return cnt[i][j] % MOD;
			}

			for (int k = 0; k < 4; k++) {
				int mi = i + mh4[k];
				int mj = j + mw4[k];

				if (mi < 0 || mj < 0 || mi >= h || mj >= w || a[i][j] <= a[mi][mj]) continue;
				cnt[i][j] += func(mi, mj);
				cnt[i][j] %= MOD;
			}

			return cnt[i][j] % MOD;
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
