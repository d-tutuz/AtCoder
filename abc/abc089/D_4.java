package abc089;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class D_4 {

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
	static int modP = 1000000007;

	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int h = in.nextInt(), w = in.nextInt(), d = in.nextInt();
			P[] p = new P[h*w+1];
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					int a = in.nextInt();
					p[a] = new P(i, j);
				}
			}

			int[] c = new int[h*w+1];
			int[] sum = new int[h*w+1];

			for (int i = 1; i <= d; i++) {
				for (int j = 1; j <= h*w/d; j++) {
					if (i+j*d > h*w) break;
					int f = i+j*d;
					int b = i+(j-1)*d;
					c[f] = Math.abs(p[f].h-p[b].h)+Math.abs(p[f].w-p[b].w);
					sum[f] = sum[b]+c[f];
				}
			}

			int q = in.nextInt();
			for (int i = 0; i < q; i++) {
				int l = in.nextInt(), r = in.nextInt();
				out.println(sum[r]-sum[l]);
			}


		}
	}
	static class P {
		int h, w;

		public P(int x, int y) {
			super();
			this.h = x;
			this.w = y;
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
