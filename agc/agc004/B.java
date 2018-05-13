package agc004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class B {

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
			long x = in.nextLong();
			long min = INF;
			long sum = 0;
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				long t = in.nextLong();
				a[i] = t;
				min = Math.min(min, t);
				sum += t;
			}

			long[] nums = new long[n];
			long[] counts = new long[n];

			for (int i = 0; i < n; i++) {
				long cost = a[i];
				long count = 0;
				long num = i;
				for (int j = 0; j < n; j++) {
					if (i < j) {
						if (a[j] + (n-(j-i))*x < cost) {
							cost = a[j] + (n-(j-i))*x;
							count = n-(j-i);
							num = j;
						}
					} else {
						if (a[j] + (i-j)*x < cost) {
							cost = a[j] + (i-j)*x;
							count = i-j;
							num = j;
						}
					}
				}
				counts[i] = count;
				nums[i] = num;
			}

			long ans = 0;
			long max = 0;
			for (int i = 0; i < n; i++) {
				max = Math.max(max, counts[i]);
			}

			for (int i = 0; i < n; i++) {
				ans += a[(int) nums[i]];
			}
			ans += x*max;

			System.out.println(ans);


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
