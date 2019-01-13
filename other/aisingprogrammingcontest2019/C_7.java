package aisingprogrammingcontest2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C_7 {

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
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		int h, w;
		char[][] s;
		final int B = 0, W = 1;
		public void solve(int testNumber, MyInput in, PrintWriter out) {

			h = in.nextInt(); w = in.nextInt();
			s = new char[h][w];

			DisjointSet set = new DisjointSet(h * w);
			List<P> list = new ArrayList<>();
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					s[i][j] = in.nextChar();
					if (s[i][j] == '#') list.add(new P(i, j));
					for (int k = 0; k < 4; k++) {
						int mh = i + mh4[k];
						int mw = j + mw4[k];
						if (isInside(mh, mw) && ((s[i][j] == '#' && s[mh][mw] == '.') || (s[i][j] == '.' && s[mh][mw] == '#'))) {
							set.unite(f(i, j), f(mh, mw));
						}
					}
				}
			}

			long ans = 0;
			long[] memo = new long[h * w];
			Arrays.fill(memo, -1);
			boolean[][] used = new boolean[h][w];
			for (P p : list) {
				int root = set.root(f(p.h, p.w));
				if (memo[root] != -1) {
					ans += memo[root];
					continue;
				}
				ans += memo[root] = dfs(p.h, p.w, used, B);
			}

			out.println(ans);
		}

		int dfs(int nh, int nw, boolean[][] used, int stat) {

			used[nh][nw] = true;

			int ret = 0;
			for (int i = 0; i < 4; i++) {
				int mh = nh + mh4[i];
				int mw = nw + mw4[i];
				if (isInside(mh, mw) && !used[mh][mw]) {
					if (stat == B && s[mh][mw] == '.') {
						ret += dfs(mh, mw, used, W) + 1;
					}
					if (stat == W && s[mh][mw] == '#') {
						ret += dfs(mh, mw, used, B);
					}
				}
			}

			return ret;
		}

		int f(int i, int j) {
			return i * w + j;
		}

		boolean isInside(int i, int j) {
			return 0 <= i && i < h && 0 <= j && j < w;
		}
	}

	public static class DisjointSet {

		int[] p, rank, cnt;

		public DisjointSet(int size) {
			p = new int[size];
			rank = new int[size];
			cnt = new int[size];

			for (int j = 0; j < size; j++) {
				makeSet(j);
			}
		}

		private void makeSet(int x) {
			p[x] = x;
			rank[x] = 0;
			cnt[x] = 1;
		}

		public int root(int x) {
			return p[x] == x ? x : root(p[x]);
		}

		private void link(int x, int y) {
			if (rank[x] > rank[y]) {
				p[y] = x;
			} else if (rank[x] < rank[y]) {
				p[x] = y;
			} else if (rank[x] == rank[y]) {
				p[x] = y;
				rank[y]++;
			}

			if (x != y) {
				cnt[x] = cnt[y] += cnt[x];
			}
		}

		public void unite(int x, int y) {
			link(root(x), root(y));
		}

		public boolean same(int x, int y) {
			return root(x) == root(y);
		}

		public int getSize(int x) {
			return cnt[root(x)];
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
