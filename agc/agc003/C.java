package agc003;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int na = n- n/2;
			int nb = n/2;
			long[] list1 = new long[na];
			long[] list2 = new long[nb];


			for (int i = 0; i < n; i++) {
				long t = in.nextLong();
				if (i % 2 == 0) {
					list1[i/2] = t;
				} else {
					list2[i/2] = t;
				}
			}

			Arrays.sort(list1);
			Arrays.sort(list2);

			long ans1 = 0;
			for (int i = 0; i < nb; i++) {
				long idx = lowerBound(list1, list2[i]);
				ans1 += (long)abs(idx-(i+1));
			}

//			long ans2 = 0;
//			for (int i = 0; i < na; i++) {
//				int idx = lowerBound(list2, list1[i]);
//				ans2 += (long)abs(idx-(i));
//			}
			out.println(min(ans1, INF));
		}
	}

	public static long lowerBound(long[] a, long obj) {
		long l = 0,r = a.length - 1;
		while (r - l >= 0) {
			long c = (l + r) / 2;
			if (obj <= a[(int)c]) {
				r = c - 1;
			} else {
				l = c + 1;
			}
		}
		return l;
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
