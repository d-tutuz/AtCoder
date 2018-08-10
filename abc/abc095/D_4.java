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

public class D_4 {

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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			long c = in.nextLong();

			P[] p = new P[n+1];
			P[] p2 = new P[n+1];
			p[0] = new P(0, 0);
			for (int i = 1; i < n+1; i++) {
				long x = in.nextLong(), v = in.nextLong();
				p[i] = new P(x, v);
			}
			for (int i = 0; i < n+1; i++) {
				p2[i] = new P((c - p[(n+1-i)%(n+1)].x) % c, p[(n+1-i)%(n+1)].v);
			}

			long[] lsum = new long[n+1];
			long[] lmax = new long[n+1];
			for (int i = 1; i < n+1; i++) {
				lsum[i] = lsum[i-1] + p2[i].v - (p2[i].x - p2[i-1].x);
			}
			for (int i = 1; i < n+1; i++) {
				lmax[i] = max(lmax[i-1], lsum[i]);
			}

			long[] rsum = new long[n+1];
			long[] rmax = new long[n+1];
			for (int i = 1; i < n+1; i++) {
				rsum[i] = rsum[i-1] + p[i].v - (p[i].x - p[i-1].x);
			}
			for (int i = 1; i < n+1; i++) {
				rmax[i] = max(rmax[i-1], rsum[i]);
			}

			long ansr = 0;
			for (int i = 0; i < n+1; i++) {
				ansr = max(ansr, rmax[i] + max(lmax[n-i] - p[i].x, 0));
			}

			long ansl = 0;
			for (int i = 0; i < n+1; i++) {
				ansl = max(ansl, lmax[i] + max(rmax[n-i] - p2[i].x, 0));
			}

			out.println(max(ansl, ansr));

		}

		class P {
			long x, v;

			public P(long x, long v) {
				super();
				this.x = x;
				this.v = v;
			}

			@Override
			public String toString() {
				return x + ":" + v;
			}

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

		public int[] nextIntArrayDec(int n) {
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt() - 1;
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

		public long[] nextLongArrayDec(int n) {
			long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong() - 1;
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
