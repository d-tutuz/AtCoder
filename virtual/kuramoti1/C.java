package kuramoti1;

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
		static int overRedCount = 0;

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int[] a = in.nextIntArray(n);
			int[] color = new int[n];
			for (int i = 0; i < n; i++) {
				color[i] = getColor(a[i]);
			}

			Set<Integer> set = new HashSet<>();
			for (int i : color) {
				if (i == 0) continue;
				set.add(i);
			}

			int min = -1, max = -1;
			if (set.size() == 0) {
				min = 1;
				max = overRedCount;
			} else {
				min = set.size();
				max = set.size()+overRedCount;
			}

			out.println(min+" "+max);

		}

		static int getColor(int a) {
			int ret = 0;
			if (1 <= a && a <= 399) {
				ret = 1;
			} else if (400 <= a && a <= 799) {
				ret = 2;
			} else if (800 <= a && a <= 1199) {
				ret = 3;
			} else if (1200 <= a && a <= 1599) {
				ret = 4;
			} else if (1600 <= a && a <= 1999) {
				ret = 5;
			} else if (2000 <= a && a <= 2399) {
				ret = 6;
			} else if (2400 <= a && a <= 2799) {
				ret = 7;
			} else if (2800 <= a && a <= 3199) {
				ret = 8;
			} else if (3200 <= a) {
				overRedCount++;
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
