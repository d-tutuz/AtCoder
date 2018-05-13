package abc095;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
	static int modP = 1000000007;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			long c = in.nextLong();
			long[] d1 = new long[n+2];
			long[] d2 = new long[n+2];
			long[] v = new long[n+2];
			long[] sum1 = new long[n+2];
			long[] sum2 = new long[n+2];

			d1[0] = 0; d1[n+1] = c;
			d2[0] = c; d2[n+1] = 0;

			for (int i = 1; i <= n; i++) {
				long tx = in.nextLong();
				long tv = in.nextLong();
				d1[i] = tx;
				d2[i] = c - tx;
				v[i] = tv;
			}

			for (int i = 0; i < n+1; i++) {
				sum1[i+1] = sum1[i] + v[i+1];
			}
			for (int i = n+1; i > 0; i--) {
				sum2[i-1] = sum2[i] + v[i-1];
			}

			long[] value1 = new long[n+2];
			long[] value2 = new long[n+2];

			for (int i = 0; i < n + 1; i++) {
				value1[i+1] = Math.max(value1[i+1], value1[i]+v[i+1]-d1[i+1]+d1[i]);
			}
			for (int i = 0; i < n + 1; i++) {
				value2[i+1] = Math.max(value2[i+1], value2[i]+v[i+1]+(-2*d1[i+1]+2*d1[i]));
			}

			long[] maxValue1 = Arrays.copyOf(value1, n+2);
			long[] maxValue2 = Arrays.copyOf(value2, n+2);
			for (int i = 1; i < n + 2; i++) {
				maxValue1[i] = Math.max(maxValue1[i-1], maxValue1[i]);
			}
			for (int i = 1; i < n + 2; i++) {
				maxValue2[i] = Math.max(maxValue2[i-1], maxValue2[i]);
			}

			long ans1 = 0;
			for (int y = n + 1; y > 0; y--) {
				long tmp = 0;
				tmp += (sum2[y]-d2[y]+maxValue2[y-1]);
				ans1 = Math.max(ans1, tmp);
			}

			long ans2 = 0;
			for (int y = n + 1; y > 0; y--) {
				long tmp = 0;
				tmp += (sum2[y]-2*d2[y]+maxValue1[y-1]);
				ans2 = Math.max(ans2, tmp);
			}

			System.out.println(Math.max(ans1, ans2));
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
