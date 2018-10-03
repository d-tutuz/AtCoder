package abc086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class D_2 {

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

		int n, k;
		int[][] sum;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			n = in.nextInt(); k = in.nextInt();
			int[][] mat = new int[4*k+1][4*k+1];
			for (int i = 0; i < n; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				char c = in.nextString().charAt(0);
				if (c == 'W') x += k;
				x %= 2*k;
				y %= 2*k;
				mat[x][y]++;
			}

			sum = new int[4*k+1][4*k+1];
			for (int i = 0; i < 4*k; i++) {
				for (int j = 0; j < 4*k; j++) {
					mat[i][j] = mat[i%(2*k)][j%(2*k)];
				}
			}
			for (int i = 0; i <= 4*k; i++) {
				for (int j = 0; j <= 4*k; j++) {
					sum[i][j] = mat[i][j];
					if (i > 0) sum[i][j] += sum[i-1][j];
					if (j > 0) sum[i][j] += sum[i][j-1];
					if (i > 0 && j > 0) sum[i][j] -= sum[i-1][j-1];
				}
			}

			int ans = 0;
			for (int i = 0; i <= 2*k; i++) {
				for (int j = 0; j <= 2*k; j++) {
					ans = Math.max(ans, get(i, j) + get(i+k, j+k));
				}
			}

			out.println(ans);

		}

		int get(int i, int j) {
			int ret = sum[i+k-1][j+k-1];
			if (i > 0 && j > 0) ret += sum[i-1][j-1];
			if (i > 0) ret -= sum[i-1][j+k-1];
			if (j > 0) ret -= sum[i+k-1][j-1];
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
