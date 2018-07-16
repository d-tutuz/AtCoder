package codefestival2017final_parallel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class A {

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

			char[] tmp = in.nextString().toCharArray();

			char[] s = new char[tmp.length+10];
			Arrays.fill(s, 'x');
			for (int i = 0; i < tmp.length; i++) {
				s[i+10] = tmp[i];
			}

			int n = s.length;
			boolean ok = true;
			for (int i = 10; i < n; i++) {
				if (!(s[i] == 'A' || s[i] == 'K' || s[i] == 'I' || s[i] == 'H' || s[i] == 'B' || s[i] == 'R' || s[i] == 'x')) {
					ok = false; break;
				}

				if (s[i] == 'K') {
					if (!(s[i-1] == 'x' || (s[i-1] == 'A' && s[i-2] == 'x'))) {
						ok = false;
						break;
					}
				}

				if (s[i] == 'I') {
					if (!(s[i-1] == 'K')) {
						ok = false;
						break;
					}
				}

				if (s[i] == 'H') {
					if (!(s[i-1] == 'I')) {
						ok = false;
						break;
					}
				}

				if (s[i] == 'B') {
					if (!((s[i-1] == 'A' && s[i-2] == 'H') || s[i-1] == 'H')) {
						ok = false;
						break;
					}
				}

				if (s[i] == 'R') {
					if (!((s[i-1] == 'A' && s[i-2] == 'B') || s[i-1] == 'B')) {
						ok = false;
						break;
					}
					if (!((i == n-2 && s[i+1] == 'A') || i == n-1)) {
						ok = false;
						break;
					}
				}

				if (!(s[n-2] == 'R' || s[n-1] == 'R')) {
					ok = false; break;
				}

			}
			out.println(ok ? "YES" : "NO");

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
