package aisingprogrammingcontest2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class Main2 {

	public static void main(String[] args) throws IOException {
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

			int n = in.nextInt(), q = in.nextInt();
			long[] a = in.nextLongArray(n);
			long[] sum = new long[n], odd = new long[n], even = new long[n];
			for (int i = 0; i < n; i++) {
				sum[i] = a[i];
				if (i % 2 == 0) {
					even[i] = a[i];
				} else {
					odd[i] = a[i];
				}
			}
			Arrays.parallelPrefix(sum, Math::addExact);
			Arrays.parallelPrefix(odd, Math::addExact);
			Arrays.parallelPrefix(even, Math::addExact);


			while (q-- > 0) {
				long x = in.nextLong();
				long ng = INF, ok = 0;
				while (ng - ok > 1) {
					long mid = (ng + ok) / 2;
					int ri_1 = upperBound(a, x + mid) - 1;
					int li_1 = lowerBound(a, x - mid);
					int len = ri_1 - li_1 + 1;
					int li_2 = n - len;
					if (li_2 - ri_1 >= 0) {
						ok = mid;
					} else {
						ng = mid;
					}
				}

				long mid = ok;
				int ri_1 = upperBound(a, x + mid) - 1;
				int li_1 = lowerBound(a, x - mid);
				int len = ri_1 - li_1 + 1;
				int li_2 = n - len;

				long ans = 0;
				if (li_2 - ri_1 == 1) {
					ans += sum[n-1] - sum[li_2 - 1];
					if (n % 2 == 0) {
						ans += li_1 - 1 >= 0 ? odd[li_1 - 1] : 0;
					} else {
						ans += li_1 - 1 >= 0 ? even[li_1 - 1] : 0;
					}
//				} else if (li_2 - ri_1 == 0) {
				} else {
					ans += sum[n-1] - sum[li_2 - 1];
					if (n % 2 == 0) {
						ans += li_1 - 1 >= 0 ? odd[li_1 - 1] : 0;
					} else {
						ans += li_1 - 1 >= 0 ? even[li_1 - 1] : 0;
					}
				}

				out.println(ans);
			}
		}
	}

	public static int upperBound(long[] a, long obj) {
		int l = 0, r = a.length - 1;
		while (r - l >= 0) {
			int c = (l + r) / 2;
			if (a[c] <= obj) {
				l = c + 1;
			} else {
				r = c - 1;
			}
		}
		return l;
	}

	public static int lowerBound(long[] a, long obj) {
		int l = 0, r = a.length - 1;
		while (r - l >= 0) {
			int c = (l + r) / 2;
			if (obj <= a[c]) {
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