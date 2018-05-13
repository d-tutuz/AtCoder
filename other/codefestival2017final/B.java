package codefestival2017final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
	static int modP = 1000000007;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			char[] s = in.nextString().toCharArray();
			int[] table = new int[3];
			int len = s.length;
			for (int i = 0; i < len; i++) {
				table[s[i]-'a']++;
			}

			for (int k = 0; k < 3; k++) {
				// a,c,b,a,c,b, ...
				int[] tmptable = Arrays.copyOf(table, 3);
				for (int i = 0; i < len; i++) {
					if (i % 3 == 0) {
						tmptable[k%3]--;
					} else if (i % 3 == 1) {
						tmptable[(k+2)%3]--;
					} else if (i % 3 == 2) {
						tmptable[(k+1)%3]--;
					}
				}
				if (check(tmptable)) {
					out.println("YES");
					return;
				}

				// a,b,c,a,b,c, ...
				tmptable = Arrays.copyOf(table, 3);
				for (int i = 0; i < len; i++) {
					if (i % 3 == 0) {
						tmptable[k%3]--;
					} else if (i % 3 == 1) {
						tmptable[(k+1)%3]--;
					} else if (i % 3 == 2) {
						tmptable[(k+2)%3]--;
					}
				}
				if (check(tmptable)) {
					out.println("YES");
					return;
				}
			}
			out.println("NO");

		}
		static boolean check(int[] table) {
			boolean ret = true;
			for (int i = 0; i < table.length-1; i++) {
				if (table[i] == 0) {
					continue;
				} else {
					ret = false;
				}
			}
			return ret;
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
