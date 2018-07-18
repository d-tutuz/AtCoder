package abc041;

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

		int n;
		long[] s = new long[1<<n];
		long[][] mat;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			n = in.nextInt();
			int m = in.nextInt();
			mat = new long[n+1][n+1];
			for (int i = 0; i < n+1; i++) {
				Arrays.fill(mat[i], -1);
			}

			// x から ｙ への有向辺
			for (int i = 0; i < m; i++) {
				int x = in.nextInt(), y = in.nextInt();
				mat[x][y] = 1;
			}

			long ans = 0;

			// i をトポロジカルソート済みの頂点として数え上げ
			for (int i = 1; i <= n; i++) {
				ans += rec(1<<i, i);
			}

			out.println(ans);
		}

		long rec(int state, int now) {

			long ret = 1;

			// すべてソート済になった場合は return.
			if (Integer.bitCount(state) == n) {
				return ret;
			}

			// 頂点 [1, n] についてトポロジカルソート
			for (int to = 1; to <= n; to++) {
				if (now == to) continue;

				// まだ未ソート かつ 現在の位置から対抗頂点への辺がない
				if (((state >> to) & 1) == 0 && mat[now][to] == -1) {
					ret *= rec(state | (1 << to), to);
				}
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
