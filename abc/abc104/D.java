package abc104;

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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			char[] s = in.nextString().toCharArray();
			int Acount = 0;
			int Bcount = 0;
			int Ccount = 0;
			int Xcount = 0;
			for (char c : s) {
				if (c == '?') {
					Xcount++;
				} else if (c == 'A') {
					Acount++;
				} else if (c == 'B') {
					Bcount++;
				} else if (c == 'C') {
					Ccount++;
				}
			}

			int Anow = 0;
			int Bnow = 0;
			int Cnow = 0;
			int Xnow = 0;

			long ans = 0;
			for (int i = 0; i < s.length; i++) {
				if (s[i] == '?') {
					ans += (Xnow + Anow) * ((Ccount - Cnow + Xcount-1-Xnow));
					Xnow++;
				} else if (s[i] == 'A') {
					ans += (Xnow + Anow) * ((Ccount - Cnow + Xcount-Xnow));
					Anow++;
				} else if (s[i] == 'B') {
					ans += (Xnow + Anow) * ((Ccount - Cnow + Xcount-Xnow));
					Bnow++;
				} else if (s[i] == 'C') {
					ans += (Xnow + Anow) * ((Ccount - Cnow + Xcount-Xnow));
					Cnow++;
				}
			}

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
