package kuramoti1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class E {

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
			long[] a = in.nextLongArray(3*n);

			long ans = Long.MIN_VALUE;

			LinkedList<Long> f = new LinkedList<>();
			LinkedList<Long> b = new LinkedList<>();

			for (int k = 0; k <= n; k++) {
				long sum = 0;
				for (int j = 0; j < 3*n; j++) {
					if (j < n+k) {
						f.add(a[j]);
					} else {
						b.add(a[j]);
					}
				}
				Collections.sort(f, Collections.reverseOrder());
				Collections.sort(b);

				for (int i = 0; i < n; i++) {
					sum += f.removeFirst()-b.removeFirst();
				}

				ans = Math.max(ans, sum);
				f.clear();
				b.clear();
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
