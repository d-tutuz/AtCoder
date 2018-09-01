package abc045;

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
import java.util.TreeMap;

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
	static int[] mh9 = { -1, -1, -1, 0, 0, 1, 1, 1, 0 };
	static int[] mw9 = { -1, 0, 1, -1, 1, -1, 0, 1, 0 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			long h = in.nextLong(), w = in.nextLong();
			int n = in.nextInt();
			int[] a = new int[n];
			int[] b = new int[n];

			for (int i = 0; i < n; i++) {
				a[i] = in.nextInt();
				b[i] = in.nextInt();
			}

			Map<P, Integer> map = new HashMap<>();

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < 9; j++) {
					int mh = a[i] + mh9[j];
					int mw = b[i] + mw9[j];

					if (2 <= mh && mh < h && 2 <= mw && mw < w) {
						map.merge(new P(mh, mw), 1, Integer::sum);
					}
				}
			}

			TreeMap<Long, Long> ans = new TreeMap<>();
			ans.put(0L, (h-2)*(w-2));
			for (long i = 1L; i <= 9; i++) {
				ans.put(i, 0L);
			}

			for (long count : map.values()) {
				ans.merge(0L, -1L, Long::sum);
				ans.merge(count, 1L, Long::sum);
			}

			for (long i : ans.values()) {
				out.println(i);
			}
		}


	}

	static class P {
		int h, w;

		public P(int h, int w) {
			super();
			this.h = h;
			this.w = w;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + h;
			result = prime * result + w;
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
			if (h != other.h)
				return false;
			if (w != other.w)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "[h=" + h + ", w=" + w + "]";
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

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

	static long max(long... n) {
		long ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.max(ret, n[i]);
		}
		return ret;
	}

	static int max(int... n) {
		int ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.max(ret, n[i]);
		}
		return ret;
	}

	static long min(long... n) {
		long ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.min(ret, n[i]);
		}
		return ret;
	}

	static int min(int... n) {
		int ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.min(ret, n[i]);
		}
		return ret;
	}

}
