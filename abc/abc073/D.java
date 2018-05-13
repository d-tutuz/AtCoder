package abc073;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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

	static int INF = 1 << 28;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int m = in.nextInt();
			int r = in.nextInt();
			int[] rn = in.nextIntArray(r);
			for (int i = 0; i < r; i++) {
				rn[i]--;
			}

			int[][] d = new int[n][n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(d[i], INF);
			}
			for (int i = 0; i < n; i++) {
				d[i][i] = 0;
			}
			for (int i = 0; i < m; i++) {
				int a = in.nextInt() - 1;
				int b = in.nextInt() - 1;
				int c = in.nextInt();
				d[a][b] = c;
				d[b][a] = c;
			}

			warshallFloyd(d);

			int cost = INF;
			int[] a = new int[r];
			for (int i = 0; i < r; i++) {
				a[i] = i;
			}
			while (Permutation.next(a)) {
				int tmp = 0;
				for (int j = 0; j < r - 1; j++) {
					tmp += d[rn[a[j]]][rn[a[j+1]]];
				}
				cost = Math.min(cost, tmp);
			}
			out.println(cost);

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

	static void warshallFloyd(int[][] d) {
		int INF = 1 << 28;
		int len = d.length;
		for (int k = 0; k < len; k++) {
			for (int i = 0; i < len; i++) {
				if (d[i][k] == INF)
					continue;
				for (int j = 0; j < len; j++) {
					if (d[k][j] == INF)
						continue;
					d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
				}
			}
		}
	}

	public static List<String> permute(char[] ary, int startIndex,
			int endIndex, List<String> list) {
		if (startIndex == endIndex) {
			list.add(String.valueOf(ary));
		} else {
			for (int i = startIndex; i <= endIndex; i++) {
				swap(ary, startIndex, i);
				permute(ary, startIndex + 1, endIndex, list);
				swap(ary, startIndex, i);
			}
		}
		return list;
	}

	public static void swap(char[] ary, int x, int y) {
		char temp = ary[x];
		ary[x] = ary[y];
		ary[y] = temp;
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
