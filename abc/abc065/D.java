package abc065;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
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

			int  n = in.nextInt();
			P[] p = new P[n];
			Q[] q = new Q[n];

			for (int i = 0; i < n; i++) {
				long x = in.nextLong(), y = in.nextLong();
				p[i] = new P(i, x, y);
				q[i] = new Q(i, x, y);
			}

			Arrays.sort(p);
			Arrays.sort(q);

			List<E> list = new ArrayList<>();
			for (int i = 1; i < n; i++) {
				P f = p[i-1];
				P t = p[i];
				list.add(new E(f.i, t.i, f(f, t)));
			}
			for (int i = 1; i < n; i++) {
				Q f = q[i-1];
				Q t = q[i];
				list.add(new E(f.i, t.i, f(f, t)));
			}

			Collections.sort(list);

			long cost = 0;
			DisjointSet set = new DisjointSet(n);

			for (int i = 0; i < list.size(); i++) {
				int f = list.get(i).f;
				int t = list.get(i).t;
				long c = list.get(i).c;
				if (!set.same(f, t)) {
					cost += c;
					set.unite(f, t);
				}
			}

			out.println(cost);

		}

		/**
		 * DisjointSet
		 * */
		public class DisjointSet {

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

		long f(P p1, P p2) {
			return Math.min(Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y));
		}
		long f(Q p1, Q p2) {
			return Math.min(Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y));
		}


		class E implements Comparable<E> {
			int f;
			int t;
			long c;

			public E(int f, int t, long c) {
				super();
				this.f = f;
				this.t = t;
				this.c = c;
			}

			@Override
			public int compareTo(E o) {
				return (int)(this.c - o.c);
			}

			@Override
			public String toString() {
				return "E [f=" + f + ", t=" + t + ", c=" + c + "]";
			}

		}

		class P implements Comparable<P> {

			int i;
			long x, y;

			public P(int i, long x, long y) {
				super();
				this.i = i;
				this.x = x;
				this.y = y;
			}

			@Override
			public int compareTo(P o) {
				return (int)(this.x - o.x);
			}

			@Override
			public String toString() {
				return "Q [i=" + i + ", x=" + x + ", y=" + y + "]";
			}

		}

		class Q implements Comparable<Q> {

			int i;
			long x, y;

			public Q(int i, long x, long y) {
				super();
				this.i = i;
				this.x = x;
				this.y = y;
			}

			@Override
			public int compareTo(Q o) {
				return (int)(this.y - o.y);
			}

			@Override
			public String toString() {
				return "Q [i=" + i + ", x=" + x + ", y=" + y + "]";
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
