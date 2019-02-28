package 第3回ドワンゴからの挑戦状予選;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class B_2 {

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
		long[][] memo;
		long max = -1;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			String ss = in.nextString();
			s = new char[ss.length()+1];
			for (int i = 1; i <= ss.length(); i++) {
				s[i] = ss.charAt(i-1);
			}
			int n = s.length;

			memo = new long[n][3];
			for (int i = 0; i < n; i++) {
				Arrays.fill(memo[i], -1);
			}

			long ans = max(func(1, 0), max);

			out.println(ans * 2);

		}

		long func(int cur, long count) {

			if (cur >= s.length) {
				return count;
			}

			if (memo[cur][s[cur] == '2' ? 0 : s[cur] == '5' ? 1 : 2] != -1) {
				return memo[cur][s[cur] == '2' ? 0 : s[cur] == '5' ? 1 : 2];
			}

			long ret = 0;

			if (s[cur] == '?' || s[cur] == '5') {
				if (s[cur-1] == '2' || s[cur-1] == '?') {
					ret += func(cur+2, count+1);
				} else {
					max = max(count, max);
					ret += func(cur+1, 0);
				}
			} else {
				max = max(count, max);
				ret += func(cur+1, 0);
			}

			return memo[cur][s[cur] == '2' ? 0 : s[cur] == '5' ? 1 : 2] = ret;
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
