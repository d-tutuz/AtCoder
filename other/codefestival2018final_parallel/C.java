package codefestival2018final_parallel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			P[] p = new P[n];
			for (int i = 0; i < n; i++) {
				p[i] = new P(in.nextLong(), in.nextLong());
			}

			Arrays.sort(p);

			for (int i = 1; i < n; i++) {
				if (p[i-1].b + (p[i].a - p[i-1].a) < p[i].b) {
					p[i].b = p[i-1].b + (p[i].a - p[i-1].a);
				}
			}

			int m = in.nextInt();
			long[] t = in.nextLongArray(m);

			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = p[i].a;
			}

			for (int i = 0; i < m; i++) {
				int idx = lowerBound(a, t[i]);
				long ans = LINF;
				for (int j = Math.max(idx-1, 0); j <= Math.min(idx+1, n-1); j++) {
					if (p[j].a >= t[i]) {
						ans = Math.min(ans, p[j].b);
					} else {
						ans = Math.min(ans, p[j].b + t[i] - p[j].a);
					}
				}
				out.println(ans);
			}

		}
	}

	public static int lowerBound(long[] a, long obj) {
		int l = 0, r = a.length - 1;
		while (r - l >= 0) {
			int c = (l + r) / 2;
			if (obj <= a[c]) {
				r = c - 1;
			} else {
				l = c + 1;
			}
		}
		return l;
	}

	static class P implements Comparable<P> {
		long a, b;

		public P(long a, long b) {
			super();
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo(P o) {
			int s = Long.compare(this.a, o.a);
			int t = Long.compare(this.b, o.b);
			return s == 0 ? t : s;
		}

		@Override
		public String toString() {
			return "P [a=" + a + ", b=" + b + "]";
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

		public int[] nextIntArray1Index(int n) {
			int[] res = new int[n + 1];
			for (int i = 0; i < n; i++) {
				res[i + 1] = nextInt();
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

		public long[] nextLongArray1Index(int n) {
			long[] res = new long[n + 1];
			for (int i = 0; i < n; i++) {
				res[i + 1] = nextLong();
			}
			return res;
		}

		public double[] nextDoubleArray(int n) {
			double[] res = new double[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextDouble();
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
