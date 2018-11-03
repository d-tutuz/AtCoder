package abc107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class D_3 {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
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

		int k = 1 << 17;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int[] a = in.nextIntArray(n);
			int[] as = a.clone();
			Arrays.sort(as);

			BIT bit = new BIT(k);
			int thr = n * (n+1) / 2;
			thr = (thr + 1) / 2;

			int l = -1, r = n;
			while (r - l > 1) {
				bit.clear();
				int m = (r+l)/2;
				int[] b = new int[n+1];
				for (int i = 0; i < n; i++) {
					b[i+1] = as[i] < as[m] ? -1 : 1;
					b[i+1] += b[i];
				}

				int[] bs = b.clone();
				Arrays.sort(bs);
				int idx = 0;
				TreeMap<Integer, Integer> map = new TreeMap<>();
				for (int i = 0; i < n+1; i++) map.put(bs[i], idx++);

				int cnt = 0;
				for (int i = 0; i < n+1; i++) {
					cnt += bit.query(map.get(b[i]));
					bit.add(map.get(b[i]), 1);
				}

				if (cnt >= thr) {
					l = m;
				} else {
					r = m;
				}
			}
			out.println(as[r]);

		}
	}

	/**
	 * 0-indexed BinaryIndexTree
	 * */
	static class BIT {
		private int n;
		private long[] bit;

		public BIT(int n) {
			this.n = n;
			bit = new long[n + 1];
		}

		public void clear() {
			Arrays.fill(bit, 0);
		}

		public void add(int i, long x) {
			while (i <= n) {
				bit[i] += x;
				i |= i + 1;
			}
		}

		public long query(int l, int r) {
			return l <= r ? query(r) - query(l - 1) : 0;
		}

		private long query(int i) {
			long s = 0;
			while (i >= 0) {
				s += bit[i];
				i = (i & (i + 1)) - 1;
			}
			return s;
		}
	}

	static class InputReader {
		BufferedReader in;
		StringTokenizer tok;

		public String nextString() {
			while (!tok.hasMoreTokens()) {
				try {
					tok = new StringTokenizer(in.readLine(), " ");
				} catch (IOException e) {
					throw new InputMismatchException();
				}
			}
			return tok.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(nextString());
		}

		public long nextLong() {
			return Long.parseLong(nextString());
		}

		public double nextDouble() {
			return Double.parseDouble(nextString());
		}

		public int[] nextIntArray(int n) {
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt();
			}
			return res;
		}

		public int[] nextIntArrayDec(int n) {
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt() - 1;
			}
			return res;
		}

		public int[] nextIntArray1Index(int n) {
			int[] res = new int[n + 1];
			for (int i = 0; i < n; i++) {
				res[i + 1] = nextInt();
			}
			return res;
		}

		public long[] nextLongArray(int n) {
			long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong();
			}
			return res;
		}

		public long[] nextLongArrayDec(int n) {
			long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong() - 1;
			}
			return res;
		}

		public long[] nextLongArray1Index(int n) {
			long[] res = new long[n + 1];
			for (int i = 0; i < n; i++) {
				res[i + 1] = nextLong();
			}
			return res;
		}

		public double[] nextDoubleArray(int n) {
			double[] res = new double[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextDouble();
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
