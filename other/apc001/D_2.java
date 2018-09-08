package apc001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	@SuppressWarnings("unchecked")
	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt();
			int[] a = in.nextIntArray(n);

			if (n - 1 == m) {
				out.println(0);
				return;
			}

			DisjointSet set = new DisjointSet(n);

			int group = n;
			for (int i = 0; i < m; i++) {
				int x = in.nextInt(), y = in.nextInt();
				if (x == y) continue;
				set.unite(x, y);
				group--;
			}

			long ans = 0, need = 2 * (group - 1);

			// 各groupの最小値の頂点とコストを求める
			boolean[] used = new boolean[n];
			PriorityQueue<P>[] pq = new PriorityQueue[n];
			pq = Stream.generate(PriorityQueue::new).limit(n).toArray(PriorityQueue[]::new);

			for (int i = 0; i < n; i++) {
				pq[set.findSet(i)].add(new P(i, a[i]));
			}

			for (int i = 0; i < n; i++) {
				if (!pq[i].isEmpty()) {
					P p = pq[i].remove();
					used[p.i] = true;
					ans += p.cost;
					need--;
				}
			}

			// その他から need 分の頂点を選ぶ
			PriorityQueue<Integer> other = new PriorityQueue<Integer>();
			for (int i = 0; i < n; i++) {
				if (!used[i]) {
					other.add(a[i]);
				}
			}

			while (need > 0) {
				if (other.isEmpty()) {
					out.println("Impossible");
					return;
				}
				ans += other.remove();
				need--;
			}

			out.println(ans);
		}

		class P implements Comparable<P> {
			int i, cost;

			public P(int i, int cost) {
				super();
				this.i = i;
				this.cost = cost;
			}

			@Override
			public int compareTo(P o) {
				return this.cost - o.cost;
			}

			@Override
			public String toString() {
				return "P [i=" + i + ", cost=" + cost + "]";
			}

		}

		/**
		 * DisjointSet
		 * */
		public static class DisjointSet {

			int[] p, rank, cnt;

			public DisjointSet(int size) {
				p = new int[size];
				rank = new int[size];
				cnt = new int[size];

				for (int j = 0; j < size; j++) {
					makeSet(j);
				}
			}

			private void makeSet(int x) {
				p[x] = x;
				rank[x] = 0;
				cnt[x] = 1;
			}

			public int findSet(int x) {
				return p[x] == x ? x : findSet(p[x]);
			}

			private void link(int x, int y) {
				if (rank[x] > rank[y]) {
					p[y] = x;
				} else if (rank[x] < rank[y]) {
					p[x] = y;
				} else if (rank[x] == rank[y]) {
					p[x] = y;
					rank[y]++;
				}

				if (x != y) {
					cnt[x] = cnt[y] += cnt[x];
				}
			}

			public void unite(int x, int y) {
				link(findSet(x), findSet(y));
			}

			public boolean same(int x, int y) {
				return findSet(x) == findSet(y);
			}

			public int getSize(int x) {
				return cnt[findSet(x)];
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

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

	static long max(long... n) {
		long ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.max(ret, n[i]);
		}
		return ret;
	}

	static int max(int... n) {
		int ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.max(ret, n[i]);
		}
		return ret;
	}

	static long min(long... n) {
		long ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.min(ret, n[i]);
		}
		return ret;
	}

	static int min(int... n) {
		int ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.min(ret, n[i]);
		}
		return ret;
	}

}
