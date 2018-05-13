package abc006;

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
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int[] an = in.nextIntArray(n);
			int[] bn = Arrays.copyOf(an, n);

			int count1 = 0;
			// 要素を選択
			for (int i = 1; i < n; i++) {
				if (an[i-1] > an[i]) {
					for (int j = i; j > 0; j--) {
						if (an[j-1] > an[j]) {
							swap(j-1, j, an);
						}
					}
					count1++;
				}
			}

			int count2 = 0;
			// 要素を選択
			for (int i = n-1; i > 0; i--) {
				if (bn[i-1] > bn[i]) {
					for (int j = i-1; j < n-1; j++) {
						if (bn[j] > bn[j+1]) {
							swap(j, j+1, bn);
						}
					}
					count2++;
				}
			}

			out.println(Math.min(count1, count2));

		}

		static void swap(int i, int j, int[] an) {
			int tmp = an[i];
			an[i] = an[j];
			an[j] = tmp;
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
