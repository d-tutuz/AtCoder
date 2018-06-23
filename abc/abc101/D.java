package abc101;

import static java.lang.Math.*;

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

	static long INF = Long.MAX_VALUE/3;
	static long MK = (long)pow(10, 15);

	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			long k = in.nextLong();

			long count = 0;
			if (k >= 9) {
				out.println(1);
				out.println(2);
				out.println(3);
				out.println(4);
				out.println(5);
				out.println(6);
				out.println(7);
				out.println(8);
				out.println(9);
			}
			for (long n = 19; n <= MK; n += 10) {

				if (n*S(n+1) <= (n+1)*S(n)
						&& n*S(n+1) <= (n+1)*S(n)
						&& n*S(n+10) <= (n+10)*S(n)
						&& n*S(n+100) <= (n+100)*S(n)
						&& n*S(n+1000) <= (n+1000)*S(n)
						&& n*S(n+10000) <= (n+10000)*S(n)
						&& n*S(n+100000) <= (n+100000)*S(n)
						&& n*S(n+1000000) <= (n+1000000)*S(n)
						) {
					if (++count <= k-9) {
						out.println(n);
					} else {
						break;
					}
				}
			}

		}

		long S(long num) {
			long ret = 0;
			while (num > 0) {
				ret += num % 10;
				num /= 10;
			}
			return ret;
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
