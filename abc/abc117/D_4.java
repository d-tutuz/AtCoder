package abc117;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D_4 {

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

		int thr = 50;
		public void solve(int testNumber, MyInput in, PrintWriter out) {

			int n = in.nextInt();
			long k = in.nextLong();
			long[] a = in.nextLongArray(n);
			Arrays.sort(a);

			long[] bit0 = new long[thr], bit1 = new long[thr];

			for (int i = 0; i < n; i++) {
				for (int j = thr-1; j >= 0; j--) {
					if ((a[i] >> j & 1) == 1) {
						bit1[thr-1-j]++;
					} else {
						bit0[thr-1-j]++;
					}
				}
			}

			int hibit = -1;
			boolean[] can = new boolean[thr];
			for (int i = thr-1; i >= 0; i--) {
				if ((k >> i & 1) == 1) {
					can[thr-1-i] = true;
					hibit = Math.max(hibit, i);
				}
			}

			int numhibit = -1;
			for (int i = thr-1; i >= 0; i--) {
				for (int j = 0; j < n; j++) {
					if ((a[j] >> i & 1) == 1) {
						numhibit = Math.max(numhibit, i);
					}
				}
			}

			long ans = 0;

			// 最大値のbit
			for (int i = numhibit; i > hibit; i--) {
				ans += (1L << i) * bit1[thr-1-i];
			}
			for (int i = hibit; i >= 0; i--) {
				if (can[thr-1-i]) {
					long c = Math.max(bit0[thr-1-i], bit1[thr-1-i]);
					ans += (1L << i) * c;
				} else {
					ans += (1L << i) * bit1[thr-1-i];
				}
			}

			// 最大値のbit未満の任意の数
			long tmp = 0;
			for (int i = numhibit; i > hibit; i--) {
				tmp += (1L << i) * bit1[thr-1-i];
			}
			List<Long> list = new ArrayList<>();
			for (int i = hibit; i >= 0; i--) {
				if (can[thr-1-i]) {
					long kk = k;
					long t = (1L << i) - 1;
					kk -= 1L << i;
					kk |= t;
					list.add(kk);
				}
			}
			for (long l : list) {
				long tmp2 = tmp;
				for (int i = hibit; i >= 0; i--) {
					if ((l >> i & 1) == 1) {
						long c = Math.max(bit0[thr-1-i], bit1[thr-1-i]);
						tmp2 += (1L << i) * c;
					} else {
						tmp2 += (1L << i) * bit1[thr-1-i];
					}
				}
				ans = Math.max(ans, tmp2);
			}

			ans = Math.max(ans, tmp);

			out.printf("%d\n" ,ans);
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
