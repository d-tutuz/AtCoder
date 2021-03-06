package abc029;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class D_4 {

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

		char[] s;
		long[][][] memo;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			s = in.nextString().toCharArray();
			int n = s.length;
			memo = new long[n+1][n+1][2];
			for (int i = 0; i < n+1; i++) {
				for (int j = 0; j < n+1; j++) {
					Arrays.fill(memo[i][j], -1);
				}
			}

			long ans = rec(0, 0, 1);

			out.println(ans);

		}

		long rec(int now, int count, int tight) {


			if (memo[now][count][tight] >= 0) {
				return memo[now][count][tight];
			}

			if (now == s.length) {
				return count;
			}

			long ret = 0;
			int d = s[now]-'0';
			for (int e = 0; e <= (tight == 1 ? d : 9); e++) {

				System.out.printf("(%d,%d,%d)\n", now+1, e == 1 ? count+1 : count, tight == 1 && e == d ? 1 : 0);
				ret += rec(now+1, e == 1 ? count+1 : count, tight == 1 && e == d ? 1 : 0);
			}

			return memo[now][count][tight] = ret;
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
