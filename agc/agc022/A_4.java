package agc022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.StringTokenizer;

public class A_4 {

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
			int n = s.length;
			Set<Character> set = new HashSet<>();
			for (int i = 0; i < n; i++) {
				set.add(s[i]);
			}

			if (n < 26) {
				for (char c = 'a'; c <= 'z'; c++) {
					if (!set.contains(c)) {
						out.println(new String(s) + c);
						return;
					}
				}
			} else if ((new String(s)).equals("zyxwvutsrqponmlkjihgfedcba")) {
				out.println(-1);
				return;
			} else {
				int[] c = new int[s.length];
				for (int i = 0; i < c.length; i++) c[i] = s[i] - 'a';
				Permutation.next(c);
				for (int i = 0; i < c.length; i++) {
					if ((c[i] + 'a') == s[i]) {
						out.print((char)(c[i] + 'a'));
					} else {
						out.println((char)(c[i] + 'a'));
						return;
					}
				}
			}

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
