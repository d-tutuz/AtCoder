package abc093;

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
	static int modP = 1000000007;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int Q = in.nextInt();
			long[] an = new long[Q];
			long[] bn = new long[Q];
			for (int i = 0; i < Q; i++) {
				an[i] = in.nextLong();
				bn[i] = in.nextLong();
			}

			for (int i = 0; i < Q; i++) {
				long a = Math.min(an[i], bn[i]);
				long b = Math.max(an[i], bn[i]);

				if (a == b || a + 1 == b) {
					out.println(2 * a - 2);
				} else {
					long c = sqrt(a, b);
					if (c * (c + 1) >= a * b) {
						out.println(2 * c - 2);
					} else {
						out.println(2 * c - 1);
					}
				}
			}

		}
		static long sqrt(long a, long b) {
			long c = (long)Math.sqrt(a * b);
			while (c * c >= a * b) {
				c--;
			}
			return c;
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
