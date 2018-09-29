package codefestival2014quala;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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

		char[] a;
		int k, n;
		long ans = Long.MAX_VALUE/10, l;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			l = in.nextLong();
			a = String.valueOf(l).toCharArray();
			n = a.length;
			k = in.nextInt();

			func(0, 0, 0, 0, "");

			long tmp = 0;
			for (int i = 0; i < n-1; i++) {
				tmp *= 10;
				tmp += 9;
			}

			out.println(Math.min(ans, l - tmp));

		}

		void func(int i, int set, int lt, int gt, String str) {

			if (i == n) {
				ans = Math.min(ans, Math.abs(l - Long.parseLong(str)));
				return;
			}

			int d = a[i] - '0';

			if (lt == 0 && gt == 0) {
				for (int e = 0; e <= 9; e++) {
					int nextset = set | (1 << e);
					if (Integer.bitCount(nextset) > k) continue;

					lt = e < d ? 1 : 0;
					gt = e > d ? 1 : 0;

					func(i+1, nextset, lt, gt, str + String.valueOf(e));
				}
			} else if (lt == 1) {
				for (int e = 9; e >= 0; e--) {
					int nextset = set | (1 << e);
					if (Integer.bitCount(nextset) > k) continue;

					func(i+1, nextset, 1, 0, str + String.valueOf(e));
					break;
				}
			} else if (gt == 1) {
				for (int e = 0; e <= 9; e++) {
					int nextset = set | (1 << e);
					if (Integer.bitCount(nextset) > k) continue;

					func(i+1, nextset, 0, 1, str + String.valueOf(e));
					break;
				}
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
