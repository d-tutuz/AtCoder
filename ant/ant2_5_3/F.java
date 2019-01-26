package ant2_5_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class F {

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
		public void solve(int testNumber, MyInput in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt();
			T[] t = new T[m];
			for (int i = 0; i < m; i++) {
				int u = in.nextInt(), v = in.nextInt(), w = in.nextInt();
				t[i] = new T(u, v, w);
			}
			Arrays.sort(t);

			int q = in.nextInt();
			UnionFind uf = new UnionFind(n);
			while (q-- > 0) {

				int a = in.nextInt(), b = in.nextInt();
				uf.union(a, b);
				ArrayList<Integer> vList = uf.getGroupList(a);
				UnionFind tmp = new UnionFind(n);
				int cost = 0, num = 0;
				for (int i = 0; i < m; i++) {
					if (uf.same(t[i].u, t[i].v) && uf.same(a, t[i].u)) {
						if (!tmp.same(t[i].u, t[i].v)) {
							tmp.union(t[i].u, t[i].v);
							cost += t[i].cost;
							num++;
							if (num == vList.size()-1) {
								break;
							}
						}
					}
				}

				out.println(num == vList.size()-1 ? Integer.toString(cost) : "IMPOSSIBLE");
			}

		}

		class T implements Comparable<T> {
			int u, v ,cost;

			public T(int s, int t, int cost) {
				super();
				this.u = s;
				this.v = t;
				this.cost = cost;
			}

			@Override
			public String toString() {
				return "T [s=" + u + ", t=" + v + ", cost=" + cost + "]";
			}

			@Override
			public int compareTo(T o) {
				return Integer.compare(this.cost, o.cost);
			}
		}
	}

	static class UnionFind {
		int[] data;
		int[] weight;

		public UnionFind(int size) {
			data = new int[size];
			weight = new int[size];
			clear();
		}

		public void clear() {
			Arrays.fill(data, -1);
		}

		public int root(int x) {
			return data[x] < 0 ? x : (data[x] = root(data[x]));
		}

		public void union(int x, int y) {
			x = root(x);
			y = root(y);

			if (x != y) {
				if (data[y] > data[x]) {
					final int t = x;
					x = y;
					y = t;
				}

				data[x] += data[y];
				data[y] = x;

				weight[x] += weight[y];
			}
		}

		public ArrayList<Integer> getGroupList(int x) {
			ArrayList<Integer> ret = new ArrayList<>();
			for (int i = 0; i < data.length; i++) {
				if (root(i) == root(x)) ret.add(i);
			}
			return ret;
		}

		boolean same(int x, int y) {
			return root(x) == root(y);
		}

		public int size(int x) {
			return -data[root(x)];
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
