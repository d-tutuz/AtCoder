package 九州大学プログラミングコンテスト2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;
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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int h = in.nextInt(), w = in.nextInt(), x = in.nextInt();
			char[][] s = new char[h][w];
			for (int i = 0; i < h; i++) {
				s[i] = in.nextString().toCharArray();
			}

			P st = null, gl = null;
			List<T> list = new ArrayList<>();
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (s[i][j] == 'S') {
						st = new P(i, j);
					}

					if (s[i][j] == 'G') {
						gl = new P(i, j);
					}

					if (s[i][j] == '@') {
						list.add(new T(i, j, x));
					}
				}
			}

			for (T ps : list) {
				Queue<T> q = new ArrayDeque<>();
				q.add(ps);
				while (!q.isEmpty()) {
					T p = q.remove();

					int nh = p.h;
					int nw = p.w;
					int count = p.c;

					if (s[nh][nw] == '#' || count == 0) continue;

					for (int i = 0; i < 4; i++) {
						int mh = nh + mh4[i];
						int mw = nw + mw4[i];
						if (s[mh][mw] == '#') continue;
						s[mh][mw] = '@';
						q.add(new T(mh, mw, count-1));
					}
				}
			}

			Queue<P> q = new ArrayDeque<>();
			q.add(st);
			int[][] cost = new int[h][w];
			fill(cost, INF);
			cost[st.h][st.w] = 0;

			while (!q.isEmpty()) {
				P p = q.remove();
				int nh = p.h;
				int nw = p.w;

				if (s[nh][nw] == '#') continue;

				for (int i = 0; i < 4; i++) {
					int mh = nh + mh4[i];
					int mw = nw + mw4[i];
					if (s[mh][mw] == '#' || s[mh][mw] == '@' || cost[mh][mw] <= cost[nh][nw] + 1) continue;
					cost[mh][mw] = cost[nh][nw] + 1;
					q.add(new P(mh, mw));
				}
			}

			out.println(cost[gl.h][gl.w] == INF ? -1 : cost[gl.h][gl.w]);
		}
	}

	static void fill(int[][] a, int v) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				a[i][j] = v;
			}
		}
	}

	static class P {
		int h, w;

		public P(int h, int w) {
			super();
			this.h = h;
			this.w = w;
		}

		@Override
		public String toString() {
			return "P [h=" + h + ", w=" + w + "]";
		}
	}


	static class T {
		int h, w, c;

		public T(int h, int w, int c) {
			super();
			this.h = h;
			this.w = w;
			this.c = c;
		}

		@Override
		public String toString() {
			return "T [h=" + h + ", w=" + w + ", c=" + c + "]";
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
