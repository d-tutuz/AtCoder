package abc024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class C_2 {

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
				ps[i] = new P(in.nextInt(), in.nextInt());
			}

			for (int i = 0; i < k; i++) {
				int s = in.nextInt(), t = in.nextInt();

				if (s < t) {
					int now = s;
					for (int j = 0; j < d; j++) {
						P p = ps[j];
						if (p.l <= now && now <= p.r) {
							now = p.r;
						}
						if (now >= t) {
							out.println(j+1);
							break;
						}
					}
				} else {
					int now = s;
					for (int j = 0; j < d; j++) {
						P p = ps[j];
						if (p.l <= now && now <= p.r) {
							now = p.l;
						}
						if (now <= t) {
							out.println(j+1);
							break;
						}
					}
				}
			}

		}
	}
	static class P {
		int l, r;

		public P(int l, int r) {
			super();
			this.l = l;
			this.r = r;
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
