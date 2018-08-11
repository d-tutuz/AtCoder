package colocon_coloplprogrammingcontest2018_final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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

			List<Long> list = new ArrayList<>();
			long n = in.nextLong();
			char[] s = in.nextString().toCharArray();

			boolean allA = true;
			for (char c : s) {
				if (c != 'A') allA = false;
			}

			long count = 0;
			for (int i = 0; i < s.length; i++) {
				if (s[i] == 'A') {
					count++;
				} else {
					list.add(count);
					count = 0;
				}
			}
			list.add(count);

			if (allA) {
				out.println((count*n) * (count*n+1) / 2);
				return;
			}

			List<Long> nlist = new ArrayList<>();
			boolean isFirst = true;
			for (int i = 0; i < list.size(); i++) {
				if (isFirst) {
					nlist.add(list.get(list.size()-1) + list.get(i));
					isFirst = false;
				} else {
					if (i == list.size()-1) continue;
					nlist.add(list.get(i));
				}
			}

			long sum = 0, nsum = 0;
			for (int i = 0; i < list.size(); i++) {
				sum += (list.get(i) * (list.get(i)+1)) / 2;
			}
			for (int i = 0; i < nlist.size(); i++) {
				nsum += (nlist.get(i) * (nlist.get(i)+1)) / 2;
			}


			if (s[0] == s[s.length-1] && s[0] == 'A') {
				out.println(sum + nsum * (n-1));
			} else {
				out.println(sum * n);
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
