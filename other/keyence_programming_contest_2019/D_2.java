package keyence_programming_contest_2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.TreeMap;

public class D_2 {

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
			int[] a = in.nextIntArray(n);
			int[] b = in.nextIntArray(m);

			int[] as = a.clone();
			int[] bs = a.clone();
			Arrays.sort(as);
			Arrays.sort(bs);

			TreeMap<Integer, Integer> amap = new TreeMap<>();
			TreeMap<Integer, Integer> bmap = new TreeMap<>();

			for (int i = 0; i < n; i++) {
				amap.put(a[i], i);
			}
			for (int i = 0; i < m; i++) {
				bmap.put(b[i], i);
			}

			long ans = 1;
			long[] ac = new long[n], bc = new long[m];
			boolean[][] used = new boolean[n][m];

			for (int i = n * m; i >=  1; i--) {
				if (amap.containsKey(i) && bmap.containsKey(i)) {
					ac[amap.get(i)]++;
					bc[bmap.get(i)]++;
					used[amap.get(i)][bmap.get(i)] = true;
					ac[amap.get(i)]++;
					bc[bmap.get(i)]++;

				} else if (amap.containsKey(i)) {
					long cnt = 0;
					for (int k = 0; k < m; k++) {
						if (i <= b[k] && !used[amap.get(i)][k]) {
							cnt++;
						}
					}

					for (int k = 0; k < m; k++) {
						if (i <= b[k] && !used[amap.get(i)][k]) {
							used[amap.get(i)][k] = true;
							break;
						}
					}
					ans *= cnt;
					ac[amap.get(i)]++;
					ans %= MOD;
				} else if (bmap.containsKey(i)) {
					long cnt = 0;
					for (int k = 0; k < n; k++) {
						if (i <= a[k] && !used[k][bmap.get(i)]) {
							cnt++;
						}
					}

					for (int k = 0; k < n; k++) {
						if (i <= a[k] && !used[k][bmap.get(i)]) {
							used[k][bmap.get(i)] = true;
							break;
						}
					}

					ans *= cnt;
					bc[bmap.get(i)]++;
					ans %= MOD;
				} else {
					long cnt = 0;
					for (int j = 0; j < n; j++) {
						for (int k = 0; k < m; k++) {
							if (i <= a[j] && i <= b[k] && !used[j][k]) {
								cnt++;
							}
						}
					}

					top:
					for (int j = 0; j < n; j++) {
						for (int k = 0; k < m; k++) {
							if (i <= a[j] && i <= b[k] && !used[j][k]) {
								used[j][k] = true;
								break top;
							}
						}
					}
					ans *= cnt;
					ans %= MOD;
				}
			}

			out.println(ans);

		}
	}

	public static int upperBound(int[] a, int obj) {
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

	public static int lowerBound(int[] a, int obj) {
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
