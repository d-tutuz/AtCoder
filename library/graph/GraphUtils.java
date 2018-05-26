package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;



public class GraphUtils {

	/**
	 * ワーシャルフロイド法
	 * @input d[][] は、i＝jのとき0、i≠jのときINFで初期化した2次元配列
	 *
	 * @return d[i][j]は i->jの最短経路が格納された配列
	 * */
	void warshallFloyd(int[][] d) {
		int INF = Integer.MAX_VALUE;
		int len = d.length;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				if (d[j][i] == INF) continue;
				for (int k = 0; k < len; k++) {
					if (d[i][k] == INF) continue;
					d[j][k] = Math.min(d[j][k], d[j][i] + d[i][k]);
				}
			}
		}
	}

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
	 * 最大フロー
	 * @input N ノード数
	 * */
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

		void addEdge(int u, int v, int c, List<Edge>[] G) {
			G[u].add(new Edge(v, c, G[v].size()));
			G[v].add(new Edge(u, c, G[u].size() - 1));
		}

		private int dfs(int u, int v, int f) {
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

}
