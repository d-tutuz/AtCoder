package sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Diff {

	public static void main(String[] args) throws IOException {
		TaskX solver = new TaskX();
		OutputStream outputStream = new FileOutputStream(new File("Diff.txt"));
		PrintWriter out = new PrintWriter(outputStream);
		solver.makeTest(out);

		InputStream inputStream = System.in;
		inputStream = new FileInputStream(new File("Diff.txt"));
		MyInput in = new MyInput(inputStream);
		outputStream = System.out;
		out = new PrintWriter(outputStream);
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

		public void makeTest(PrintWriter out) {

			Random rnd = new Random();

			int n = 10000; //, q = 1;
			out.write(String.valueOf(n));
			out.write(" ");
			out.write(String.valueOf("1"));
			out.write("\n");

			Set<Integer> set = new HashSet<>();
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				int tmp = rnd.nextInt(1000000) + 1;
				while (set.contains(tmp)) {
					tmp = rnd.nextInt(1000000) + 1;
				}
				set.add(tmp);
				a[i] = tmp;
			}
			Arrays.sort(a);
			for (int i = 0; i < n; i++) {
				out.write(String.valueOf(a[i]));
				out.write(" ");
			}
			out.write("\n");
			int d = rnd.nextInt(a[n-1]);
			out.write(String.valueOf(d));
			out.write("\n");


			/////////////////////////////////
			out.write(String.valueOf(n));
			out.write(" ");
			out.write(String.valueOf("1"));
			out.write("\n");
			for (int i = 0; i < n; i++) {
				out.write(String.valueOf(a[i]));
				out.write(" ");
			}
			out.write("\n");
			out.write(String.valueOf(d));
			out.write("\n");

			/////////////////////////////////
			out.write(String.valueOf(n));
			out.write(" ");
			out.write(String.valueOf("1"));
			out.write("\n");
			for (int i = 0; i < n; i++) {
				out.write(String.valueOf(a[i]));
				out.write(" ");
			}
			out.write("\n");
			out.write(String.valueOf(d));

			out.flush();
		}

		public void solve(int testNumber, MyInput in, PrintWriter out) throws IOException {

			long c1 = correct(testNumber, in);
			long c2 = wrong(testNumber, in);

			if (c1 != c2) {
				out.println("ERROR");
				int n = in.nextInt(), q = in.nextInt();
				long[] a = in.nextLongArray(n);
				long x = in.nextLong();
				out.printf("%d %d\n",n ,q);
				printArrayLine(a, out);
				out.println(x);
				out.println("=================");
				out.printf("%d %d\n",c1 ,c2);
				return;
			} else {
				out.println("OK");
				out.printf("%d %d\n",c1 ,c2);
			}
		}

		long correct(int testNumber, MyInput in) throws IOException {

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

			long ans = 0;
			while (q-- > 0) {
				long x = in.nextLong();
				long ng = -1, ok = INF;
				while (ok - ng > 1) {
					long mid = (ng + ok) / 2;
					int li_1 = lowerBound(a, x - mid);
					int ri_1 = upperBound(a, x + mid) - 1;
					int len = ri_1 - li_1 + 1;
					if (n - 1 <= ri_1 + len) {
						ok = mid;
					} else {
						ng = mid;
					}
				}

				long mid = ok;
				int ri_1 = upperBound(a, x + mid) - 1;
				int li_1 = lowerBound(a, x - mid);
				int aoki = ri_1 - li_1 + 1;
				int taka = n - (ri_1 + 1);

				if (aoki != taka) {
					ri_1--;
				}

				ans += sum[n-1] - (ri_1 >= 0 ? sum[ri_1] : 0);
				if (n % 2 == 0) {
					ans += li_1 - 1 >= 0 ? odd[li_1 - 1] : 0;
				} else {
					ans += li_1 - 1 >= 0 ? even[li_1 - 1] : 0;
				}
			}

			return ans;
		}

		long wrong(int testNumber, MyInput in) {
			int n = in.nextInt(), q = in.nextInt();
			long[] a = in.nextLongArray(n);
			long[] sum = new long[n], odd = new long[n], even = new long[n];
			for (int i = 0; i < n; i++) {
				sum[i] = (i-1 >= 0 ? sum[i-1] : 0) + a[i];
				even[i] = (i-1 >= 0 ? even[i-1] : 0) + (i % 2 == 0 ? a[i] : 0);
				odd[i] = (i-1 >= 0 ? odd[i-1] : 0) + (i % 2 == 1 ? a[i] : 0);
			}

			long ans = 0;
			while (q-- > 0) {
				long x = in.nextLong();
				int ok = 1, ng = n;
				while (ng - ok > 1) {
					int k = (ok + ng) / 2;
					if (check(k, x, a)) {
						ok = k;
					} else {
						ng = k;
					}
				}

				ans = sum[n - 1] - sum[n - ok - 1];
				if (n % 2 == 0) {
					ans += n - 2*ok - 1 >= 0 ? odd[n - 2*ok - 1] : 0;
				} else {
					ans += n - 2*ok - 1 >= 0 ? even[n - 2*ok - 1] : 0;
				}
			}
			return ans;
		}

		// 高橋くんが上からk枚とることができるかどうか
		boolean check(int k, long x, long[] a) {
			int n = a.length;
			if (k > n) return false;
			long d = a[n-k] - x;
			int s = lowerBound(a, x - d);

			// 青木くんが a[n-k] を選ぶのが何番目になるか。
			// 閉区間 [a[n-k] - x, a[n-k] + x] に含まれる数をすべて選ぶので、その個数番目
			int m = (n - 1 - k + 1) - (s - 1);

			// 高橋くんが a[n-k] 番目を選ぶ場合に青木くんが k 番目以上に選ぶのであれば
			// まだ a[n-k] は残っているので上から k 枚をとることが可能
			return m >= k;
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
