package abc075;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class D_2 {

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

			int N = in.nextInt(), K = in.nextInt();
			P[] p = new P[N];
			for (int i = 0; i < N; i++) {
				long x = in.nextLong(), y = in.nextLong();
				p[i] = new P(x, y);
			}

			long ans = Long.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 0; k < N; k++) {
						for (int m = 0; m < N; m++) {

							long xmin = minPx(p[i], p[j], p[k], p[m]);
							long xmax = maxPx(p[i], p[j], p[k], p[m]);
							long ymin = minPy(p[i], p[j], p[k], p[m]);
							long ymax = maxPy(p[i], p[j], p[k], p[m]);

							long count = 0;
							for (P ps : p) {
								if (xmin <= ps.x && ps.x <= xmax && ymin <= ps.y && ps.y <= ymax) {
									count++;
								}
							}

							if (count >= K) {
								ans = Math.min(ans, (xmax - xmin) * (ymax - ymin));
							}

						}
					}
				}
			}

			out.println(ans);

		}

		long minPx(P p1, P p2, P p3, P p4) {
			return Math.min(Math.min(p1.x, p2.x), Math.min(p3.x, p4.x));
		}

		long maxPx(P p1, P p2, P p3, P p4) {
			return Math.max(Math.max(p1.x, p2.x), Math.max(p3.x, p4.x));
		}

		long minPy(P p1, P p2, P p3, P p4) {
			return Math.min(Math.min(p1.y, p2.y), Math.min(p3.y, p4.y));
		}

		long maxPy(P p1, P p2, P p3, P p4) {
			return Math.max(Math.max(p1.y, p2.y), Math.max(p3.y, p4.y));
		}


		class P {
			long x, y;

			public P(long x, long y) {
				super();
				this.x = x;
				this.y = y;
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
