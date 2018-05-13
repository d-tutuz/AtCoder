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

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), k = in.nextInt(), l = in.nextInt();
			DisjointSet road = new DisjointSet(n);
			DisjointSet rail = new DisjointSet(n);

			for (int i = 0; i < k; i++) {
				int x = in.nextInt()-1, y = in.nextInt()-1;
				road.unite(x, y);
			}

			for (int i = 0; i < l; i++) {
				int x = in.nextInt()-1, y = in.nextInt()-1;
				rail.unite(x, y);
			}

			Map<Long, Integer> map = new HashMap<>();
			for (int i = 0; i < n; i++) {
				long key = (long)road.findSet(i) << 32 | rail.findSet(i);
				int count = map.containsKey(key) ? map.get(key) : 0;
				map.put(key, ++count);
			}
			for (int i = 0; i < n; i++) {
				long key = (long)road.findSet(i) << 32 | rail.findSet(i);
				if (i == 0) {
					out.print(map.get(key));
				} else {
					out.print(" "+map.get(key));
				}
			}
			out.print("\n");

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
