package abc010;

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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			P src = new P(in.nextDouble(), in.nextDouble());
			P tar = new P(in.nextDouble(), in.nextDouble());

			int t = in.nextInt(), v = in.nextInt();
			int n = in.nextInt();

			P[] ps = new P[n];
			for (int i = 0; i < n; i++) {
				ps[i] = new P(in.nextDouble(), in.nextDouble());
			}

			boolean isTrue = false;
			for (int i = 0; i < n; i++) {
				if (dist(src, ps[i]) + dist(ps[i], tar) > t*v) {
					continue;
				}
				isTrue = true;
				break;
			}

			out.println(isTrue ? "YES" : "NO");

		}
	}
	static class P {
		double x, y;

		public P(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static double dist(P a, P b) {
		return Math.sqrt(Math.pow((a.x-b.x),2) + Math.pow((a.y-b.y),2));
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
