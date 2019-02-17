package p400;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class A {

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

			int n = in.nextInt(), k = in.nextInt();
			long[] b = in.nextLongArray(n);

			if (k == 1) {
				long ret = 0;
				for (long l : b) if (l >= 0) ret += l;
				out.println(ret);
				return;
			}

			SegmentTree seg = new SegmentTree(n, -LINF);

			// [0, i] の最大値
			long[] dp = new long[n];
			Arrays.fill(dp, -LINF);
			dp[0] = b[0];
			seg.update(0, dp[0]);

			for (int i = 1; i < n; i++) {

				// [0, i] 全体を 0 にする
				if (i - k + 1 >= 0) dp[i] = 0;

				// [j, i] を 0 にする
				if (i - k >= 0) dp[i] = Math.max(dp[i], seg.query(0, i - k + 1));

				// b[i] を足す
				dp[i] = Math.max(dp[i], dp[i-1] + b[i]);

				seg.update(i, dp[i]);
			}

			out.println(Math.max(0, dp[n-1]));

		}

		class SegmentTree extends AbstractSegmentTree<Long> {

			public SegmentTree(int n, Long initial_value) {
				super(n, initial_value);
			}

			@Override
			Long merge(Long x, Long y) {
				return Math.max(x, y);
			}

		}

		@SuppressWarnings("unchecked")
		abstract class AbstractSegmentTree<T> {
			int size;
			T[] dat;
			T INITIAL_VALUE;

			abstract T merge(T x, T y);

			public AbstractSegmentTree(int n, T initial_value) {
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

			void update(int k, T a) {
				k += size;
				dat[k] = a;
				while (k > 0) {
					k /= 2;
					dat[k] = merge(dat[2 * k], dat[2 * k + 1]);
				}
			}

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

			T query(int a, int b) {
				return query(a, b, 1, 0, size);
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
