package pra_20190126;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class E {

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		MyInput in = new MyInput(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static int INF = 1 << 30;
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
//	static int[] mh4 = { 0, -1, 1, 0 };
//	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh4 = { -1, 1, 0,  0 };
	static int[] mw4 = {  0, 0, 1, -1 };

	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		int n, m, k;
		@SuppressWarnings("unchecked")
		public void solve(int testNumber, MyInput in, PrintWriter out) {

			n = in.nextInt(); m = in.nextInt(); k = in.nextInt();
			char[] d = in.nextChars();
			char[][] s = new char[n][m];
			int sh = -1, sw = -1, gh = -1, gw = -1;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					s[i][j] = in.nextChar();
					if (s[i][j] == 'S') {
						sh = i;
						sw = j;
					}
					if (s[i][j] == 'G') {
						gh = i;
						gw = j;
					}
				}
			}
			List<Integer>[] g = new ArrayList[4];
			g = Stream.generate(ArrayList::new).limit(4).toArray(List[]::new);
			for (int i = 0; i < 2*k; i++) {
				if (d[i%k] == 'U') g[0].add(i);
				if (d[i%k] == 'D') g[1].add(i);
				if (d[i%k] == 'R') g[2].add(i);
				if (d[i%k] == 'L') g[3].add(i);
			}

			long[][] w = new long[k][4];
			for (int t = 0; t < k; t++) {
				for (int i = 0; i < 4; i++) {
					int idx = lowerBound(g[i], t);
					if (g[i].isEmpty()) {
						w[t][i] = LINF;
						continue;
					}
					int v = g[i].get(idx);
					w[t][i] = v - t + 1;
				}
			}

			long[][] cost = new long[n][m];
			PriorityQueue<T> pq = new PriorityQueue<>();
			fill(cost, LINF);
			cost[sh][sw] = 0;
			pq.add(new T(new P(sh, sw), 0));

			while (!pq.isEmpty()) {
				T tup = pq.remove();
				if (tup.c > cost[tup.p.h][tup.p.w]) continue;

				int nh = tup.p.h, nw = tup.p.w;
				int ntime = (int)(cost[tup.p.h][tup.p.w] % k);
				for (int i = 0; i < 4; i++) {
					int mh = nh + mh4[i];
					int mw = nw + mw4[i];
					if (isInside(mh, mw) && s[mh][mw] != '#') {
						if (cost[nh][nw] + w[ntime][i] < cost[mh][mw]) {
							cost[mh][mw] = cost[nh][nw] + w[ntime][i];
							pq.add(new T(new P(mh, mw), cost[mh][mw]));
						}
					}
				}
			}

			out.println(cost[gh][gw] == LINF ? -1 : cost[gh][gw]);
		}

		boolean isInside(int i, int j) {
			return 0 <= i && i < n && 0 <= j && j < m;
		}

		class P {
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

		class T implements Comparable<T> {
			P p;
			long c;

			@Override
			public int compareTo(T o) {
				return Long.compare(this.c, o.c);
			}

			public T(P p, long c) {
				super();
				this.p = p;
				this.c = c;
			}


			@Override
			public String toString() {
				return "T [p=" + p + ", c=" + c + "]";
			}

		}
	}

	static void fill(long[][] a, long v) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				a[i][j] = v;
			}
		}
	}

	static void print(int[][] a, PrintWriter out) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				if (j > 0)
					out.print(" ");
				out.print(a[i][j]);
			}
			out.print("\n");
		}
	}

	public static int lowerBound(List<Integer> a, int obj) {
		int l = 0, r = a.size() - 1;
		while (r - l >= 0) {
			int c = (l + r) / 2;
			if (obj <= a.get(c)) {
				r = c - 1;
			} else {
				l = c + 1;
			}
		}
		return l;
	}

	static class MyInput {
		private final BufferedReader in;
		private static int pos;
		private static int readLen;
		private static final char[] buffer = new char[1024 * 8];
		private static char[] str = new char[500 * 8 * 2];
		private static boolean[] isDigit = new boolean[256];
		private static boolean[] isSpace = new boolean[256];
		private static boolean[] isLineSep = new boolean[256];

		static {
			for (int i = 0; i < 10; i++) {
				isDigit['0' + i] = true;
			}
			isDigit['-'] = true;
			isSpace[' '] = isSpace['\r'] = isSpace['\n'] = isSpace['\t'] = true;
			isLineSep['\r'] = isLineSep['\n'] = true;
		}

		public MyInput(InputStream is) {
			in = new BufferedReader(new InputStreamReader(is));
		}

		public int read() {
			if (pos >= readLen) {
				pos = 0;
				try {
					readLen = in.read(buffer);
				} catch (IOException e) {
					throw new RuntimeException();
				}
				if (readLen <= 0) {
					throw new MyInput.EndOfFileRuntimeException();
				}
			}
			return buffer[pos++];
		}

		public int nextInt() {
			int len = 0;
			str[len++] = nextChar();
			len = reads(len, isSpace);
			int i = 0;
			int ret = 0;
			if (str[0] == '-') {
				i = 1;
			}
			for (; i < len; i++)
				ret = ret * 10 + str[i] - '0';
			if (str[0] == '-') {
				ret = -ret;
			}
			return ret;
		}

		public long nextLong() {
			int len = 0;
			str[len++] = nextChar();
			len = reads(len, isSpace);
			int i = 0;
			long ret = 0;
			if (str[0] == '-') {
				i = 1;
			}
			for (; i < len; i++)
				ret = ret * 10 + str[i] - '0';
			if (str[0] == '-') {
				ret = -ret;
			}
			return ret;
		}

		public char nextChar() {
			while (true) {
				final int c = read();
				if (!isSpace[c]) {
					return (char) c;
				}
			}
		}

		public String nextString() {
			return new String(nextChars());
		}

		public char[] nextChars() {
			int len = 0;
			str[len++] = nextChar();
			len = reads(len, isSpace);
			return Arrays.copyOf(str, len);
		}

		public char[][] next2DChars(int h, int w) {
			char[][] s = new char[h][w];
			for (int i = 0; i < h; i++) {
				s[i] = nextChars();
			}
			return s;
		}

		int reads(int len, boolean[] accept) {
			try {
				while (true) {
					final int c = read();
					if (accept[c]) {
						break;
					}
					if (str.length == len) {
						char[] rep = new char[str.length * 3 / 2];
						System.arraycopy(str, 0, rep, 0, str.length);
						str = rep;
					}
					str[len++] = (char) c;
				}
			} catch (MyInput.EndOfFileRuntimeException e) {
			}
			return len;
		}

		public int[] nextIntArray(final int n) {
			final int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt();
			}
			return res;
		}

		public int[] nextIntArray1Index(final int n) {
			final int[] res = new int[n + 1];
			for (int i = 1; i < n + 1; i++) {
				res[i] = nextInt();
			}
			return res;
		}

		public int[] nextIntArrayDec(final int n) {
			final int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt() - 1;
			}
			return res;
		}

		public long[] nextLongArray(final int n) {
			final long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong();
			}
			return res;
		}

		public long[] nextLongArray1Index(final int n) {
			final long[] res = new long[n + 1];
			for (int i = 1; i < n + 1; i++) {
				res[i] = nextLong();
			}
			return res;
		}

		public long[] nextLongArrayDec(final int n) {
			final long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong() - 1;
			}
			return res;
		}

		public double nextDouble() {
			return Double.parseDouble(nextString());
		}

		public double[] nextDoubleArray(int n) {
			double[] res = new double[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextDouble();
			}
			return res;
		}

		static class EndOfFileRuntimeException extends RuntimeException {
		}

	}

}
