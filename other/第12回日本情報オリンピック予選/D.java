package 第12回日本情報オリンピック予選;

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

public class D {

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

			int D = in.nextInt(), N = in.nextInt();
			int[] t = in.nextIntArray(D);
			int[] a = new int[N], b = new int[N], c = new int[N];
			for (int i = 0; i < N; i++) {
				a[i] = in.nextInt();
				b[i] = in.nextInt();
				c[i] = in.nextInt();
			}

			List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
			int tmp = 0;
			for (int j = 0; j < N; j++) {
				if (a[j] <= t[0] && t[0] <= b[j]) {
					tmp = max(tmp, c[j]);
				}
			}
			list1.add(tmp);

			tmp = INF;
			for (int j = 0; j < N; j++) {
				if (a[j] <= t[0] && t[0] <= b[j]) {
					tmp = min(tmp, c[j]);
				}
			}
			list2.add(tmp);

			for (int i = 1; i < D; i++) {
				tmp = -INF;
				int add = 0;
				for (int j = 0; j < N; j++) {
					if (a[j] <= t[i] && t[i] <= b[j]) {
						if (tmp < abs(c[j] - list1.get(list1.size()-1))) {
							tmp = abs(c[j] - list1.get(list1.size()-1));
							add = c[j];
						}
					}
				}
				list1.add(add);
			}

			for (int i = 1; i < D; i++) {
				tmp = -INF;
				int add = 0;
				for (int j = 0; j < N; j++) {
					if (a[j] <= t[i] && t[i] <= b[j]) {
						if (tmp < abs(c[j] - list2.get(list2.size()-1))) {
							tmp = abs(c[j] - list2.get(list2.size()-1));
							add = c[j];
						}
					}
				}
				list2.add(add);
			}

			int c1 = 0, c2 = 0;
			for (int i = 0; i < list1.size()-1; i++) {
				c1 += abs(list1.get(i) - list1.get(i+1));
				c2 += abs(list2.get(i) - list2.get(i+1));
			}

			out.println(max(c1, c2));
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
