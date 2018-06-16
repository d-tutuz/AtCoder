package codefestival2014quala;

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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		char[] s;
		int k;
		long[][][][] memo = new long[2][20][1 << 10][2];

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			s = in.nextString().toCharArray();
			k = in.nextInt();
			int n = s.length;

			for (long[][][] i : memo)
				for (long[][] j : i)
					for (long[] k : j)
						Arrays.fill(k, -1);

			long src = Long.parseLong(String.valueOf(new String(s)));
			long min = abs(src - recMin(0, 0, 1));
			long max = abs(src - recMax(0, 0, 1));

			out.println(min(min, max));
		}

		long recMin(int i, int used, int tight) {

			if (i == s.length) return 0;
			if (memo[0][i][used][tight] >= 0) return memo[0][i][used][tight];

			int cnt = Integer.bitCount(used);

			long ret = Long.MAX_VALUE;

			long dd = 1;
			for (int j = 0; j < s.length-1-i; j++) {
				dd *= 10;
			}

			int d = s[i]-'0';
			for (int e = 0; e < 10; e++) {
				if (cnt < k || (used & (1 << e)) != 0) {
					long v = recMin(i+1, used | (1 << e), tight == 1 && d == e ? 1 : 0);
					if (v < Long.MAX_VALUE) {
						ret = Math.min(v + d * dd, ret);
					}
				}
			}

			return memo[0][i][used][tight] = ret;
		}

		long recMax(int i, int used, int tight) {

			if (i == s.length) return 0;
			if (memo[1][i][used][tight] >= -1) return memo[1][i][used][tight];

			int cnt = Integer.bitCount(used);
			long ret = -1;

			long dd = 1;
			for (int j = 0; j < s.length-1-i; j++) {
				dd *= 10;
			}

			int d = s[i]-'0';
			for (int e = 0; e < 10; e++) {
				if (cnt < k || ((used & (1 << d)) != 0)) {
					if (tight == 0 || d >= e) {
						long v = recMax(i+1, used | (1 << d), tight == 1 && d == e ? 1 : 0);
						if (v >= 0) {
							ret = Math.max(v + d * dd, ret);
						}
					}
				}
			}

			return memo[1][i][used][tight] = ret;

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
