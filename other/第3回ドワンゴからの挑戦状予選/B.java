package 第3回ドワンゴからの挑戦状予選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class B {

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

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			char[] s = in.nextString().toCharArray();
			int n = s.length;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < s.length; i++) {
				if (i % 2 == 0) sb.append('2');
				if (i % 2 == 1) sb.append('5');
			}

			char[] p1 = sb.toString().toCharArray();

			sb = new StringBuilder();
			for (int i = 0; i < s.length; i++) {
				if (i % 2 == 0) sb.append('5');
				if (i % 2 == 1) sb.append('2');
			}
			char[] p2 = sb.toString().toCharArray();

			int ans = 0;
			int tmp = 0;
			for (int i = 0;;) {
				if (i+1 >= s.length) break;
				if ((s[i] == '?' || s[i] == p1[i]) && (s[i+1] == '?' || s[i+1] == p1[i+1])) {
					tmp++;
					i += 2;
					continue;
				}
				i++;
				ans = Math.max(ans, tmp*2);
				tmp = 0;
			}
			ans = Math.max(ans, tmp*2);
			tmp = 0;

			for (int i = 0;;) {
				if (i+1 >= s.length) break;
				if ((s[i] == '?' || s[i] == p2[i]) && (s[i+1] == '?' || s[i+1] == p2[i+1])) {
					tmp++;
					i += 2;
					continue;
				}
				ans = Math.max(ans, tmp*2);
				tmp = 0;
				i++;
			}
			ans = Math.max(ans, tmp*2);
			tmp = 0;

			out.println(ans);
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
