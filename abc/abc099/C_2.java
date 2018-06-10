package abc099;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.StringTokenizer;

public class C_2 {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static int INF = 1 << 26;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();

			List<Integer> coins = new ArrayList<>();
			coins.add(1);
			for (int i = 0; i < 10; i++) {
				int num = (int)Math.pow(6, i);
				if (num <= 100000) {
					coins.add(num);
				} else {
					break;
				}
			}

			for (int i = 0; i < 10; i++) {
				int num = (int)Math.pow(9, i);
				if (num <= 100000) {
					coins.add(num);
				} else {
					break;
				}
			}

			int[] dp = new int[100010];
			Arrays.fill(dp, INF);

			int m = coins.size();
			for (int i = 0; i < m; i++) {
				dp[coins.get(i)] = 1;
			}

			for (int i = 0; i < dp.length; i++) {
				for (int j = 0; j < m; j++) {
					if (i - coins.get(j) <= 0) continue;
					int a = dp[i - 1] + 1;
					int b = dp[i - coins.get(j)] + 1;
					dp[i] = Math.min(dp[i], Math.min(a, b));
				}
			}

			out.println(dp[n]);

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
