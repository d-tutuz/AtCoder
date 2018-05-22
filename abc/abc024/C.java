package abc024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.InputMismatchException;
import java.util.Queue;
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

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), d = in.nextInt(), k = in.nextInt();
			P[] ps = new P[d];
			for (int i = 0; i < d; i++) {
				ps[i] = new P(in.nextInt(), in.nextInt(), i+1);
			}

			for (int i = 0; i < k; i++) {
				int ans = INF;
				Queue<P> q = new ArrayDeque<>();

				int f = in.nextInt(), t = in.nextInt();

				if (f < t) {
					q.add(new P(f, f, -1));

					while (!q.isEmpty()) {
						P pn = q.remove();
						int num = pn.t;
						int nowCost = pn.c;

						if (ans < nowCost) {
							continue;
						}

						if (t <= num) {
							ans = Math.min(ans, pn.c);
						}

						for (P p : ps) {
							if (p.f <= num && num < p.t && nowCost <= p.c) {
								q.add(p);
							}
						}
					}
				} else {
					q.add(new P(f, f, -1));

					while (!q.isEmpty()) {
						P pn = q.remove();
						int num = pn.f;
						int nowCost = pn.c;

						if (ans < nowCost) {
							continue;
						}

						if (num <= t) {
							ans = Math.min(ans, pn.c);
						}

						for (P p : ps) {
							if (p.f < num && num <= p.t && nowCost <= p.c) {
								q.add(p);
							}
						}
					}
				}

				out.println(ans);
			}


		}
	}

	static class P {
		int f, t, c;

		public P(int f, int t, int c) {
			super();
			this.f = f;
			this.t = t;
			this.c = c;
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
