package arc038;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B {

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

	static class TaskX {

		int h, w;
		char[][] s;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			h = in.nextInt(); w = in.nextInt();
			boolean[][] win = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				Arrays.fill(win[i], false);
			}
			s = new char[h][w];
			for (int i = 0; i < h; i++) {
				s[i] = in.nextString().toCharArray();
			}

			int[] mh2 = { 1, 0 };
			int[] mw2 = { 0, 1 };

			Queue<P> q = new ArrayDeque<>();
			q.add(new P(0, 0));

			List<P> list = new ArrayList<>();
			while (!q.isEmpty()) {
				P p = q.remove();
				boolean ok = false;
				for (int i = 0; i < 2; i++) {
					int mh = p.h + mh2[i];
					int mw = p.w + mw2[i];

					if (canMove(mh, mw)) {
						ok = true;
						q.add(new P(mh, mw));
					}
				}

				if (!ok) list.add(new P(p.h, p.w));
			}

			int[] mh3 = { -1, 0, -1 };
			int[] mw3 = { 0, -1, -1 };

			for (P pp : list) {
//				out.println(pp);
				q.add(pp);
			}
			while (!q.isEmpty()) {
				P p = q.remove();

				for (int i = 0; i < 3; i++) {
					int mh = p.h + mh3[i];
					int mw = p.w + mw3[i];

					if (canMove(mh, mw)) {
						if (!win[p.h][p.w]) {
							win[mh][mw] = true;
						}
						q.add(new P(mh, mw));
					}
				}
			}

			print(win);

			out.println(win[0][0] ? "First" : "Second");

		}

		boolean canMove(int hh, int ww) {
			return 0 <= hh && hh < h && 0 <= ww && ww < w && s[hh][ww] != '#';
		}
	}

	static void print(boolean[][] ok) {
		int n = ok.length;
		int m = ok[0].length;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(ok[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
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

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + h;
			result = prime * result + w;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			P other = (P) obj;
			if (h != other.h)
				return false;
			if (w != other.w)
				return false;
			return true;
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
