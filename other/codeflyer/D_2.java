package codeflyer;

import static java.lang.Math.*;

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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int h = in.nextInt(), w = in.nextInt();
			int n = in.nextInt(), m = in.nextInt();
			char[][] s = new char[n][m];
			for (int i = 0; i < n; i++) {
				s[i] = in.nextString().toCharArray();
			}

			int[][] imos = new int[min(h+1, 2*n+2)][min(w+1, 2*m+2)];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (s[i][j] == '#') {
						int lenH = imos.length-1;
						int lenW = imos[0].length-1;
						imos[i][j]++;
						imos[i][j + lenW-m+1]--;
						imos[i + lenH-n+1][j]--;
						imos[i + lenH-n+1][j + lenW-m+1]++;
					}
				}
			}

			for (int i = 0; i < imos.length; i++) {
				for (int j = 1; j < imos[0].length; j++) {
					imos[i][j] += imos[i][j-1];
				}
			}

			for (int i = 1; i < imos.length; i++) {
				for (int j = 0; j < imos[0].length; j++) {
					imos[i][j] += imos[i-1][j];
				}
			}

			long ans = 0;
			for (int i = 0; i < imos.length-1; i++) {
				for (int j = 0; j < imos[0].length-1; j++) {
					if (imos[i][j] > 0) {
						long p = 1;
						long q = 1;

						if (2*n < h && i == n) {
							p = h - 2*n;
						}
						if (2*m < w && j == m) {
							q = w - 2*m;
						}

						ans += p*q;
					}
				}
			}

			out.println(ans);
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
