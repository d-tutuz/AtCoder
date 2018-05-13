package arc073;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
	static int modP = 1000000007;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int N = in.nextInt();
			long W = in.nextLong();
			P[] ps = new P[301];
			for (int i = 0; i < 301; i++) {
				ps[i] = new P(0, 0);
			}
			long w1 = in.nextLong();
			long v1 = in.nextLong();
			ps[0] = new P(0, v1);
			for (int i = 1; i < N; i++) {
				long w = in.nextLong(), v = in.nextLong();
				ps[i] = new P(w - w1, v);
			}

			long[][] dp = new long[N+1][301];

			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < 301; j++) {
					int pw = (int)ps[i].w;
					if (j - pw < 0) {
						dp[i][j] = dp[i-1][j];
						continue;
					}
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-pw]+ps[i].v);
				}
			}

			long sum1 = 0;
			for (int i = 0; i < N; i++) {
				sum1 += ps[i].w;
			}
			long sum2 = sum1 + N * w1;

			out.println(dp[N][(int) (W*sum1/sum2)]);

		}
	}
	static class P {
		long w, v;
		P (long w, long v) {
			this.w = w;
			this.v = v;
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
