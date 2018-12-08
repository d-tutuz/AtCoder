package abc115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.StringTokenizer;

public class D_2 {

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

		long[] a;
		Map<P, Long> memo = new HashMap<>();
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			a = new long[60];
			a[0] = 1;
			for (int i = 1; i < 60; i++) {
				a[i] = 3 + a[i-1] * 2;
			}

			memo.put(new P(1, 0), 0L);
			memo.put(new P(1, 1), 0L);
			memo.put(new P(1, 2), 1L);
			memo.put(new P(1, 3), 2L);
			memo.put(new P(1, 4), 3L);
			memo.put(new P(1, 5), 3L);

			int n = in.nextInt();
			long x = in.nextLong();
			long sum = func(n, x);
			out.println(sum);

		}

		long func(int L, long k) {
			if (L == 0) {
				return 1L;
			}

			if (memo.containsKey(new P(L, k))) {
				return memo.get(new P(L, k));
			}

			long ret = 0;
			long c = a[L]/2 + 1;
			if (k < c) {
				ret += func(L-1, k-1);
			} else if (k == c) {
				ret += func(L-1, k-1) + 1;
			} else if (k > c) {
				ret += func(L-1, a[L-1]) + 1 + func(L-1, k - c);
			}

			memo.put(new P(L, k), ret);
			return ret;
		}

		static class P {
			int first;
			long second;

			public P(int first, long second) {
				super();
				this.first = first;
				this.second = second;
			}

			@Override
			public String toString() {
				return "P [first=" + first + ", second=" + second + "]";
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + first;
				result = prime * result + (int) (second ^ (second >>> 32));
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
				P other = (P) obj;
				if (first != other.first)
					return false;
				if (second != other.second)
					return false;
				return true;
			}

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
