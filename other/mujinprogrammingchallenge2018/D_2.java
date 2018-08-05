package mujinprogrammingchallenge2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
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

		boolean[][] visited, memo;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt();
			visited = new boolean[1000][1000];
			memo = new boolean[1000][1000];

			long ans = 0;
			for (int x = 1; x <= n; x++) {
				for (int y = 1; y <= m; y++) {
					if (memo[x][y] = rec(x, y)) {
						ans++;
					}
				}
			}

			out.println(n * m - ans);
		}

		boolean rec(int x, int y) {
			if (x == 0 || y == 0) {
				return memo[x][y] = true;
			}

			if (visited[x][y]) {
				return memo[x][y];
			}

			visited[x][y] = true;

			if (x < y) {
				x = rev(x);
			} else {
				y = rev(y);
			}

			if (x < y) {
				y = y - x;
			} else {
				x = x - y;
			}
			return memo[x][y] = rec(x, y);

		}

		int rev(int k) {

			char[] sk = String.valueOf(k).toCharArray();
			int len = sk.length;
			int ret = 0;
			for (int i = len-1, j = 0; i >= 0; i--, j++) {
				ret += (int)(sk[i]-'0') * Math.pow(10, len-1-j);
			}

			return ret;
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
