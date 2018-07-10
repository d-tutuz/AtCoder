package arc092;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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

			int n = in.nextInt();
			long[] A = in.nextLongArray(n);
			long[] B = in.nextLongArray(n);

			long ans = 0;

			// k bit目に着目
			for (int i = 0; i < 30; i++) {

				long[] a = new long[n];
				long[] b = new long[n];
				long T = 1<<i;
				for (int j = 0; j < n; j++) {
					a[j] = A[j] % (2*T);
					b[j] = B[j] % (2*T);
				}
				Arrays.sort(b);

				long tmp = 0;
				// a[i]を固定して、範囲に含まれるb[i]の含まれる個数を数え上げる
				for (int ai = 0; ai < n; ai++) {

					tmp += lowerBound(b, 2*T - a[ai]) - lowerBound(b, 1*T - a[ai]);
					tmp += lowerBound(b, 4*T - a[ai]) - lowerBound(b, 3*T - a[ai]);

				}
				if (tmp % 2 == 1) {
					ans |= 1 << i;
				}
			}

			out.println(ans);

		}

		public static int lowerBound(long[] a, long obj) {
			int l = 0, r = a.length - 1;
			while (r - l >= 0) {
				int c = (l + r) / 2;
				if (obj <= a[c]) {
					r = c - 1;
				} else {
					l = c + 1;
				}
			}
			return l;
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

		public long[] nextLongArray(int n) {
			long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong();
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
