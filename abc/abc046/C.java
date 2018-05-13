package abc046;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class C {

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
			long[] t = new long[n], a = new long[n];
			for (int i = 0; i < n; i++) {
				t[i] = in.nextLong();
				a[i] = in.nextLong();
			}

			long nt = t[0], na = a[0];
			for (int i = 1; i < n; i++) {
				if (t[i-1]<=t[i] && a[i-1]<=a[i]) {
					nt = t[i];
					na = a[i];
				} else if (t[i-1]>t[i] && a[i-1]>a[i]) {
					long mul, mul1, mul2;
					if (t[i-1]%t[i]==0) {
						mul1 = t[i-1]/t[i];
					} else {
						mul1 = (t[i-1]/t[i])+1;
					}

					if (a[i-1]%a[i]==0) {
						mul2 = a[i-1]/a[i];
					} else {
						mul2 = (a[i-1]/a[i])+1;
					}

					mul = Math.max(mul1, mul2);
					t[i] *= mul;
					a[i] *= mul;

				} else if (t[i-1]>t[i]) {
					long mul = 1;
					if (t[i-1]%t[i]==0) {
						mul = t[i-1]/t[i];
					} else {
						mul = (t[i-1]/t[i])+1;
					}
					t[i] *= mul;
					a[i] *= mul;

				} else if (a[i-1]>a[i]) {
					long mul = 1;
					if (a[i-1]%a[i]==0) {
						mul = a[i-1]/a[i];
					} else {
						mul = (a[i-1]/a[i])+1;
					}
					t[i] *= mul;
					a[i] *= mul;
				}
//				out.println(t[i] + " " + a[i]);
			}

			out.println(t[n-1]+a[n-1]);

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
