package tenka1programmercontest_2018;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.StringTokenizer;

public class C_2 {

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

			for (int k = 0; k < 1000; k++) {
				int n = 7;
				Random r = new Random();
				int[] a = new int[n];
				for (int i = 0; i < n; i++) {
//					a[i] = r.nextInt((int)Math.pow(10, 9));
					a[i] = r.nextInt(20);
				}

//				out.println(n);
//				for (int i = 0; i < n; i++) {
//					if (i > 0) out.print(" ");
//					out.print(a[i]);
//				}
//				out.print("\n");

				Arrays.sort(a);

				long ans = 0;
				do {
					long tmp = 0;
					for (int i = 0; i < n-1; i++) {
						tmp += abs(a[i+1] - a[i]);
					}

					ans = Math.max(ans, tmp);
				} while (Permutation.next(a));

//				out.println(ans);

				//////////////////////////////

				Deque<Long> from = new ArrayDeque<Long>();
				Deque<Long> to = new ArrayDeque<Long>();

				Arrays.sort(a);
				for (long i : a) {
					from.addLast(i);
				}

				to.addFirst(from.removeFirst());

				int count = 0;
				while (!from.isEmpty()) {
					if (count % 2 == 0) {
						if (!from.isEmpty()) {
							long num = from.removeLast();
							to.addFirst(num);
						}
						if (!from.isEmpty()) {
							long num = from.removeLast();
							to.addLast(num);
						}
					} else {
						if (!from.isEmpty()) {
							long num = from.removeFirst();
							to.addFirst(num);
						}
						if (!from.isEmpty()) {
							long num = from.removeFirst();
							to.addLast(num);
						}
					}
					count++;
				}

				long[] b = new long[n];
				for (int i = 0; i < n; i++) {
					b[i] = to.removeFirst();
				}

				long ans2 = 0;
				for (int i = 0; i < n-1; i++) {
					ans2 += abs(b[i+1] - b[i]);
				}
				long ans3 = 0;
				for (int i = 0; i < n-1; i++) {
					ans3 += abs(a[i+1] - a[i]);
				}
				ans2 = max(ans2, ans3);
//				out.println(ans2);

				if (ans != ans2) {
					out.println("Error");
					out.println(n);
					for (int i = 0; i < n; i++) {
						if (i > 0) out.print(" ");
						out.print(a[i]);
					}
					out.print("\n");
					out.println("ans :"+ ans);
					out.println("ans2:"+ ans2);

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

		private static void swap(char[] a, int i, int j) {
			char tmp = a[i];
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

		public int[] nextIntArrayDec(int n) {
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt() - 1;
			}
			return res;
		}

		public int[] nextIntArray1Index(int n) {
			int[] res = new int[n + 1];
			for (int i = 0; i < n; i++) {
				res[i + 1] = nextInt();
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

		public long[] nextLongArray1Index(int n) {
			long[] res = new long[n + 1];
			for (int i = 0; i < n; i++) {
				res[i + 1] = nextLong();
			}
			return res;
		}

		public double[] nextDoubleArray(int n) {
			double[] res = new double[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextDouble();
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
