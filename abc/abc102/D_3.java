package abc102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.StringTokenizer;

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

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();

			// 1-indexed
			long[] a = in.nextLongArray1Idx(n);
			long[] sum = a.clone();
			Arrays.parallelPrefix(sum, Math::addExact);

			long ans = LINF;
			for (int i = 2; i < n-1; i++) {

				List<Long> list = new ArrayList<>();
				P p1 = calc(sum, 1, i);
				P p2 = calc(sum, i+1, n);

				list.add(p1.a);
				list.add(p1.b);
				list.add(p2.a);
				list.add(p2.b);

				Collections.sort(list);

				ans = min(ans, list.get(3) - list.get(0));
			}

			out.println(ans);

		}

		// 区間 [from, to] で累積和をなるべく半分に分ける2数を求める
		P calc(long[] sum, int from, int to) {

			int now = find(sum, from, to);
			P p1 = new P(sum[now] - sum[from-1], sum[to] - sum[now]);
			P p2 = new P(sum[now+1] - sum[from-1], sum[to] - sum[now+1]);

			return p1.sum() >= p2.sum() ? p2 : p1;
		}

		// 区間 [from, to] で累積和をなるべく半分に分ける位置を探す
		int find(long[] sum, int from, int to) {
			long v = (sum[to] - sum[from-1])/2;

			int l = from, r = to;
			while (r - l > 1) {
				int m = (r+l)/2;
				long tmp = sum[m] - sum[from-1];
				if (tmp > v) {
					r = m;
				} else {
					l = m;
				}
			}
			return l;
		}

		class P {
			long a, b;

			public P(long a, long b) {
				super();
				this.a = a;
				this.b = b;
			}

			long sum() {
				return Math.abs(this.a - this.b);
			}

			@Override
			public String toString() {
				return "P [a=" + a + ", b=" + b + "]";
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

		public long[] nextLongArray(int n) {
			long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong();
			}
			return res;
		}

		public long[] nextLongArray1Idx(int n) {
			long[] res = new long[n+1];
			for (int i = 1; i < n+1; i++) {
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
