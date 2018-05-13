package arc074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
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

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			Long[] an = new Long[3*n];
			for (int i = 0; i < 3*n; i++) {
				an[i] = in.nextLong();
			}


			long ans = Long.MIN_VALUE;
			for (int k = n-1; k < 2*n; k++) {
				Long[] a = new Long[3*n];
				for (int i = 0; i < 3*n; i++) {
					a[i] = 0L;
				}
				Long[] b = new Long[3*n];
				for (int i = 0; i < 3*n; i++) {
					b[i] = Long.MAX_VALUE;
				}
				long tmp = 0;
				for (int i = 0; i <= k; i++) {
					a[i] = an[i];
				}
				for (int i = k+1; i < 3*n; i++) {
					b[i] = an[i];
				}
				Arrays.sort(a, Comparator.reverseOrder());
				Arrays.sort(b, Comparator.naturalOrder());

				for (int i = 0; i < n; i++) {
					tmp += a[i];
					tmp -= b[i];
				}

				ans = Math.max(ans, tmp);
			}

			out.println(ans);

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
