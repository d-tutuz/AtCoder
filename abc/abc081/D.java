package abc081;

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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int[] a = new int[n];
			int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				int t = in.nextInt();
				a[i] = t;
				min = Math.min(min, t);
				max = Math.max(max, t);
			}

			List<P> ans = new ArrayList<>();

			boolean isPositive = Math.abs(max) >= Math.abs(min);
			int idx = -1;
			int tar = isPositive ? max : min;
			for (int i = 0; i < n; i++) {
				if (tar == a[i]) {
					idx = i;
					break;
				}
			}
			if (isPositive) {
				for (int i = 0; i < n; i++) {
					ans.add(new P(idx, i));
				}
				for (int i = 1; i < n; i++) {
					ans.add(new P(i-1, i));
				}

			} else {
				for (int i = 0; i < n; i++) {
					ans.add(new P(idx, i));
				}

				for (int i = n-1; i > 0; i--) {
					ans.add(new P(i, i-1));
				}
			}

			out.println(ans.size());
			for (P p : ans) {
				out.println((p.x+1) +" "+ (p.y+1));
			}
		}
	}

	static class P {
		int x, y;

		public P(int x, int y) {
			super();
			this.x = x;
			this.y = y;
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
