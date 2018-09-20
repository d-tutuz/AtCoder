package abc035;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

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
	static long LINF = 1L << 50;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	@SuppressWarnings("unchecked")
	static class TaskX {

		List<P>[] ord, rev;
		int n, m, t;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			n = in.nextInt(); m = in.nextInt();
			long t = in.nextLong();
			long[] a = in.nextLongArray(n);

			ord = new ArrayList[n];
			ord = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			rev = new ArrayList[n];
			rev = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			for (int i = 0; i < m; i++) {
				int from = in.nextInt()-1, to = in.nextInt()-1;
				int c = in.nextInt();
				ord[from].add(new P(to, c));
				rev[to].add(new P(from, c));
			}

			long[] c1 = new long[n], c2 = new long[n];

			dijkstra(c1, 0, ord);
			dijkstra(c2, 0, rev);

			long ans = 0;

			for (int i = 0; i < n; i++) {
				long time = t - c1[i] - c2[i];
				ans = Math.max(ans, a[i] * time);
			}

			out.println(ans);

		}

		void dijkstra(long[] cost, int s, List<P>[] g) {

			Arrays.fill(cost, INF);
			cost[s] = 0;

			PriorityQueue<Integer> q = new PriorityQueue<>();
			q.add(s);

			while (!q.isEmpty()) {
				int now = q.remove();

				for (P p : g[now]) {
					if (cost[now] + p.c < cost[p.t]) {
						cost[p.t] = cost[now] + p.c;
						q.add(p.t);
					}
				}
			}
		}
	}

	static class P implements Comparable<P> {
		int t;
		long c;

		public P(int t, long c) {
			super();
			this.t = t;
			this.c = c;
		}

		@Override
		public String toString() {
			return "P [t=" + t + ", c=" + c + "]";
		}

		@Override
		public int compareTo(P o) {
			return Long.compare(this.c, o.c);
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
