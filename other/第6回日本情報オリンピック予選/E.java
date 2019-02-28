package 第6回日本情報オリンピック予選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class E {

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

			int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
			int N = in.nextInt();

			int[] A = new int[a];
			int[] B = new int[b];
			int[] C = new int[c];

			P[] p = new P[N];
			for (int i = 0; i < N; i++) {
				int ai = in.nextInt()-1;
				int bi = in.nextInt()-1-a;
				int ci = in.nextInt()-1-a-b;
				int n = in.nextInt();
				p[i] = new P(ai, bi, ci, n);
			}

			Arrays.sort(p);

			int[] ansA = new int[a];
			int[] ansB = new int[b];
			int[] ansC = new int[c];
			Arrays.fill(ansA, 2);
			Arrays.fill(ansB, 2);
			Arrays.fill(ansC, 2);

			for (int i = 0; i < N; i++) {
				if (p[i].n == 1) {
					A[p[i].a] = 1;
					B[p[i].b] = 1;
					C[p[i].c] = 1;
					ansA[p[i].a] = 1;
					ansB[p[i].b] = 1;
					ansC[p[i].c] = 1;
				}
			}

			for (int i = 0; i < N; i++) {
				if (A[p[i].a] + B[p[i].b] + C[p[i].c] == 2) {
					if (A[p[i].a] == 0) {
						ansA[p[i].a] = 0;
						continue;
					}
					if (B[p[i].b] == 0) {
						ansB[p[i].b] = 0;
						continue;
					}
					if (C[p[i].c] == 0) {
						ansC[p[i].c] = 0;
						continue;
					}
				}
			}

			for (int i : ansA) {
				out.println(i);
			}
			for (int i : ansB) {
				out.println(i);
			}
			for (int i : ansC) {
				out.println(i);
			}

		}

		class P implements Comparable<P> {
			int a, b, c, n;

			public P(int a, int b, int c, int n) {
				super();
				this.a = a;
				this.b = b;
				this.c = c;
				this.n = n;
			}

			@Override
			public int compareTo(P o) {
				return -(this.n - o.n);
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
