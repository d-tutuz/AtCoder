package abc097;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
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
	static int modP = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), k = in.nextInt();
			DisjointSet set = new DisjointSet(n);

			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = in.nextInt()-1;
			}

			for (int i = 0; i < k; i++) {
				int x = in.nextInt()-1, y = in.nextInt()-1;
				set.unite(x, y);
			}

			int count = 0;
			for (int i = 0; i < n; i++) {
				if (set.same(a[i], i)) {
					count++;
				}
			}

			out.println(count);

		}

		public class DisjointSet {

			int[] p, rank;

			public DisjointSet(int size) {
				p = new int[size];
				rank = new int[size];

				for (int j = 0; j < size; j++) {
					makeSet(j);
				}
			}

			private void makeSet(int x) {
				p[x] = x;
				rank[x] = 0;
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
			}

			public void unite(int x, int y) {
				link(findSet(x), findSet(y));
			}

			public boolean same(int x, int y) {
				return findSet(x) == findSet(y);
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
