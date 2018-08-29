package abc082;

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

			int os = 10000;
			int max = 8001;

			String[] s = in.nextString().split("T");
			int gx = in.nextInt(), gy = in.nextInt();

			List<Integer> xlist = new ArrayList<>();
			List<Integer> ylist = new ArrayList<>();
			for (int i = 0; i < s.length; i++) {
				if (i % 2 == 0) {
					xlist.add(s[i].length());
				} else {
					ylist.add(s[i].length());
				}
			}

			boolean[][] dpx = new boolean[max][max+os];
			boolean[][] dpy = new boolean[max][max+os];

			dpx[0][0+os] = true;
			dpy[0][0+os] = true;

			for (int i = 0; i < xlist.size(); i++) {
				if (i == 0) {
					dpx[i+1][xlist.get(i)+os] = true;
					continue;
				}

				for (int j = -max+os; j < max+os; j++) {
					if (dpx[i][j]) {
						dpx[i+1][j+xlist.get(i)] = true;
						dpx[i+1][j-xlist.get(i)] = true;
					}
				}
			}

			for (int i = 0; i < ylist.size(); i++) {
				for (int j = -max+os; j < max+os; j++) {
					if (dpy[i][j]) {
						dpy[i+1][j+ylist.get(i)] = true;
						dpy[i+1][j-ylist.get(i)] = true;
					}
				}
			}

			if (dpx[xlist.size()][gx+os] && dpy[ylist.size()][gy+os]) {
				out.println("Yes");
			} else {
				out.println("No");
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

		public int[] nextIntArrayDec(int n) {
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt() - 1;
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

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
