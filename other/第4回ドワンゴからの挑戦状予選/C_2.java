package 第4回ドワンゴからの挑戦状予選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
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

	static int INF = 1 << 30;
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		int[][] p = partition(1010, 1010, MOD);
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt();
			int[] a = in.nextIntArray(n), b = in.nextIntArray(m);
			out.println(f(a, b) * f(b, a) % MOD);

		}

		long f(int[] kill, int[] death) {
			Deque<P> q = new ArrayDeque<>();
			q.addFirst(new P(kill[0], 1));
			for (int i = 1; i < kill.length; i++) {
				if (q.peekFirst().first == kill[i]) {
					P p = q.removeFirst();
					p.second++;
					q.addFirst(p);
				} else {
					q.addFirst(new P(kill[i], 1));
				}
			}

			List<P> cnt = new ArrayList<>();
			while (!q.isEmpty()) {
				P p = q.removeLast();
				cnt.add(p);
			}

			int sum = Arrays.stream(death).sum();
			int n = cnt.size();

			long[][] dp = new long[n+1][sum+1];
			dp[0][0] = 1;
			for (int g = 0; g < n; g++) {
				for (int s = 0; s < sum+1; s++) {
					for (int i = 0; i+s < sum+1; i++) {
						dp[g+1][s+i] += dp[g][s] * p[cnt.get(g).second][i];
						dp[g+1][s+i] %= MOD;
					}
				}
			}

			return dp[n][sum];
		}

		int[][] partition(int sum, int part, final int mod) {
			int[][] dp = new int[part + 1][sum + 1];
			dp[0][0] = 1;
			for (int i = 1; i <= part; i++) {
				for (int j = 0; j <= sum; j++) {
					dp[i][j] = dp[i - 1][j] + (j < i ? 0 : dp[i][j - i]);
					dp[i][j] %= MOD;
				}
			}
			return dp;
		}
	}

	static class P {
		int first, second;

		public P(int first, int second) {
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
			result = prime * result + second;
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
