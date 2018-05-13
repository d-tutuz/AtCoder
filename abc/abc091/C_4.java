package abc091;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class C_4 {

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

			int n = in.nextInt();
			int s = n + n, t = s + 1;
			P[] red = new P[n];
			for (int i = 0; i < n; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				red[i] = new P(x, y);
			}
			P[] blue = new P[n];
			for (int i = 0; i < n; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				blue[i] = new P(x, y);
			}

			Flow f = new Flow(2 * n + 2);

			for (int i = 0; i < n; i++) {
				f.addEdge(s, i, 1);
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (red[i].x < blue[j].x && red[i].y < blue[j].y) {
						f.addEdge(i, j + n, 1);
					}
				}
			}

			for (int j = 0; j < n; j++) {
				f.addEdge(j + n, t, 1);
			}

			int ans = f.maxFlow(s, t);
			out.println(ans);

		}
	}
	static class P {
		int x, y;
		P(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Flow {

		static int INF = 1 << 30;

		boolean[] used;
		List<Edge>[] G;
		int V;

		@SuppressWarnings("unchecked")
		public Flow(int V) {
			G = Stream.generate(ArrayList::new).limit(V).toArray(List[]::new);
			used = new boolean[V];
			this.V = V;
		}

		static class Edge {
			int to, cap, rev;
			public Edge(int to, int cap, int rev) {
				this.to = to;
				this.cap = cap;
				this.rev = rev;
			}
		}

		void addEdge(int u, int v, int c) {
			G[u].add(new Edge(v, c, G[v].size()));
			G[v].add(new Edge(u, 0, G[u].size() - 1));
		}

		int dfs(int u, int v, int f) {
			if (u == v) return f;
			used[u] = true;
			for (Edge e : G[u]) {
				if (!used[e.to] && e.cap > 0) {
					int d = dfs(e.to, v, Math.min(f, e.cap));
					if (d > 0) {
						e.cap -= d;
						G[e.to].get(e.rev).cap += d;
						return d;
					}
				}
			}
			return 0;
		}

		int maxFlow(int s, int t) {
			int flow = 0;
			while (true) {
				used = new boolean[V];
				int f = dfs(s, t, INF);
				if (f == 0) break;
				flow += f;
			}
			return flow;
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
