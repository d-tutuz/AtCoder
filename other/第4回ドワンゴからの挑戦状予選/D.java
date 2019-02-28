package 第4回ドワンゴからの挑戦状予選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class D {

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

		ArrayList<Integer>[] g, rev;
		int n;
		long[] x, sum;
		int[] a, depth;
		@SuppressWarnings("unchecked")
		public void solve(int testNumber, MyInput in, PrintWriter out) {

			n = in.nextInt();
			x = in.nextLongArray(n);
			sum = new long[n];
			a = new int[n];
			depth = new int[n];
			g = new ArrayList[n];
			g = Stream.generate(ArrayList::new).limit(n).toArray(ArrayList[]::new);
			for (int i = 1; i < n; i++) {
				a[i] = in.nextInt()-1;
				g[a[i]].add(i);
			}

			// 根の深さを求める(bfs)
//			int maxDepth = -1;
//			Queue<P> q = new ArrayDeque<>();
//			q.add(new P(0, 0));
//			while (!q.isEmpty()) {
//				P p = q.remove();
//				depth[p.cur] = p.d;
//				maxDepth = Math.max(maxDepth, depth[p.cur]);
//				for (int nex : g[p.cur]) {
//					q.add(new P(nex, p.d + 1));
//				}
//			}

			// 各頂点 i について直後の連結成分のみの和を求める
			long max = 0;
			for (int i = 0; i < n; i++) {
				max = Math.max(max, bfs(i) + (i != 0 ? x[0] : 0));
			}

			out.println(max);
		}

		long bfs(int cur) {
			long ret = x[cur];
			for (int nex : g[cur]) {
				ret += x[nex];
			}
			return ret;
		}

//		long dfs(int cur) {
//
//			long ret = x[cur];
//			for (int nex : g[cur]) {
//				ret += dfs(nex);
//			}
//			return sum[cur] = ret;
//		}

		class P {
			int cur, d;

			public P(int cur, int d) {
				super();
				this.cur = cur;
				this.d = d;
			}

		}
	}

	static void printArrayLine(long[] a, PrintWriter out) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				out.print(a[i]);
			} else {
				out.print(" " + a[i]);
			}
		}
		out.print("\n");
	}

	static void printArrayLine(int[] a, PrintWriter out) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				out.print(a[i]);
			} else {
				out.print(" " + a[i]);
			}
		}
		out.print("\n");
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
