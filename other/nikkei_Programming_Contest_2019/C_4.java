package nikkei_Programming_Contest_2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;

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

		public void solve(int testNumber, MyInput in, PrintWriter out) {

			int n = in.nextInt();
			T[] t = new T[n];
			TreeMap<T, Integer> ma = new TreeMap<>((t1, t2) -> (t1.a - t2.a == 0 ? -(t1.b - t2.b) : -(t1.a - t2.a)));
			TreeMap<T, Integer> mb = new TreeMap<>((t1, t2) -> (t1.b - t2.b == 0 ? -(t1.a - t2.a) : -(t1.b - t2.b)));

			long taka = 0, ao = 0;
			for (int i = 0; i < n; i++) {
				int a = in.nextInt(), b = in.nextInt();
				T tt = new T(i, a, b);
				ma.merge(tt, 1, Integer::sum);
				mb.merge(tt, 1, Integer::sum);
				taka += a;
				ao += b;
			}

			for (int i = 0; i < n; i++) {
				if (i % 2 == 0) {
					Entry<T, Integer> e = mb.pollFirstEntry();
					if (e.getValue() - 1 > 0) {
						mb.put(new T(e.getKey().idx, e.getKey().a, e.getKey().b), e.getValue() - 1);
					}
 					ao -= e.getKey().b;
					ma.merge(new T(e.getKey().idx, e.getKey().a, e.getKey().b), -1, Integer::sum);
					if (ma.get(new T(e.getKey().idx, e.getKey().a, e.getKey().b)) <= 0) {
						ma.remove(new T(e.getKey().idx, e.getKey().a, e.getKey().b));
					}
				} else {
					Entry<T, Integer> e = ma.pollFirstEntry();
					if (e.getValue() - 1 > 0) {
						ma.put(new T(e.getKey().idx, e.getKey().a, e.getKey().b), e.getValue() - 1);
					}
					taka -= e.getKey().a;
					mb.merge(new T(e.getKey().idx, e.getKey().a, e.getKey().b), -1, Integer::sum);
					if (mb.get(new T(e.getKey().idx, e.getKey().a, e.getKey().b)) <= 0) {
						mb.remove(new T(e.getKey().idx, e.getKey().a, e.getKey().b));
					}
				}
			}

			out.println(taka - ao);
		}

		class T {
			int idx, a, b;
			@Override
			public String toString() {
				return "T [idx=" + idx + ", a=" + a + ", b=" + b + "]";
			}
			public T(int idx, int a, int b) {
				super();
				this.idx = idx;
				this.a = a;
				this.b = b;
			}
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + getOuterType().hashCode();
				result = prime * result + a;
				result = prime * result + b;
				result = prime * result + idx;
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
				if (!getOuterType().equals(other.getOuterType()))
					return false;
				if (a != other.a)
					return false;
				if (b != other.b)
					return false;
				if (idx != other.idx)
					return false;
				return true;
			}
			private TaskX getOuterType() {
				return TaskX.this;
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
