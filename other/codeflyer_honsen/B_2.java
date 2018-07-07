package codeflyer_honsen;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class B_2 {

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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), q = in.nextInt();
			long[] x = new long[n+1];
			long[] sum = new long[n+1];
			for (int i = 0; i < n; i++) {
				x[i+1] = in.nextLong();
				sum[i+1] = sum[i] + x[i+1];
			}

			while (q-- > 0) {
				long ans = 0;
				long c = in.nextLong(), d = in.nextLong();

				int l = max(lowerBound(x, c-d), 1);
				int m = max(lowerBound(x, c), 1);
				int r = max(lowerBound(x, c+d), 1);

				ans += d * (l + (n-r));
				ans += (long)(m-l)*c - (sum[m-1] - sum[l-1]);
				ans += (sum[r-1] - sum[m-1]) - (long)(r-m)*c;

				out.println(ans);

			}
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
