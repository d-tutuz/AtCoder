package abc051;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.StringTokenizer;

public class D_3 {

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
	static int modP = 1000000007;


	static int maxN = 100;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			Set<Long> set = new HashSet<>();
			int N = in.nextInt(), M = in.nextInt();

			int[][] d = new int[maxN][maxN];
			E[] e = new E[M];
			for (int i = 0; i < maxN; i++) {
				for (int j = 0; j < maxN; j++) {
					if (i == j) {
						d[i][j] = 0;
					} else {
						d[i][j] = INF;
					}
				}
			}

			for (int i = 0; i < M; i++) {
				int a = in.nextInt()-1, b = in.nextInt()-1, c = in.nextInt();
				d[a][b] = c;
				d[b][a] = c;
				e[i] = new E(a, b, c);

				set.add((long)a << 32 | b);
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
					}
				}
			}

			for (int i = 0; i < M; i++) {
				int f = e[i].f;
				int t = e[i].t;
				int c = e[i].c;

				for (int k = 0; k < N; k++) {
					if (d[f][k] == c + d[t][k]) {
						set.remove((long)f<<32 | t);
						break;
					}
				}
			}

			out.println(set.size());


		}
	}
	static class E {
		int f, t, c;
		E (int f, int t, int c) {
			this.f = f;
			this.t = t;
			this.c = c;
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
