package 第15回日本情報オリンピック予選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

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

		@SuppressWarnings("unchecked")
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt();
			int k = in.nextInt(), s = in.nextInt();
			int P = in.nextInt(), Q = in.nextInt();
			int[] c = in.nextIntArrayDec(k);

			List<Integer>[] g = new ArrayList[n];
			g = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);
			int[] a = new int[m], b = new int[m];
			for (int i = 0; i < m; i++) {
				a[i] = in.nextInt()-1;
				b[i] = in.nextInt()-1;
				g[a[i]].add(b[i]);
				g[b[i]].add(a[i]);
			}

			boolean[] dan = new boolean[n], ex = new boolean[n];
			Queue<P> q = new ArrayDeque<>();
			for (int i = 0; i < k; i++) {
				q.add(new P(c[i], s));
				ex[c[i]] = true;
			}

			while (!q.isEmpty()) {
				P p = q.remove();
				int now = p.i;
				if (dan[now]) continue;
				dan[now] = true;

				if (p.j <= 0) continue;
				for (int to : g[now]) {
					if (dan[to]) continue;
					q.add(new P(to, p.j-1));
				}
			}

			List<P>[] h = new ArrayList[n];
			h = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			for (int i = 0; i < m; i++) {
				h[a[i]].add(new P(b[i], !dan[b[i]] ? (b[i] == n-1 ? 0 : P) : (b[i] == n-1 ? 0 : Q)));
				h[b[i]].add(new P(a[i], !dan[a[i]] ? (a[i] == n-1 ? 0 : P) : (a[i] == n-1 ? 0 : Q)));
			}

			long[] cost = new long[n];
			Arrays.fill(cost, LINF);
			cost[0] = 0;
			PriorityQueue<P> pq = new PriorityQueue<>();
			pq.add(new P(0, 0));
			while (!pq.isEmpty()) {
				P pp = pq.remove();
				int now = pp.i;

				if (ex[now]) continue;

				for (P p : h[now]) {
					int to = p.i;
					if (cost[now] + p.j < cost[to]) {
						cost[to] = cost[now] + p.j;
						pq.add(new P(to, cost[to]));
					}
				}
			}

			out.println(cost[n-1]);
		}
	}

	static class P implements Comparable<P> {
		int i;
		long j;

		public P(int i, long j) {
			super();
			this.i = i;
			this.j = j;
		}

		@Override
		public int compareTo(P o) {
			return Long.compare(this.j, o.j);
		}

		@Override
		public String toString() {
			return "P [i=" + i + ", j=" + j + "]";
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
