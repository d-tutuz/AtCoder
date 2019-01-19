import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;


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
	 *
	 */

// 辺を構築する際の g.get(to).size() はすでに存在する辺を数えることで
// 逆辺の index を構築している。


// new Dinic(n) で初期化
// addEdge(from, to, cost) で辺を構築
// maxFlow(from, to) で from -> to のmaxFlow を計算

class Dinic {

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