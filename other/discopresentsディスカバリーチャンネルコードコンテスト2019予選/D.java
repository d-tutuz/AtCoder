package discopresentsディスカバリーチャンネルコードコンテスト2019予選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

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

		public void solve(int testNumber, MyInput in, PrintWriter out) {

			long[] a = new long[29];
			for (int i = 0; i < 29; i++) {
				a[i] = in.nextLong();
			}

			long xx = a[0], yy = 1;
			for (int i = 0; i < 29; i++) {
				long[] res = crt(xx, yy, a[i], i+1);
				if (res[1] == -1) {
					out.println("invalid");
					return;
				}
				xx = res[0];
				yy = res[1];
			}

			if (xx > 1e12) {
				out.println("invalid");
				return;
			}

			for (int i = 2; i <= 30; i++) {
				if (digitSum(xx, i) != a[i-2]) {
					out.println("invalid");
					return;
				}
			}

			out.println(xx);

		}

		// 負の数にも対応した mod
		long mod(long a, long m) {
			return (a % m + m) % m;
		}

		// 拡張 Euclid の互除法
		// ap + bq = gcd(a, b) となる (p, q) を求め、d = gcd(a, b) をリターンします
		//
		// long[] pq = new long[3];
		// pq[0] = d, pq[1] = p, pq[2] = q;
		long extGcd(long a, long b, long[] pq) {
			if (b == 0) {
				pq[1] = 1;
				pq[2] = 0;
				return a;
			}
			long d = extGcd(b, a % b, pq);
			long tmp = pq[2];
			pq[2] = pq[1] - a/b * tmp;
			pq[1] = tmp;
			return d;
		}


		// 中国剰余定理
		// リターン値を (r, m) とすると解は x ≡ r (mod. m)
		// 解なしの場合は (0, -1) をリターン
		long[] crt(long b1, long m1, long b2, long m2) {
			long[] pq = new long[3];
			long d = extGcd(m1, m2, pq);
			if ((b2 - b1) % d != 0) {
				return new long[]{0, -1};
			}
			long m = m1 * (m2/d);
			long tmp = (b2 - b1) / d * pq[1] % (m2/d);
			long r = mod(b1 + m1 * tmp, m);
			pq[1] = r;
			pq[2] = m;
			return new long[]{r, m};
		}

		public long digitSum(long n, int base) {
			long ret = 0;
			while (n > 0) {
				ret += n % base;
				n /= base;
			}
			return ret;
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
