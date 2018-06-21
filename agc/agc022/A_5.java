package agc022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class A_5 {

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

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			char[] s = in.nextString().toCharArray();
			int len = s.length;

			boolean[] used = new boolean[26];
			for (int i = 0; i < len; i++) {
				used[s[i]-'a'] = true;
			}

			if (len < 26) {
				for (int i = 0; i < 26; i++) {
					if (!used[i]) {
						out.print(s);
						out.println((char)(i+'a'));
						return;
					}
				}
			} else {
				char[] t = s.clone();

				Permutation p = new Permutation();
				p.next(t);

				StringBuffer sb = new StringBuffer();
				boolean same = true;
				for (int i = 0; i < len; i++) {
					if (s[i] == t[i]) {
						sb.append(t[i]);
					} else {
						sb.append(t[i]);
						same = false;
						break;
					}
				}
				out.println(same ? -1 : sb.toString());
			}

		}

		static class Permutation {
			public boolean next(char[] a) {
				int n = a.length;

				int i = n - 1;
				while (i > 0 && a[i - 1] >= a[i])
					i--;
				if (i <= 0)
					return false;

				int j = n - 1;
				while (a[j] <= a[i - 1])
					j--;
				swap(a, i - 1, j);

				int k = n - 1;
				while (i < k)
					swap(a, i++, k--);

				return true;
			}

			private static void swap(char[] a, int i, int j) {
				char tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
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
