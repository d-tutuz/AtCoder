package 第7回日本情報オリンピック予選;

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

public class F {

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

		int n;
		@SuppressWarnings("unchecked")
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			n = in.nextInt();
			int k = in.nextInt();
			List<P>[] g = new ArrayList[n];
			g = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			while (k-- > 0) {
				int com = in.nextInt();
				if (com == 0) {
					int a = in.nextInt()-1, b = in.nextInt()-1;
					int cost = dijkstra(a, b, g);
					out.println(cost == INF ? -1 : cost);
				} else if (com == 1) {
					int c = in.nextInt()-1;
					int d = in.nextInt()-1;
					int e = in.nextInt();
					g[c].add(new P(d, e));
					g[d].add(new P(c, e));
				}
			}
		}

		int dijkstra(int from, int to, List<P>[] g) {
			PriorityQueue<P> q = new PriorityQueue<>();
			int[] cost = new int[n];
			Arrays.fill(cost, INF);
			cost[from] = 0;
			q.add(new P(from, cost[from]));

			while (!q.isEmpty()) {

				P pp = q.remove();
				for (P p : g[pp.first]) {
					if (cost[pp.first] + p.second < cost[p.first]) {
						cost[p.first] = cost[pp.first] + p.second;
						q.add(new P(p.first, cost[p.first]));
					}
				}

			}

			return cost[to];
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


	static void fill(int[][] a, int v) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				a[i][j] = v;
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
