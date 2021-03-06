package mujinprogrammingchallenge2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class E {

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
	static int[] mh4 = {-1, 1, 0, 0 };
	static int[] mw4 = { 0, 0, -1, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt(), k = in.nextInt();
			char[] d = in.nextString().toCharArray();

			char[][] s = new char[n][m];
			for (int i = 0; i < n; i++) {
				s[i] = in.nextString().toCharArray();
			}

			int sh = -1, sw = -1;
			int gh = -1, gw = -1;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (s[i][j] == 'S') {
						sh = i; sw = j;
					} else if (s[i][j] == 'G') {
						gh = i; gw = j;
					}
				}
			}

			long[][] weight = new long[4][2*k];
			for (int i = 0; i < 4; i++) {
				Arrays.fill(weight[i], LINF);
			}

			for (int i = 0; i < 2*k; i++) {
				if (d[i%k] == 'U') {
					weight[0][i] = 0;
				} else if (d[i%k] == 'D') {
					weight[1][i] = 0;
				} else if (d[i%k] == 'L') {
					weight[2][i] = 0;
				} else if (d[i%k] == 'R') {
					weight[3][i] = 0;
				}
			}
			for (int i = 2*k-1; i > 0; i--) {
				for (int j = 0; j < 4; j++) {
					if (weight[j][i-1] != 0) {
						weight[j][i-1] = weight[j][i] + 1;
					}
				}
			}

			long[][] cost = new long[n][m];
			for (int i = 0; i < n; i++) {
				Arrays.fill(cost[i], LINF);
			}
			cost[sh][sw] = 0;
			PriorityQueue<P> pq = new PriorityQueue<>();
			pq.add(new P(sh, sw, 0));

			while (!pq.isEmpty()) {
				P p = pq.remove();

				for (int i = 0; i < 4; i++) {
					int mh = p.h + mh4[i];
					int mw = p.w + mw4[i];

					if (mh < 0 || mh >= n || mw < 0 || mw >= m || s[mh][mw] == '#') {
						continue;
					}

					long mc = p.c + weight[i][(int)(p.c%k)] + 1;
					if (mc < cost[mh][mw]) {
						cost[mh][mw] = mc;
						pq.add(new P(mh, mw, mc));
					}
				}
			}

			long ans = cost[gh][gw];

			out.println(ans == LINF ? -1 : ans);

		}

		class P implements Comparable<P> {
			int h, w;
			long c;

			public P(int h, int w, long c) {
				super();
				this.h = h;
				this.w = w;
				this.c = c;
			}

			@Override
			public int compareTo(P o) {
				return (int)(this.c - o.c);
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
