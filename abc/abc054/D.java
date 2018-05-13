package abc054;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

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

	static int INF = 1 << 29;
	static int nmax = 40;
	static int abmax = 10;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int ma = in.nextInt();
			int mb = in.nextInt();
			int[] a = new int[nmax];
			int[] b = new int[nmax];
			int[] c = new int[nmax];

			for (int i = 0; i < n; i++) {
				a[i] = in.nextInt();
				b[i] = in.nextInt();
				c[i] = in.nextInt();
			}

			int[][][] dp = new int[nmax+1][nmax*abmax+1][nmax*abmax+1];
			for (int i = 0; i <= n; i++) {
				for (int j = 0; j <= nmax*abmax; j++) {
					for (int k = 0; k <= nmax*abmax; k++) {
						dp[i][j][k] = INF;
					}
				}
			}
			dp[0][0][0] = 0;

			for (int i = 0; i < n; i++) {
				for (int ca = 0; ca <= nmax*abmax; ca++) {
					for (int cb = 0; cb <= nmax*abmax; cb++) {
						if (dp[i][ca][cb] == INF) continue;
						dp[i+1][ca][cb] = Math.min(dp[i+1][ca][cb], dp[i][ca][cb]);
						dp[i+1][ca+a[i]][cb+b[i]] = Math.min(dp[i+1][ca+a[i]][cb+b[i]], dp[i][ca][cb]+c[i]);
					}
				}
			}

			int ans = INF;
			for (int ca = 1; ca <= nmax*abmax; ca++) {
				for (int cb = 1; cb <= nmax*abmax; cb++) {
					if (ca*mb == cb*ma) {
						ans = Math.min(ans, dp[n][ca][cb]);
					}
				}
			}

			if (ans == INF) {
				ans = -1;
			}

			out.println(ans);

		}
	}

	static class ArrayUtils {
		public static Map<Integer, Integer> getCountMap(int[] array) {
			Map<Integer, Integer> map = new TreeMap<>();
			for (int x : array)
				map.merge(x, 1, Integer::sum);
			return map;
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
