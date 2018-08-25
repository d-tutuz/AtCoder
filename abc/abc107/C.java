package abc107;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), k = in.nextInt();

			List<Long> list = new ArrayList<>();
			boolean isU = false;
			for (int i = 0; i < n; i++) {
				long x = in.nextLong();
				list.add(x);
				if (x == 0) {
					k--;
					isU = true;
				}
			}

			if (!isU) list.add(0L);
			Collections.sort(list);

			int idx = INF;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == 0) {
					idx = i;
				}
			}

			long ans = LINF;
			if (idx - k >= 0) {
				ans = min(ans, abs(list.get(idx-k)));
			}

			if (idx + k < list.size()) {
				ans = min(ans, abs(list.get(idx+k)));
			}

			for (int i = 1; i <= k; i++) {
				if (idx - i < 0 || idx + (k-i) > list.size()-1) {
					break;
				}

				long F = list.get(idx - i);
				long S = list.get(idx + (k-i));

				ans = min(ans, 2*abs(F)+abs(S));
			}

			for (int i = 1; i <= k; i++) {
				if (idx + i > list.size()-1 || idx - (k-i) < 0) {
					break;
				}

				long S = list.get(idx + i);
				long F = list.get(idx - (k-i));

				ans = min(ans, abs(F)+2*abs(S));
			}

			out.println(ans);

		}
	}

	// other template
	static int min(int a, int b) {
		return Math.min(a, b);
	}

	static long min(long a, long b) {
		return Math.min(a, b);
	}

	static void fill(int[][] a, int value) {
		for (int i = 0; i < a.length; i++) {
			Arrays.fill(a[i], value);
		}
	}

	static void fill(long[][] a, int value) {
		for (int i = 0; i < a.length; i++) {
			Arrays.fill(a[i], value);
		}
	}

	static void fill(char[][] a, char c) {
		for (int i = 0; i < a.length; i++) {
			Arrays.fill(a[i], c);
		}
	}

	static int max(int a, int b) {
		return Math.max(a, b);
	}

	static long max(long a, long b) {
		return Math.max(a, b);
	}

	// faster input template
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
