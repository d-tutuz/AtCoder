package 第16回日本情報オリンピック予選_オンライン;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt();
			int[] a = new int[n+1];

			// ぬいぐるみの個数
			int[] tmp = new int[m];
			int[] kosu = new int[1<<m];

			// 区間[l, r](1-indexed)に含まれるぬいぐるみ k の個数を求めるための累積和
			int[][] sum = new int[m][n+1];

			for (int i = 1; i < n+1; i++) {
				int t = in.nextInt()-1;
				a[i] = t;
				tmp[t]++;
				for (int k = 0; k < m; k++) {
					sum[k][i] = sum[k][i-1] + (t == k ? 1 : 0);
				}
			}

			// (左から並んでいる)ある状態の時の個数
			for (int state = 0; state < (1<<m); state++) {
				for (int i = 0; i < m; i++) {
					if (((state >> i) & 1) == 1) {
						kosu[state] += tmp[i];
					}
				}
			}

			int[] dp = new int[1<<m];
			Arrays.fill(dp, INF);
			dp[0] = 0;

			for (int now = 0; now < (1<<m); now++) {

				// ぬいぐるみ i を入れる時
				for (int i = 0; i < m; i++) {
					if (((now >> i) & 1) == 0) {
						int next = now | (1<<i);
						int nextKosu = dp[now] + (kosu[next] - kosu[now]) - (sum[i][kosu[next]] - sum[i][kosu[now]]);
						dp[next] = Math.min(nextKosu, dp[next]);
					}
				}
			}

			out.println(dp[(1<<m)-1]);

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
				res[i] = nextInt()-1;
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
