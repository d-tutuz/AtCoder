package codefestival2018final_parallel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class E_3 {

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

			int n = in.nextInt(), k = in.nextInt();
			RangeMinimumQuery rmq = new RangeMinimumQuery(n);
			for (int i = 0; i < n; i++) {
				int a = in.nextInt();
				rmq.update(i, a);
			}
			long ans = 0;
			for (int i = 0; i < n; i++) {
				ans += rmq.query(Math.max(0, i-k+1), i+1);
			}
			out.println(ans);
		}
	}

	static class RangeMinimumQuery {
		final int inf = (1 << 31) - 1;

		int size;
		int[] dat;

		// 初期化
		public RangeMinimumQuery(int n) {
			size = 1;
			while (size < n) {
				size *= 2;
			}
			dat = new int[size * 2];
			for (int i = 0; i < size * 2; i++) {
				dat[i] = inf;
			}
		}

		// k 番目(0-indexed) を a に更新
		void update(int k, int a) {
			k += size;
			dat[k] = a;
			while (k > 0) {
				k /= 2;
				dat[k] = Math.min(dat[2 * k], dat[2 * k + 1]);
			}
		}

		// [a, b) の最小値を求める
		private int query(int a, int b, int k, int l, int r) {
			if (r <= a || b <= l) return inf;

			if (a <= l && r <= b) {
				return dat[k];
			} else {
				int vl = query(a, b, 2 * k, l, (l+r)/2);
				int vr = query(a, b, 2 * k + 1, (l+r)/2, r);
				return Math.min(vl, vr);
			}
		}

		int query(int a, int b) {
			return query(a, b, 1, 0, size);
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
