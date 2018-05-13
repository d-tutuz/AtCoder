package abc095;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
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

	static int INF = 1 << 30;
	static int modP = 1000000007;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			long c = in.nextLong();
			long[] x = new long[n], v = new long[n];
			for (int i = 0; i < n; i++) {
				x[i] = in.nextLong(); v[i] = in.nextLong();
			}

			// right
			long ans1 = 0;
			long cur = 0;
			for (int i = 0; i < n; i++) {
				cur += v[i];
				ans1 = max(ans1, cur - x[i]);
			}

			// left
			long ans2 = 0;
			cur = 0;
			for (int i = n - 1; i >= 0; i--) {
				cur += v[i];
				ans2 = max(ans2, cur - (c - x[i]));
			}

			// right -> left
			long ans3 = 0;
			cur = 0;
			long[] value = new long[n];
			for (int i = 0; i < n; i++) {
				cur += v[i];
				if (i == 0) {
					value[i] = cur - 2*x[i];
				} else {
					value[i] = max(cur - 2*x[i], value[i-1]);
				}
			}
			cur = 0;
			for (int i = n - 1; i > 0; i--) {
				cur += v[i];
				ans3 = max(ans3, cur - (c - x[i]) + value[i-1]);
			}

			// left -> right
			long ans4 = 0;
			cur = 0;
			value = new long[n];
			for (int i = n - 1; i >= 0; i--) {
				cur += v[i];
				if (i == n - 1) {
					value[i] = cur - 2*(c - x[i]);
				} else {
					value[i] = max(cur - 2*(c - x[i]), value[i+1]);
				}
			}
			cur = 0;
			for (int i = 0; i < n-1; i++) {
				cur += v[i];
				ans4 = max(ans4, cur - x[i] + value[i+1]);
			}

			out.println(max(max(ans1, ans2), max(ans3, ans4)));
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
