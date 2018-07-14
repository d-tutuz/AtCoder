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

			int n = in.nextInt(), m = in.nextInt();

			PriorityQueue<P> q = new PriorityQueue<>();
			for (int i = 0; i < n; i++) {
				long cost = in.nextLong();
				q.add(new P(i, cost));
			}

			DisjointSet set = new DisjointSet(n);
			for (int i = 0; i < m; i++) {
				int x = in.nextInt(), y = in.nextInt();
				set.unite(x, y);
			}

			long ans = 0;
			boolean[] used = new boolean[n];
			P now = null, next = null;
			while (q.size() >= 2) {
				now = q.remove();
				next = q.remove();

				while (set.same(now.num, next.num) && !q.isEmpty()) {
					next = q.remove();
				}

				// ここに来るときは連結でない
				if (!set.same(now.num, next.num)) {
					set.unite(now.num, next.num);
					ans += now.c + next.c;
					used[now.num] = true;
					used[next.num] = true;
				}
			}

			boolean ok = true;
			while (!q.isEmpty()) {
				next = q.remove();
				if (now == null || next == null) {
					continue;
				}

				if (used[now.num] || used[next.num]) {
					continue;
				}

				if (!set.same(now.num, next.num)) {
					set.unite(now.num, next.num);
					ans += now.c + next.c;
					used[now.num] = true;
					used[next.num] = true;
				}
			}

			for (int i = 1; i < n; i++) {
				if (set.same(0, i)) {
					continue;
				} else {
					ok = false;
					break;
				}
			}

			out.println(ok ? ans : "Impossible");
		}

		class P implements Comparable<P> {
			int num;
			long c;

			public P(int num, long c) {
				this.num = num;
				this.c = c;
			}

			@Override
			public int compareTo(P o) {
				return (int)(this.c - o.c);
			}

		}

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

			public void link(int x, int y) {
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
