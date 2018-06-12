package graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class GraphUtils {

	/**
	 * ベルマンフォード法
	 * 負のコストを持つ辺がある場合の最短経路探索
	 *
	 * @input Edge[]
	 * @input Node[]
	 * @input fromNode[]
	 *
	 * */
	static void bellmanFord(Edge[] edges, Node[] nodes, int fromNode) {

		int n = nodes.length;
		int m = edges.length;
		long INF = Long.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i, INF);
		}
		nodes[fromNode].cost = 0;

		for (int loop = 0; loop < n - 1; loop++) {
			for (int i = 0; i < m; i++) {
				if (nodes[edges[i].fromNodeNum].cost == INF) {
					continue;
				}
				if (nodes[edges[i].toNodeNum].cost > nodes[edges[i].fromNodeNum].cost + edges[i].weight) {
					nodes[edges[i].toNodeNum].cost = nodes[edges[i].fromNodeNum].cost + edges[i].weight;
				}
			}
		}
	}

	/**
	 * 負閉路を検知
	 * */
	static boolean findNegativeCycleByBellmanFord(Edge[] edges, Node[] nodes, int fromNode) {

		int n = nodes.length;
		int m = edges.length;
		long INF = Long.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i, INF);
		}
		nodes[fromNode].cost = 0;

		boolean isNegative = false;
		for (int loop = 0; loop < n; loop++) {
			for (int i = 0; i < m; i++) {
				if (nodes[edges[i].fromNodeNum].cost == INF) {
					continue;
				}
				if (nodes[edges[i].toNodeNum].cost > nodes[edges[i].fromNodeNum].cost + edges[i].weight) {
					nodes[edges[i].toNodeNum].cost = nodes[edges[i].fromNodeNum].cost + edges[i].weight;
					if (loop == n - 1) isNegative = true;
				}
			}
		}

		return isNegative;

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

	static class Node {
		int nodeNum;
		long cost;

		public Node(int nodeNum, long weight) {
			this.nodeNum = nodeNum;
			this.cost = weight;
		}
	}
	static class Edge {
		int fromNodeNum;
		int toNodeNum;
		long weight;

		public Edge(int fromNodeNum, int toNodeNum, long weight) {
			this.fromNodeNum = fromNodeNum;
			this.toNodeNum = toNodeNum;
			this.weight = weight;
		}
	}

	/**
	 * 二部マッチング
	 *
	 * @param v :頂点数
	 * @param c :色(1 または -1)
	 * @param color :頂点がどの色で塗られているかを保持
	 * @param g :グラフ
	 * @return :二部グラフの場合は true,そうでない場合は false
	 */
	static boolean isBipartite(int v, int c, int[] color, List<Integer>[] g) {
		color[v] = c;
		for (int t : g[v]) {
			if (color[t] == c) {
				return false;
			}
			if (color[t] == 0 && !isBipartite(t, -c, color, g)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 最大フロー＝最小カット(Dinic法)
	 *
	 * 計算量はO(|V|^2 * |E|)
	 *
	 * @param: V:頂点数
	 * @param: to:辺
	 * @param: cap:辺の容量
	 * @param: rev:逆辺
	 *
	 * @return: flow:最大フロー数
	 */

	// 辺を構築する際の g.get(to).size() はすでに存在する辺を数えることで
	// 逆辺の index を構築している。

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

	/**
	 * 根付き木
	 *
	 * 計算量：
	 * 　構築　：O(NlogN)
	 * 　クエリ：O(logN)
	 *
	 * */
	public static class LCA {

		private final int logN;
		private final int n;
		private final List<Integer>[] graph;
		private final int[][] par;
		public final int[] depth;

		public LCA(List<Integer>[] g, int root) {
			this.graph = g;
			this.n = g.length;
			this.logN = Integer.numberOfTrailingZeros(Integer.highestOneBit(n - 1)) + 1;
			this.par = new int[logN][n];
			this.depth = new int[n];

			init(root);
		}

		private void init(int root) {
			bfs(root);
			for (int k = 0; k < logN - 1; k++) {
				for (int v = 0; v < n; v++) {
					if (par[k][v] < 0)
						par[k + 1][v] = -1;
					else
						par[k + 1][v] = par[k][par[k][v]];
				}
			}
		}

		private void bfs(int v) {
			Arrays.fill(depth, Integer.MAX_VALUE);
			ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
			queue.add(v);
			depth[v] = 0;
			par[0][v] = -1;
			while (!queue.isEmpty()) {
				int now = queue.poll();
				for (int p : graph[now]) {
					if (depth[p] > depth[now] + 1) {
						depth[p] = depth[now] + 1;
						queue.add(p);
						par[0][p] = now;
					}
				}
			}
		}

		public int lca(int u, int v) {
			if (depth[u] > depth[v]) {
				int tmp = u;
				u = v;
				v = tmp;
			}
			for (int k = 0; k < logN; k++) {
				if (((depth[v] - depth[u]) >> k & 1) == 1)
					v = par[k][v];
			}
			if (u == v)
				return u;

			for (int k = logN - 1; k >= 0; k--) {
				if (par[k][u] != par[k][v]) {
					u = par[k][u];
					v = par[k][v];
				}
			}
			return par[0][u];
		}
	}

//	/**
//	 * 最大フロー
//	 * @input N ノード数
//	 * */
//	static class Flow {
//
//		static int INF = 1 << 30;
//
//		boolean[] used;
//		List<Edge>[] G;
//		int V;
//
//		@SuppressWarnings("unchecked")
//		public Flow(int V) {
//			G = Stream.generate(ArrayList::new).limit(V).toArray(List[]::new);
//			used = new boolean[V];
//			this.V = V;
//		}
//
//		static class Edge {
//			int to, cap, rev;
//			public Edge(int to, int cap, int rev) {
//				this.to = to;
//				this.cap = cap;
//				this.rev = rev;
//			}
//		}
//
//		void addEdge(int u, int v, int c, List<Edge>[] G) {
//			G[u].add(new Edge(v, c, G[v].size()));
//			G[v].add(new Edge(u, c, G[u].size() - 1));
//		}
//
//		private int dfs(int u, int v, int f) {
//			if (u == v) return f;
//			used[u] = true;
//			for (Edge e : G[u]) {
//				if (!used[e.to] && e.cap > 0) {
//					int d = dfs(e.to, v, Math.min(f, e.cap));
//					if (d > 0) {
//						e.cap -= d;
//						G[e.to].get(e.rev).cap += d;
//						return d;
//					}
//				}
//			}
//			return 0;
//		}
//
//		int maxFlow(int s, int t) {
//			int flow = 0;
//			while (true) {
//				used = new boolean[V];
//				int f = dfs(s, t, INF);
//				if (f == 0) break;
//				flow += f;
//			}
//			return flow;
//		}
//	}

}
