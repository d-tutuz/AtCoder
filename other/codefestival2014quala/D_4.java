package codefestival2014quala;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		char[] s;
		long ans = Long.MAX_VALUE/10;
		int k;

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			s = in.nextString().toCharArray();
			k = in.nextInt();

			dfs(0, 0, 0, 0, "");

			long tmp = 0;
			for (int i = 0; i < s.length-1; i++) {
				tmp *= 10;
				tmp += 9;
			}

			out.println(min(ans, Long.parseLong(new String(s)) - tmp));

		}

		void dfs(int i, int set, int gt, int lt, String now) {

			if (i == s.length) {
				long src = Long.parseLong(String.valueOf(new String(s)));
				long tar = Long.parseLong(now);
				ans = min(ans, abs(src - tar));
				return;
			}

			int d = s[i]-'0';

			if (gt == 0 && lt == 0) {
				for (int e = 0; e <= 9; e++) {

					int nextSet = set | (1 << e);
					int cnt = Integer.bitCount(nextSet);
					if (cnt > k) continue;

					gt = d < e ? 1 : 0;
					lt = d > e ? 1 : 0;
					String next = now + String.valueOf(e);

					dfs(i+1, nextSet, gt, lt, next);
				}
			} else if (lt == 1) {
				for (int e = 9; e >= 0; e--) {

					int nextSet = set | (1 << e);
					int cnt = Integer.bitCount(nextSet);
					if (cnt > k) continue;

					String next = now + String.valueOf(e);

					dfs(i+1, nextSet, 0, 1, next);
					break;
				}
			} else if (gt == 1) {
				for (int e = 0; e <= 9; e++) {
					int nextSet = set | (1 << e);
					int cnt = Integer.bitCount(nextSet);
					if (cnt > k) continue;

					String next = now + String.valueOf(e);

					dfs(i+1, nextSet, 1, 0, next);
					break;
				}
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
