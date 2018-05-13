package agc022;

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
			String t = new String(input);
			char[] s = t.toCharArray();
			int[] is = new int[s.length];
			for (int i = 0; i < is.length; i++) {
				is[i] = (int)s[i] - 'a';
			}

			String[] al = "abcdefghijklmnopqrstuvwxyz".split("");

			int[] count = new int[26];
			for (int i = 0; i < s.length; i++) {
				count[s[i]-'a']++;
			}

			if ("zyxwvutsrqponmlkjihgfedcba".equals(input)) {
				out.println(-1);
				return;
			}

			StringBuffer sb = new StringBuffer();
			if (input.length() <= 25) {
				for (int i = 0; i < 26; i++) {
					if (count[i] == 0) {
						for (int j = 0; j < s.length; j++) {
							out.print(s[j]);
						}
						out.println(al[i]);
						return;
					}
				}
			} else {
				for (int i = 0; i < s.length; i++) {
					Permutation.next(is);
					sb.append((char)(is[i]+'a'));
				}
			}
			out.println(sb.toString());


		}
	}

	static class Permutation {
		public static boolean next(int[] a) {
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

		private static void swap(int[] a, int i, int j) {
			int tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
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
