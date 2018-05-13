package abc095;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class D_VArtem {

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
	static int modP = 1000000007;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {
			int n = in.nextInt();
			long c = in.nextLong();
			long[] x = new long[n], v = new long[n];
			for (int i = 0; i < n; i++) {
				x[i] = in.nextLong();
				v[i] = in.nextLong();
			}

			long ans = solve(x, v, c);
			for (int i = 0; i < n; i++) {
				x[i] = c - x[i];
			}
			for (int i = 0, j = n - 1; i < j; i++, j--) {
				long tmp = x[i];
				x[i] = x[j];
				x[j] = tmp;

				tmp = v[i];
				v[i] = v[j];
				v[j] = tmp;
			}
			out.println(Math.max(ans, solve(x, v, c)));
		}

		private long solve(long[] x, long[] v, long c) {
			int n = x.length;
			long[] sufMax = new long[n + 1];
			long cur = 0;
			for (int i = n - 1; i >= 0; i--) {
				cur += v[i];
				sufMax[i] = Math.max(sufMax[i + 1], cur - (c - x[i]));
			}

			cur = 0;
			long ans = 0;
			for (int i = 0; i < n; i++) {
				cur += v[i];
				ans = Math.max(ans, cur - x[i]);
				ans = Math.max(ans, cur - 2 * x[i] + sufMax[i + 1]);
			}
			return ans;
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
