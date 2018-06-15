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
	static int MOD = 1000000007;

	static class TaskX {

		char[] s;
		long ans = Long.MAX_VALUE/10;
		int K;

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			s = in.nextString().toCharArray();
			K = in.nextInt();

			dfs(0, 0, 0, 0, "");

			out.println(ans);

		}

		void dfs(int i, int set, int gt, int lt, String now) {

			if (i == s.length) {
				ans = Math.min(ans, abs(Long.parseLong(new String(s)) - Long.parseLong(now)));
				return;
			}

			int d = s[i]-'0';
			if (gt == 0 && lt == 0) {
				for (int e = 0; e <= 9 ; e++) {
					if (Integer.bitCount(set) > K) continue;
					dfs(i+1, set | (1 << e), e > d ? 1 : 0, e < d ? 1 : 0, now+e);
				}
			} else if (lt == 1) {
				for (int e = 9; e >= 0 ; e--) {
					if (Integer.bitCount(set) > K) continue;
					dfs(i+1, set | (1 << e), e > d ? 1 : 0, e < d ? 1 : 0, now+e);
					break;
				}
			} else if (gt == 1) {
				for (int e = 0; e <= 9 ; e++) {
					if (Integer.bitCount(set) > K) continue;
					dfs(i+1, set | (1 << e), e > d ? 1 : 0, e < d ? 1 : 0, now+e);
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
