package agc019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class A {

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

			long q = in.nextLong();
			long h = in.nextLong();
			long s = in.nextLong();
			long d = in.nextLong();
			double n = in.nextDouble();

			long min2_00 = Math.min(Math.min(q*8, h*4), Math.min(s*2, d*1));
			long min1_00 = Math.min(Math.min(q*4, h*2), s*1);
			long min0_50 = Math.min(q*2, h*1);
			long min0_25 = q;

			long sum = 0;
			while (n-2>=0) {
				n = n-2;
				sum += min2_00;
			}
			while (n-1>=0) {
				n = n-1;
				sum += min1_00;
			}
			while (n-0.5>=0) {
				n = n-0.5;
				sum += min0_50;
			}
			while (n-0.25>=0) {
				n = n-0.25;
				sum += min0_25;
			}

			out.println(sum);


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
