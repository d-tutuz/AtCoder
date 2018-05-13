package abc075;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int k = in.nextInt();
			P[] ps = new P[n];
			long[] xs = new long[n];
			long[] ys = new long[n];

			for (int i = 0; i < n; i++) {
				long x = in.nextLong();
				long y = in.nextLong();
				ps[i] = new P(x, y);
				xs[i] = x;
				ys[i] = y;
			}

			long sum = Long.MAX_VALUE;
			for (int x1 = 0; x1 < n; x1++) {
				for (int x2 = 0; x2 < n; x2++) {
					for (int y1 = 0; y1 < n; y1++) {
						for (int y2 = 0; y2 < n; y2++) {
							int count = 0;
							long xl = min(xs[x1], xs[x2]);
							long xr = max(xs[x1], xs[x2]);
							long yd = min(ys[y1], ys[y2]);
							long yu = max(ys[y1], ys[y2]);

							for (int i = 0; i < n; i++) {
								if (xl <= ps[i].x && ps[i].x <= xr
										&& yd <= ps[i].y && ps[i].y <= yu) {
									count++;
								}
								if (count >= k) {
									sum = min(sum, abs(xr-xl)*abs(yu-yd));
								}
							}
						}
					}
				}
			}
			out.println(sum);
		}
	}

	static class P {
		long x, y;
		P(long x, long y) {
			this.x = x;
			this.y = y;
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
