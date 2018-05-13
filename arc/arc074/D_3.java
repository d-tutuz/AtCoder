package arc074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class D_3 {

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
			Queue<Long> q1 = new PriorityQueue<>((a, b) -> (int)(a-b));
			Queue<Long> q2 = new PriorityQueue<>((a, b) -> (int)-(a-b));

			long[] sum1 = new long[n+1];
			long[] sum2 = new long[n+1];

			for (int i = 0; i < n; i++) {
				q1.add(an[i]);
				sum1[0] += an[i];
			}

			for (int k = n; k < 2*n; k++) {
				q1.add(an[k]);
				sum1[k-n+1] += sum1[k-n]+an[k]-q1.remove();
			}

			for (int i = 3*n-1; i >= 2*n; i--) {
				q2.add(an[i]);
				sum2[0] += an[i];
			}

			for (int k = 2*n-1; k >= n; k--) {
				q2.add(an[k]);
				sum2[2*n-k] += sum2[2*n-k-1]+an[k]-q2.remove();
			}

			for (int i = 0; i <= n; i++) {
				ans = Math.max(ans, sum1[i]-sum2[n-i]);
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
