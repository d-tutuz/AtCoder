package keyence_programming_contest_2019;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, MyInput in, PrintWriter out) {

			int n = in.nextInt();
			long d = in.nextLong();
			P[] ps = new P[n];
			for (int i = 0; i < n; i++) {
				long a = in.nextLong();
				ps[i] = new P(i, a);
			}
			RMQ left = new RMQ(n, new P(-1, LINF)), right = new RMQ(n, new P(-1, LINF));
			for (int i = 0; i < n; i++) {
				left.update(i, new P(i, ps[i].cost - d * i));
				right.update(i, new P(i, ps[i].cost + d * i));
			}
			P[] p = ps.clone();
			Arrays.sort(ps);

			List<Edge> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				int idx = ps[i].idx;
				P le = left.query(0, idx);
				P ri = right.query(idx+1, n);
				if (le.idx != -1) {
					list.add(new Edge(idx, le.idx, abs(idx - le.idx) * d + p[idx].cost + p[le.idx].cost));
				}
				if (ri.idx != -1) {
					list.add(new Edge(idx, ri.idx, abs(idx - ri.idx) * d + p[idx].cost + p[ri.idx].cost));
				}
				left.update(idx, new P(idx, LINF));
				right.update(idx, new P(idx, LINF));
			}

			Collections.sort(list);

			long ans = 0;

			UnionFind uf = new UnionFind(n);
			for (Edge e : list) {
				if (uf.same(e.s, e.t)) continue;
				uf.union(e.s, e.t);
				ans += e.cost;
			}

			out.println(ans);
		}

		class Edge implements Comparable<Edge> {
			int s, t;
			long cost;

			public Edge(int s, int t, long cost) {
				super();
				this.s = s;
				this.t = t;
				this.cost = cost;
			}

			@Override
			public String toString() {
				return "Edge [s=" + s + ", t=" + t + ", cost=" + cost + "]";
			}

			@Override
			public int compareTo(Edge o) {
				return Long.compare(this.cost, o.cost);
			}
		}

		class RMQ extends AbstractRMQ<P> {

			public RMQ(int size, P initial_value) {
				super(size, initial_value);
			}

			@Override
			P merge(P x, P y) {
				return x.cost <= y.cost ? x : y;
			}
		}

		class P implements Comparable<P> {
			int idx;
			long cost;

			public P(int idx, long cost) {
				super();
				this.idx = idx;
				this.cost = cost;
			}

			@Override
			public int compareTo(P o) {
				return -Long.compare(this.cost, o.cost);
			}

			@Override
			public String toString() {
				return "P [idx=" + idx + ", cost=" + cost + "]";
			}
		}

		class UnionFind {
			int[] data;

			public UnionFind(int size) {
				data = new int[size];
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
				}
			}

			boolean same(int x, int y) {
				return root(x) == root(y);
			}

			public int size(int x) {
				return -data[root(x)];
			}
		}

	}

	@SuppressWarnings("unchecked")
	static abstract class AbstractRMQ<T> {
		int size;
		T[] dat;
		T INITIAL_VALUE;

		abstract T merge(T x, T y);

		public AbstractRMQ(int n, T initial_value) {
			size = 1;
			this.INITIAL_VALUE = initial_value;
			while (size < n) {
				size *= 2;
			}
			dat = (T[])new Object[size * 2];
			for (int i = 0; i < size * 2; i++) {
				dat[i] = INITIAL_VALUE;
			}
		}

		// k 番目(0-indexed) を a に更新
		void update(int k, T a) {
			k += size;
			dat[k] = a;
			while (k > 0) {
				k /= 2;
				dat[k] = merge(dat[2 * k], dat[2 * k + 1]);
			}
		}

		// [a, b) の最小値を求める
		private T query(int a, int b, int k, int l, int r) {
			if (r <= a || b <= l) return INITIAL_VALUE;

			if (a <= l && r <= b) {
				return dat[k];
			} else {
				T vl = query(a, b, 2 * k, l, (l + r) / 2);
				T vr = query(a, b, 2 * k + 1, (l + r) / 2, r);
				return merge(vl, vr);
			}
		}

		// [a, b) の最小値を求める
		T query(int a, int b) {
			return query(a, b, 1, 0, size);
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
