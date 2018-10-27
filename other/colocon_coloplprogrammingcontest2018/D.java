package colocon_coloplprogrammingcontest2018;

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

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int x = in.nextInt();
			int[] t = in.nextIntArray(n);

			int[][] dp = new int[n+1][n+1];
			for (int i = 0; i < n; i++) {

				int j = upperBound(t, t[i] + x) - 1;

				for (int k = 0; k < n; k++) {
					dp[j][k+1] = Math.max(dp[j][k+1], dp[i][k] + Math.min(t[j] - t[i], x));
					if (j+1 < n) {
						dp[j+1][k+1] = Math.max(dp[j+1][k+1], dp[i][k] + Math.min(t[j+1] - t[i], x));
					}
				}
			}

			for (int k = 0; k < n; k++) {
				int ans = 0;
				for (int i = 0; i < n+1; i++) {
					ans = Math.max(ans, dp[i][k]);
				}
				out.println(ans + x);
			}
		}
	}

	public static int upperBound(int[] a, int obj) {
		int l = 0, r = a.length - 1;
		while (r - l >= 0) {
			int c = (l + r) / 2;
			if (a[c] <= obj) {
				l = c + 1;
			} else {
				r = c - 1;
			}
		}
		return l;
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

		public int[] nextIntArray(int n) {
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt();
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
