package abc118;

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
import java.util.TreeMap;

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

		public void solve(int testNumber, MyInput in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt();
			int[] a = in.nextIntArray(m);

			Arrays.sort(a);

			P[] p = new P[10];
			p[1] = new P(0, INF);
			p[1] = new P(1, 2);
			p[2] = new P(2, 5);
			p[3] = new P(3, 5);
			p[4] = new P(4, 4);
			p[5] = new P(5, 5);
			p[6] = new P(6, 6);
			p[7] = new P(7, 3);
			p[8] = new P(8, 7);
			p[9] = new P(9, 6);

			List<P> use = new ArrayList<>();
			boolean[] can = new boolean[10];
			for (int l : a) {
				use.add(p[l]);
				can[l] = true;
			}

			Collections.sort(use);

			// 上から i 桁目
			int idx = 0;
			TreeMap<Integer, Integer> map = new TreeMap<>();
			for (int i = 0; i < m; i++) {
				while (n >= use.get(i).cnt) {
					map.put(idx, use.get(i).num);
					idx++;
					n -= use.get(i).cnt;
				}
			}

			// 上から辞書順最大
			for (int number = 9; number >= 1; number--) {
				if (!can[number]) continue;
				for (int i = 0; i < idx; i++) {
					if (n + p[map.get(i)].cnt >= p[number].cnt && map.get(i) < number) {
						n += p[map.get(i)].cnt;
						map.put(i, number);
						n -= p[number].cnt;
					}
				}
			}

			// 下から i 桁目で実現できる個数
			boolean[][] dp = new boolean[idx+1][(idx+1) * 6 + 1];
			dp[0][0] = true;
			for (int i = 0; i < idx; i++) {
				for (int number = 1; number <= 9; number++) {
					if (!can[number]) continue;
				}
			}

			StringBuilder sb = new StringBuilder();
			for (int keta : map.keySet()) {
				sb.append(map.get(keta));
			}

			out.println(sb.toString());
		}

		class P implements Comparable<P> {
			int num, cnt;

			public P(int num, int cnt) {
				super();
				this.num = num;
				this.cnt = cnt;
			}

			@Override
			public String toString() {
				return "P [num=" + num + ", cnt=" + cnt + "]";
			}

			@Override
			public int compareTo(P o) {
				int c1 = Integer.compare(this.cnt, o.cnt);
				int c2 = -Integer.compare(this.num, o.num);
				return c1 == 0 ? c2 : c1;
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
