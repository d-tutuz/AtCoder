package aisingprogrammingcontest2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class C_4 {

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
//		Set<T> set = new HashSet<>();
		public void solve(int testNumber, MyInput in, PrintWriter out) {

			h = in.nextInt(); w = in.nextInt();
			List<P> list = new ArrayList<>();
			s = new char[h][w];
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					s[i][j] = in.nextChar();
					if (s[i][j] == '#') list.add(new P(i, j));
				}
			}

			DisjointSet disjointset = new DisjointSet(500000);
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					for (int k = 0; k < 4; k++) {
						int mh = i + mh4[k];
						int mw = j + mw4[k];
						if (0 <= mh && mh < h && 0 <= mw && mw < w) {
							if ((s[i][j] == '#' && s[mh][mw] == '.') || (s[i][j] == '.' && s[mh][mw] == '#')) {
								disjointset.unite(f(i, j), f(mh, mw));
							}
						}
					}
				}
			}

			Collections.sort(list);

			long ans = 0;
			int[] memo = new int[500000];
			for (int i = 0; i < list.size(); i++) {
				boolean done = false;
				for (int j = 0; j < i; j++) {
					if (disjointset.same(f(list.get(i).h, list.get(i).w), f(list.get(j).h, list.get(j).w))) done = true;
				}
				if (!done) {
					int ret = dfs(list.get(i).h, list.get(i).w, new boolean[h][w], B, list.get(i).h, list.get(i).w, new HashSet<>());
					memo[disjointset.root(f(list.get(i).h, list.get(i).w))] = ret;
					ans += memo[disjointset.root(f(list.get(i).h, list.get(i).w))];
				} else {
					ans += memo[disjointset.root(f(list.get(i).h, list.get(i).w))];
				}
			}

//			for (P p : list) {
//				ans += dfs(p.h, p.w, new boolean[h][w], B, p.h, p.w, new HashSet<>());
//			}
			out.println(ans);

		}

		int f(int h, int w) {
			return h * 1000 + w;
		}

		int dfs(int nh, int nw, boolean[][] used, int stat, int sh, int sw, Set<T> set) {

			used[nh][nw] = true;

			for (int i = 0; i < 4; i++) {
				int mh = nh + mh4[i];
				int mw = nw + mw4[i];
				if (0 <= mh && mh < h && 0 <= mw && mw < w) {
					if (stat == B && s[mh][mw] == '.' && !used[mh][mw]) {
						dfs(mh, mw, used, W, sh, sw, set);
					}
					if (stat == W && s[mh][mw] == '#' && !used[mh][mw]) {
						dfs(mh, mw, used, B, sh, sw, set);
					}
				}
			}
			if (stat == W) set.add(new T(nh, nw, sh, sw));
			return set.size();
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

	static class T {
		int th, tw, sh, sw;

		public T(int th, int tw, int sh, int sw) {
			super();
			this.th = th;
			this.tw = tw;
			this.sh = sh;
			this.sw = sw;
		}

		@Override
		public String toString() {
			return "T [th=" + th + ", tw=" + tw + ", sh=" + sh + ", sw=" + sw
					+ "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + sh;
			result = prime * result + sw;
			result = prime * result + th;
			result = prime * result + tw;
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
			T other = (T) obj;
			if (sh != other.sh)
				return false;
			if (sw != other.sw)
				return false;
			if (th != other.th)
				return false;
			if (tw != other.tw)
				return false;
			return true;
		}

	}

	static class P implements Comparable<P> {
		int h, w;

		public P(int h, int w) {
			super();
			this.h = h;
			this.w = w;
		}

		@Override
		public int compareTo(P o) {
			return Integer.compare(this.h * this.w + this.w, o.h * o.w + o.w);
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
