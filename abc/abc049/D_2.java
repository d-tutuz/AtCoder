package abc049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), k = in.nextInt(), l = in.nextInt();

			DisjointSet set1 = new DisjointSet(n);
			for (int i = 0; i < k; i++) {
				int p = in.nextInt()-1;
				int q = in.nextInt()-1;
				set1.unite(p, q);
			}

			DisjointSet set2 = new DisjointSet(n);
			for (int i = 0; i < l; i++) {
				int r = in.nextInt()-1;
				int s = in.nextInt()-1;
				set2.unite(r, s);
			}

			Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();
			for (int i = 0; i < n; i++) {
				map.merge(new Pair<Integer, Integer>(set1.findSet(i), set2.findSet(i)), 1, Integer::sum);
			}

			for (int i = 0; i < n; i++) {
				int ans = map.get(new Pair<Integer, Integer>(set1.findSet(i), set2.findSet(i)));
				if (i > 0) {
					out.print(" "+ans);
				} else {
					out.print(ans);
				}
			}
			out.print("\n");
		}

		public static class Pair<U, V> implements Comparable<Pair<U, V>> {

			public final U first;
			public final V second;

			public static <U, V> Pair<U, V> makePair(U first, V second) {
				return new Pair<U, V>(first, second);
			}

			private Pair(U first, V second) {
				this.first = first;
				this.second = second;
			}

			public boolean equals(Object o) {
				if (this == o) {
					return true;
				}
				if (o == null || getClass() != o.getClass()) {
					return false;
				}

				Pair pair = (Pair) o;

				return !(first != null ? !first.equals(pair.first)
						: pair.first != null)
						&& !(second != null ? !second.equals(pair.second)
								: pair.second != null);
			}

			public int hashCode() {
				int result = first != null ? first.hashCode() : 0;
				result = 31 * result + (second != null ? second.hashCode() : 0);
				return result;
			}

			public String toString() {
				return "(" + first + "," + second + ")";
			}

			public int compareTo(Pair<U, V> o) {
				int value = ((Comparable<U>) first).compareTo(o.first);
				if (value != 0) {
					return value;
				}
				return ((Comparable<V>) second).compareTo(o.second);
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
