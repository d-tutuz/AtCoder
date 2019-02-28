package 第13回日本情報オリンピック予選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class E_2 {

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

		int n, k;
		int[] c, r;
		List<Integer>[] g;
		Set<Integer>[] h;
		@SuppressWarnings("unchecked")
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			n = in.nextInt(); k = in.nextInt();
			c = new int[n]; r = new int[n];
			for (int i = 0; i < n; i++) {
				c[i] = in.nextInt();
				r[i] = in.nextInt();
			}

			g = new ArrayList[n];
			g = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			for (int i = 0; i < k; i++) {
				int a = in.nextInt()-1, b = in.nextInt()-1;
				g[a].add(b);
				g[b].add(a);
			}

			h = new HashSet[n];
			h = Stream.generate(HashSet::new).limit(n).toArray(Set[]::new);

			for (int i = 0; i < n; i++) {
				bfs(i);
			}
//			boolean[] visited = new boolean[n];
//			Queue<P> q = new ArrayDeque<>();
//			for (int i = 0; i < n; i++) {
//				q.add(new P(i, r[i]));
//			}
//
//			while (!q.isEmpty()) {
//				P p = q.remove();
//				int now = p.first;
//				for (int to : g[now]) {
//					if (visited[to] || p.second <= 0) continue;
//					q.add(new P(to, p.second-1));
//					h[now].add(to);
//					visited[to] = true;
//				}
//			}

			PriorityQueue<P> pq = new PriorityQueue<>();
			int[] cost = new int[n];
			Arrays.fill(cost, INF);
			cost[0] = 0;
			pq.add(new P(0, 0));

			while (!pq.isEmpty()) {

				P pp = pq.remove();
				for (int to : h[pp.first]) {
					if (cost[pp.first] + c[pp.first] < cost[to]) {
						cost[to] = cost[pp.first] + c[pp.first];
						pq.add(new P(to, cost[to]));
						if (cost[n-1] != INF) break;
					}
				}
			}

			out.println(cost[n-1]);

		}

		void bfs(int s) {

			boolean[] visited = new boolean[n];
			visited[s] = true;
			Queue<P> q = new ArrayDeque<>();
			q.add(new P(s, r[s]));

			while (!q.isEmpty()) {
				P p = q.remove();
				int now = p.first;
				for (int to : g[now]) {
					if (visited[to] || p.second <= 0) continue;
					q.add(new P(to, p.second-1));
					h[s].add(to);
					visited[to] = true;
				}
			}
		}

		class P implements Comparable<P> {
			int first, second;

			public P(int first, int second) {
				super();
				this.first = first;
				this.second = second;
			}

			@Override
			public String toString() {
				return "P [first=" + first + ", second=" + second + "]";
			}

			@Override
			public int compareTo(P o) {
				return Integer.compare(this.second, o.second);
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + first;
				result = prime * result + second;
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				P other = (P) obj;
				if (first != other.first)
					return false;
				if (second != other.second)
					return false;
				return true;
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
