package abc074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class C_3 {

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
		static double mc = 100;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int A = in.nextInt(), B = in.nextInt(), C = in.nextInt(), D = in.nextInt();
			int E = in.nextInt(), F = in.nextInt();

			double ans = 0;
			double answa = -1;
			double anssu = -1;
			for (double i = 0; i <= mc; i++) {
				for (double j = 0; j <= mc; j++) {
					for (double k = 0; k <= mc; k++) {
						for (double l = 0; l <= mc; l++) {
							if (i == 0 && j == 0 && k == 0 && l == 0) {
								continue;
							}
							if ((A*i+B*j)*E >= (C*k+D*l) && 100*(A*i+B*j)+(C*k+D*l) <= F) {
								if (ans <= (C*k+D*l)/(100*(A*i+B*j)+(C*k+D*l))) {
									ans = (C*k+D*l)/(100*(A*i+B*j)+(C*k+D*l));
									answa = 100*(A*i+B*j)+C*k+D*l;
									anssu = C*k+D*l;
								}
							}
						}
					}
				}
			}
			out.println((int)answa +" "+(int)anssu);

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
