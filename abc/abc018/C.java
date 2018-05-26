package abc018;

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
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int r = in.nextInt(), c = in.nextInt(), k = in.nextInt();
			char[][] s = new char[r][c];
			for (int i = 0; i < r; i++) {
				s[i] = in.nextString().toCharArray();
			}

			long ans = 0;
			for (int h = k-1; h < r-k+1; h++) {
				for (int w = k-1; w < c-k+1; w++) {
					P src = new P(h, w);
					int[][] dist = new int[r][c];
					Queue<P> q = new ArrayDeque<>();
					q.add(src);

					boolean isGreen = false;
					while (!q.isEmpty()) {
						P p = q.remove();
						int nh = p.h;
						int nw = p.w;

						if (s[nh][nw] == 'x') {
							isGreen = true;
							break;
						}

						for (int i = 0; i < 4; i++) {
							int mh = nh + mh4[i];
							int mw = nw + mw4[i];

							if (mh < 0 || mw < 0 || mh >= r || mw >= c) {
								continue;
							}

							dist[mh][mw] = dist[nh][nw] + 1;
							if (dist[mh][mw] <= k-1) {
								q.add(new P(mh, mw));
							} else {
								continue;
							}

							if (s[mh][mw] == 'x') {
								isGreen = true;
								break;
							}

						}
					}
					if (!isGreen) {
						ans++;
					}
				}
			}

			out.println(ans);

		}
	}
	static class P {
		int h, w;

		public P(int h, int w) {
			super();
			this.h = h;
			this.w = w;
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
