package soundhoundinc_programmingcontest_2018_spring;

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
import java.util.StringTokenizer;

public class C_3 {

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
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int r = in.nextInt(), c = in.nextInt();
			char[][] s = new char[r][c];
			for (int i = 0; i < r; i++) {
				s[i] = in.nextString().toCharArray();
			}

			int[] mr4 = { 0, -1, 1, 0 };
			int[] mc4 = { -1, 0, 0, 1 };
			Dinic dinic = new Dinic(1610);

			int n = 0;
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (s[i][j] == '*') continue;
					n++;

					if ((i + j) % 2 == 0) {
						for (int k = 0; k < 4; k++) {
							int ti = i + mr4[k];
							int tj = j + mc4[k];
							if (ti < 0 || tj < 0 || ti >= r || tj >= c) continue;
							if (s[ti][tj] == '*') continue;

							dinic.addEdge(i+j, ti+tj, 1);
						}
					}
				}
			}

			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (s[i][j] == '*') continue;
					if ((i + j) % 2 == 0) {
						dinic.addEdge(1600, i+j, 1);
					} else {
						dinic.addEdge(i+j, 1601, 1);
					}
				}
			}

			int max = dinic.maxFlow(1600, 1601);

			out.println(n - max);

		}
	}

	static class Dinic {

		private class Edge {
			int to, cap, rev;

			public Edge(int to, int cap, int rev) {
				this.to = to;
				this.cap = cap;
				this.rev = rev;
			}
		}

		ArrayList<ArrayList<Edge>> g;
		int[] level, iter;

		public Dinic(int V) {
			g = new ArrayList<>(V);
			for (int i = 0; i < V; i++) g.add(new ArrayList<>());
			level = new int[V];
			iter = new int[V];
		}

		void addEdge(int from, int to, int capacity) {
			g.get(from).add(new Edge(to, capacity, g.get(to).size()));
			g.get(to).add(new Edge(from, 0, g.get(from).size() -1));
		}

		void bfs(int s) {
			Arrays.fill(level, -1);
			ArrayDeque<Integer> q = new ArrayDeque<>();
			level[s] = 0;
			q.add(s);

			while (!q.isEmpty()) {
				int v = q.poll();
				for (Edge e : g.get(v)) {
					if (e.cap > 0 && level[e.to] < 0) {
						level[e.to] = level[v] + 1;
						q.add(e.to);
					}
				}
			}
		}

		int dfs(int v, int t, int f) {
			if (v == t) return f;
			for (int i = iter[v]; i < g.get(v).size(); i++) {
				Edge e = g.get(v).get(i);
				if (e.cap > 0 && level[v] < level[e.to]) {
					int d = dfs(e.to, t, Math.min(f, e.cap));
					if (d > 0) {
						e.cap -= d;
						g.get(e.to).get(e.rev).cap += d;
						return d;
					}
				}
			}
			return 0;
		}

		int maxFlow(int s, int t) {
			int flow = 0;
			while (true) {
				bfs(s);
				if (level[t] < 0) return flow;
				Arrays.fill(iter, 0);
				int f;
				while ((f = dfs(s, t, Integer.MAX_VALUE)) > 0) {
					flow += f;
				}
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
