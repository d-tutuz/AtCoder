package codefestival2016qualA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class C {

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

			char[] moji = in.nextString().toCharArray();
			int len = moji.length;
			int[] s = new int[len];
			for (int i = 0; i < len; i++) {
				s[i] = (int)(moji[i]-'a');
			}
			long k = in.nextLong();
			if (k > len * 26) {
				k = k % 26 + 26 * len;
			}
			for (int i = 0; i < len; i++) {
				if (k >= 26-s[i] && s[i]!=0) {
					k -= (26-s[i]);
					s[i] = 0;
				}
			}

			while (k > 0) {
				s[len-1]++;
				s[len-1] %= 26;
				k--;
			}

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < len; i++) {
				sb.append((char)(s[i]+'a'));
			}
			out.println(sb.toString());

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
