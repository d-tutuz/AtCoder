package soundhoundinc_programmingcontest_2018_spring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class C {

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

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int r = in.nextInt(), c = in.nextInt();
			char[][] map1 = new char[r][c];
			for (int i = 0; i < r; i++) {
				map1[i] = in.nextString().toCharArray();
			}

			long c1 = 0;
			// #.#.#
			// .#.#.
			// #.#.#
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (i % 2 == 0 && j % 2 == 0) {
						if (map1[i][j] == '.') c1++;
					} else if (i % 2 == 1 && j % 2 == 1) {
						if (map1[i][j] == '.') c1++;
					}
				}
			}

			long c2 = 0;
			// .#.#.
			// #.#.#
			// .#.#.
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (i % 2 == 0 && j % 2 == 1) {
						if (map1[i][j] == '.') c2++;
					} else if (i % 2 == 1 && j % 2 == 0) {
						if (map1[i][j] == '.') c2++;
					}
				}
			}

			out.println(Math.max(c1, c2));

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
