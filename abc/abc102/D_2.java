package abc102;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
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

		long[] a, sum;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			a = new long[n+1];
			sum = new long[n+1];
			for (int i = 1; i < n+1; i++) {
				a[i] = in.nextLong();
				sum[i] = sum[i-1] + a[i];
			}

			long min = LINF;

			for (int i = 2; i < n-1; i++) {
				P p1 = calc(1, i);
				P p2 = calc(i+1, n);

				List<Long> list = new ArrayList<>();
				list.add(p1.a);
				list.add(p1.b);
				list.add(p2.a);
				list.add(p2.b);

				Collections.sort(list);

				min = Math.min(min, list.get(3) - list.get(0));
			}

			out.println(min);

		}

		// 閉区間 [b, e] 上の sum の差を最小にする値を求める。
		P calc(int b, int e) {

			int l = b, r = e;
			long hs = sum[r] - sum[l-1];

			// P <= Q となる位置 l を探す
			while (r - l > 1) {
				int m = (r+l)/2;

				long ls = sum[m] - sum[b-1];

				if (ls * 2 <= hs) {
					l = m;
				} else {
					r = m;
				}
			}

			// P <= Q の場合
			P ret1 = new P(sum[l] - sum[b-1], sum[e] - sum[l]);

			// P > Q の場合
			P ret2 = new P(sum[l+1] - sum[b-1], sum[e] - sum[l+1]);

			if (ret1.a - ret1.b > ret2.b - ret2.a) {
				return ret1;
			} else {
				return ret2;
			}

		}

		class P {
			long a, b;

			public P(long a, long b) {
				super();
				this.a = a;
				this.b = b;
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

}
