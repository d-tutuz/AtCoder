package 第6回日本情報オリンピック本選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
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

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int[] x = new int[n], y = new int[n];

			boolean[][] ok = new boolean[5001][5001];
//			Set<Integer> set = new HashSet<>();
			for (int i = 0; i < n; i++) {
				int xx = in.nextInt(), yy = in.nextInt();
				ok[xx][yy] = true;
				x[i] = xx;
				y[i] = yy;
//				set.add(x[i] * 10000 + y[i]);
			}

			int ans = 0;
			for (int i = 0; i < n; i++) {
				for (int j = i+1; j < n; j++) {
					int x1 = (+1) * (y[i] - y[j]) + x[j];
					int y1 = (-1) * (x[i] - x[j]) + y[j];
					int x2 = (+1) * (y[i] - y[j]) + x[i];
					int y2 = (-1) * (x[i] - x[j]) + y[i];

//					if (isInside(x1, y1) && isInside(x2, y2) && set.contains(x1 * 10000 + y1) && set.contains(x2 * 10000 + y2)) {
					if (isInside(x1, y1) && isInside(x2, y2) && ok[x1][y1] && ok[x2][y2]) {
						ans = Math.max(ans, f(x[i], y[i], x[j], y[j]));
					}
				}
			}

			out.println(ans);
		}

		boolean isInside(int x, int y) {
			return 0 <= x && x <= 5000 && 0 <= y && y <= 5000;
		}

		int f(int x1, int y1, int x2, int y2) {
			return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
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
