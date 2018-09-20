package colocon_coloplprogrammingcontest2018;

import static java.lang.Math.*;

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
import java.util.TreeSet;

public class C_3 {

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

			TreeSet<Long> set = new TreeSet<>();

			long[] c = new long[37];
			long a = in.nextLong(), b = in.nextLong();
			for (long i = a; i <= b; i++) {
				if (i % 2 == 0) c[2]++;
				if (i % 3 == 0) c[3]++;
				if (i % 6 == 0) c[6]++;

				if (i % 2 != 0 && i % 3 != 0) set.add(i);
			}

			Long[] arr = new Long[set.size()];
			set.toArray(arr);

			int n = arr.length;

			for (int i = 0; i < 1 << n; i++) {
				List<Long> list = new ArrayList<>();
				for (int j = 0; j < n; j++) {
					if ((i >> j & 1) == 1) {
						list.add(arr[j]);
					}
				}
				if (check(list)) c[0]++;
			}

			long ans = 0;
			ans += (pow(2, c[2]) - 1 == 0 ? 1 : pow(2, c[2]) - 1) * (c[0] == 0 ? 1 : c[0]);
			ans += (pow(2, c[3]) - 1 == 0 ? 1 : pow(2, c[3]) - 1) * (c[0] == 0 ? 1 : c[0]);
			ans -= (pow(2, c[6]) - 1 == 0 ? 1 : pow(2, c[6]) - 1) * (c[0] == 0 ? 1 : c[0]);
			ans += c[0];
			ans ++;

			out.println(ans);
		}

		boolean check(List<Long> a) {
			int n = a.size();
			if (n == 0) return false;

			boolean[] ok = new boolean[37];
			for (int i = 0; i < n; i++) {
				for (int j = 4; j <= 36; j++) {
					if (a.get(i) % j != 0) continue;

					if (!ok[j]) {
						ok[j] = true;
					} else {
						return false;
					}
				}
			}

			return true;

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
