package agc016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
	static int modP = 1000000007;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			String input = in.nextString();
			String s = new String(input);

			String[] alpha = "abcdefghijklmnopqrstuvwxyz".split("");
			StringBuffer sb = new StringBuffer();
			int ans = INF;
			for (int i = 0; i < 26; i++) {
				int count = 0;
				s = input;
				while (!check(s)) {
					sb = new StringBuffer();
					for (int k = 0; k < s.length() - 1; k++) {
						if (alpha[i].equals(s.substring(k, k+1)) || alpha[i].equals(s.substring(k+1, k+2))) {
							sb.append(alpha[i]);
						} else {
							sb.append(s.substring(k, k+1));
						}
					}
					s = sb.toString();
					count++;
				}
				ans = Math.min(ans, count);
			}

			out.println(ans);

		}

		static boolean check(String s) {
			for (int i = 0; i < s.length() - 1; i++) {
				if (!s.substring(i, i+1).equals(s.substring(i+1, i+2))) {
					return false;
				}
			}
			return true;
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
