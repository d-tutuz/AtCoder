package 技術室奥プログラミングコンテスト;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
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

			int h = in.nextInt(), w = in.nextInt(), q = in.nextInt();
			long[] a = new long[h+1];
			long[] b = new long[w+1];
			long[] sumHB = new long[h+1];
			long[] sumHW = new long[h+1];
			long[] sumWB = new long[w+1];
			long[] sumWW = new long[w+1];

			for (int i = 1; i <= h; i++) {
				long t = in.nextLong();
				a[i] = t;
				if (i % 2 == 1) {
					sumHB[i] = sumHB[i-1] + t;
					sumHW[i] = sumHW[i-1];
				} else {
					sumHW[i] = sumHW[i-1] + t;
					sumHB[i] = sumHB[i-1];
				}
			}

			for (int i = 1; i <= w; i++) {
				long t = in.nextLong();
				b[i] = t;
				if (i % 2 == 1) {
					sumWB[i] = sumWB[i-1] + t;
					sumWW[i] = sumWW[i-1];
				} else {
					sumWW[i] = sumWW[i-1] + t;
					sumWB[i] = sumWB[i-1];
				}
			}

			for (int i = 0; i < q; i++) {
				int sx = in.nextInt(), sy = in.nextInt();
				int tx = in.nextInt(), ty = in.nextInt();

				long dh = (sumHW[tx]-sumHW[sx-1])-(sumHB[tx]-sumHB[sx-1]);
				long dw = (sumWW[ty]-sumWW[sy-1])-(sumWB[ty]-sumWB[sy-1]);

				out.println(dh*dw);
			}

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
