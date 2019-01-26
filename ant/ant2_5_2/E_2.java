package ant2_5_2;

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

public class E_2 {

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

		int n, m;
		@SuppressWarnings("unchecked")
		public void solve(int testNumber, MyInput in, PrintWriter out) {

			n = in.nextInt(); m =in.nextInt();
			List<P>[] g = new ArrayList[n], h = new ArrayList[n];;
			g = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);
			h = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			for (int i = 0; i < m; i++) {
				int f = in.nextInt(), t = in.nextInt(), c = in.nextInt();
				if (f > t) {
					int tmp = f;
					f = t;
					t = tmp;
				}
				g[f].add(new P(t, c));
				h[t].add(new P(f, c));
			}

			long[][] cost = new long[n][28];
			fill(cost, INF);
			cost[0][0] = 0;

			PriorityQueue<T> pq = new PriorityQueue<>();
			pq.add(new T(0, 0, cost[0][0]));

			while (!pq.isEmpty()) {
				T p = pq.remove();

				// 順方向
				for (P pp : g[p.n]) {
					if (cost[p.n][p.time] + pp.c < cost[pp.t][(p.time + pp.c) % 4]) {
						cost[pp.t][(p.time + pp.c) % 4] = cost[p.n][p.time] + pp.c;
						pq.add(new T(pp.t, (p.time + pp.c) % 4, cost[pp.t][(p.time + pp.c) % 4]));
					}
				}

				// 逆方向
				for (P pp : h[p.n]) {
					if (cost[p.n][p.time] + pp.c < cost[pp.t][(p.time + pp.c) % 4]) {
						cost[pp.t][(p.time + pp.c) % 4] = cost[p.n][p.time] + pp.c;
						pq.add(new T(pp.t, (p.time + pp.c) % 4, cost[pp.t][(p.time + pp.c) % 4]));
					}
				}
			}


			long[][] cost2 = new long[n][28];
			fill(cost2, INF);
			cost2[0][0] = 0;

			pq.clear();
			pq.add(new T(0, 0, cost2[0][0]));

			while (!pq.isEmpty()) {
				T p = pq.remove();

				// 順方向
				for (P pp : g[p.n]) {

					if (cost2[p.n][p.time] + pp.c < cost2[pp.t][(p.time + pp.c) % 7]) {
						cost2[pp.t][(p.time + pp.c) % 7] = cost2[p.n][p.time] + pp.c;
						pq.add(new T(pp.t, (p.time + pp.c) % 7, cost2[pp.t][(p.time + pp.c) % 7]));
					}
				}

				// 逆方向
				for (P pp : h[p.n]) {
					if (cost2[p.n][p.time] + pp.c < cost2[pp.t][(p.time + pp.c) % 7]) {
						cost2[pp.t][(p.time + pp.c) % 7] = cost2[p.n][p.time] + pp.c;
						pq.add(new T(pp.t, (p.time + pp.c) % 7, cost2[pp.t][(p.time + pp.c) % 7]));
					}
				}
			}

			out.println(Math.min(cost[n-1][0], cost2[n-1][0]));
		}

		class T implements Comparable<T> {
			int n, time;
			long cost;

			public T(int n, int time, long cost) {
				super();
				this.n = n;
				this.time = time;
				this.cost = cost;
			}

			@Override
			public int compareTo(T o) {
				return Long.compare(this.cost, o.cost);
			}

			@Override
			public String toString() {
				return "T [n=" + n + ", time=" + time + ", cost=" + cost + "]";
			}

		}

		class P {
			int t, c;

			public P(int t, int c) {
				super();
				this.t = t;
				this.c = c;
			}

			@Override
			public String toString() {
				return "P [t=" + t + ", c=" + c + "]";
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

	static void fill(int[][] a, int v) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				a[i][j] = v;
			}
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

