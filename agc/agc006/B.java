package agc006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.StringTokenizer;

public class B {

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

			int n = in.nextInt(), x = in.nextInt();
			int t = 2*x-1;
			int[][] s = new int[n][t];

			for (int i = 0; i < t; i++) {
				s[0][i] = i+1;
			}

			Set<Integer> set = new HashSet<>();
			for (int k = 0; k < n; k++) {
				while (Permutation.next(s[k])) {
					for (int i = 0; i < t; i++) {
						out.print(s[k][i]+" ");
					}

					int l = 1, r = t-2;
					for (int i = 1; i < n; i++) {
						for (int j = 1; j < t-1; j++) {
							int[] tmp = {s[i-1][j-1], s[i-1][j], s[i-1][j+1]};
							Arrays.sort(tmp);
							s[i][j] = tmp[1];
						}
					}
					set.add(s[n-1][x-1]);
					out.println(" -> "+s[n-1][x-1]);
				}

			}

			out.println();
			for (Integer integer : set) {
				out.println(integer);
			}

		}
	}

	/**
	 * 順列(N!)
	 *
	 * @input {0 ～ n-1}の数が入った配列
	 * @output 順列に並びられた1回分の配列 a[]
	 *         配列 a[] の中の値が順列される。(次の順列になる)
	 *
	 * @sample
	 * while(Permutation.next(a)) {
	 * 		array[a[i]][a[i+1]]
	 * }
	 *
	 * */
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
