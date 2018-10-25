package codefestival2016grandfinal_parallel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Queue;
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
			P[] p = new P[2*n];
			for (int i = 0; i < n; i++) {
				p[i] = new P(in.nextInt(), 0);
			}
			for (int i = 0; i < n; i++) {
				p[i+n] = new P(in.nextInt(), 1);
			}
			Arrays.sort(p);

			Queue<Integer> s0 = new ArrayDeque<>(), s1 = new ArrayDeque<>();
			long ans = 1;
			for (int i = 0; i < 2*n; i++) {
				if (p[i].i == 0) {
					if (s1.size() > 0) {
						ans *= s1.size();
						ans %= MOD;
						s1.remove();
					} else {
						s0.add(p[i].pos);
					}
				} else {
					if (s0.size() > 0) {
						ans *= s0.size();
						ans %= MOD;
						s0.remove();
					} else {
						s1.add(p[i].pos);
					}
				}
			}

			out.println(ans % MOD);
		}
	}

	static class P implements Comparable<P> {
		int pos, i;

		public P(int pos, int i) {
			super();
			this.pos = pos;
			this.i = i;
		}

		@Override
		public String toString() {
			return "P [pos=" + pos + ", i=" + i + "]";
		}

		@Override
		public int compareTo(P o) {
			return Integer.compare(this.pos, o.pos);
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
