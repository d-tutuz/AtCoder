package mujinprogrammingchallenge2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt();
			char[][] s = new char[n][m];
			for (int i = 0; i < n; i++) {
				s[i] = in.nextString().toCharArray();
			}

			boolean[][] used = new boolean[n][m];

			Map<Long, Integer> map = new HashMap<>();

			int mark = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (used[i][j]) {
						continue;
					}
					Queue<P> q = new ArrayDeque<>();
					q.add(new P(i, j));
					used[i][j] = true;
					map.put(gk(i, j), mark);

					while (!q.isEmpty()) {

						P p = q.remove();

						for (int k = 0; k < 4; k++) {
							int mh = p.i + mh4[k];
							int mw = p.j + mw4[k];

							if (mh < 0 || mw < 0 || mh >= n || mw >= m || s[mh][mw] == '#') {
								continue;
							}

							if (used[mh][mw]) {
								continue;
							}
							used[mh][mw] = true;
							map.put(gk(mh, mw), mark);
							q.add(new P(mh, mw));
						}
					}
					mark++;
				}
			}

			List<Long> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					list.add(gk(i, j));
				}
			}

			for (long k1 : list) {
				for (long k2 : list) {
					int i = (int)(k1 >> 32);
					int j = (int)(k1 & ((long)1<<32)-1);
				}
			}
		}

		static long gk(int i, int j) {
			return (long)i << 32 | j;
		}

		class P {
			int i, j;

			public P(int i, int j) {
				super();
				this.i = i;
				this.j = j;
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
