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

public class D {

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
	static long LINF = 1L << 50;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		int n;
		long[] a = new long[200010];
		long[] sum = new long[200010];
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			n = in.nextInt();
			for (int i = 1; i <= n; i++) {
				a[i] = in.nextLong();
				sum[i] = sum[i-1] + a[i];
			}

			long ans = LINF;
			// 半分に区切る位置を全探索
			for (int i = 2; i <= n-1; i++) {
				P[] psum = new P[2];

				psum[0] = divide(1, i);
				psum[1] = divide(i+1, n);

				List<Long> list = new ArrayList<>();
				list.add(psum[0].a);
				list.add(psum[0].b);
				list.add(psum[1].a);
				list.add(psum[1].b);

				Collections.sort(list);

				ans = Math.min(ans, list.get(3) - list.get(0));

			}

			out.println(ans);

		}

		// 閉区間[b, e]の数列をなるべく均等に分ける各配列の総和をペアで返す
		P divide(int b, int e) {

			int l = b, r = e;
			long ds= sum[e] - sum[b-1];

			// 二分探索で[b, m]の和が[b, e]の半分以下になる最大のmを探す
			while (r - l > 1) {
				int m = (r + l) / 2;

				long ps = sum[m] - sum[b-1];
				if (ps * 2 <= ds) {
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
			long a;
			long b;

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

		public long[] nextLongArray(int n) {
			long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong();
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
