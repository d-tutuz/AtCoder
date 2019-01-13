package aisingprogrammingcontest2019;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class D {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		inputStream = new FileInputStream(new File("/workspace/Atcoder/other/aisingprogrammingcontest2019/D_input1.txt"));
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

		int n, q;
		long[] a, b, takasm;
		public void solve(int testNumber, MyInput in, PrintWriter out) {

			n = in.nextInt(); q = in.nextInt();
			a = in.nextLongArray(n);
			b = a.clone();
			takasm = new long[n];
			Arrays.parallelPrefix(b, Math::addExact);

			// 交互にとる数は n の数に依存する
			for (int i = 0; i < n; i++) {
				if (n % 2 == 1 && i % 2 == 0) takasm[i] = a[i];
				if (n % 2 == 0 && i % 2 == 1) takasm[i] = a[i];
			}
			Arrays.parallelPrefix(takasm, Math::addExact);

			while (q-- > 0) {
				int x = in.nextInt();

				// 二分探索で条件を満たす青木の範囲を求める
				int ng = -1, ok = 10;
				P p = calc(ng, ok, x);

				// 1つ重なっているときに右側を調整
				int L = p.l, R = p.r;
			    int taka = n - 1 - R;
			    int aoki = R - L + 1;
			    if (taka != aoki) {
			        R--;
			        aoki--;
			    }

				long res = get(R + 1, n - 1);
				if (L > 0) res += takasm[L-1];
				out.println(res);

			}
		}

		P calc(int ng, int ok, int x) {

			while (ok - ng > 1) {
				int k = (ng + ok) / 2;
				P p = f(x, k);
				if (p.l > p.r) {
					ng = k;
					continue;
				}

				int len = p.r - p.l + 1;

				/*
				 * 青木のとる範囲を [La, Ra] とすると高橋の範囲 [Lt, Rt] について
				 * n-1 <= Rt = Ra + len であれば条件を満たす高橋のとる範囲となる
				 * */
				if (n - 1 <= p.r + len) {
					ok = k;
				} else {
					ng = k;
				}
			}

			return f(x, ok);
		}

		// [x-len, x+len] なる index l, r を求める
		P f(int x, int len) {
			int l = lowerBound(a, x - len);     // x+len 以上の最小のidx
			int r = upperBound(a, x + len) - 1; // x+len 以下の最大のidx
			return new P(l, r);
		}

		long get(int l, int r) {
			return b[r] - (l > 0 ? b[l-1] : 0);
		}
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

	static class P {
		int l, r;

		public P(int l, int r) {
			super();
			this.l = l;
			this.r = r;
		}

		@Override
		public String toString() {
			return "P [l=" + l + ", r=" + r + "]";
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
