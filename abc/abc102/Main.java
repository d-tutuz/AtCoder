package abc102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		long[] a;
		Map<Double, Double> memo = new HashMap<>();
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = in.nextLong()-(i+1);
			}

			double ans = Long.MAX_VALUE/2;
			ans = ternarySearch(-INF, INF);
			out.println(ans);

		}

		double f(double b) {

			if (memo.containsKey(b)) {
				return memo.get(b);
			}

			double ret = 0;
			for (long l : a) {
				ret += Math.abs(l - b);
			}
			memo.put(b, ret);
			return ret;
		}

		double ternarySearch(double l, double r) {
			for (int i = 0; i < 200; i++) {
				double a = (l + l + r) / 3;
				double b = (l + r + r) / 3;
				if (f(a) > f(b))
					l = a;
				else
					r = b;
			}
			return l;
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
