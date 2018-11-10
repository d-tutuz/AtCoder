package abc077;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

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

	static int INF = 1 << 30;
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		@SuppressWarnings("unchecked")
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int k = in.nextInt();

			List<E>[] g = new ArrayList[k];
			g = Stream.generate(ArrayList::new).limit(k).toArray(List[]::new);

			for (int i = 0; i < k; i++) {
				g[i].add(new E((i+1)%k, 1));
				if (i != (10*i)%k) {
					g[i].add(new E((10*i)%k, 0));
				}
			}

			Deque<E> q = new ArrayDeque<>();
			q.add(new E(1, 1));

			int[] cost = new int[k];
			Arrays.fill(cost, INF);
			cost[1] = 1;

			while (!q.isEmpty()) {
				E ee = q.removeFirst();
				int now = ee.t;

				for (E e : g[now]) {
					if (cost[now] + e.c < cost[e.t]) {
						cost[e.t] = cost[now] + e.c;
						if (e.c == 0) {
							q.addFirst(e);
						} else {
							q.addLast(e);
						}
					}
				}
			}

			out.println(cost[0]);

		}
	}

	static class E implements Comparable<E> {
		int t, c;

		public E(int t, int c) {
			super();
			this.t = t;
			this.c = c;
		}

		@Override
		public int compareTo(E o) {
			return Long.compare(this.c, o.c);
		}

		@Override
		public String toString() {
			return "E [t=" + t + ", c=" + c + "]";
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
