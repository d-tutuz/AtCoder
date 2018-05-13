package abc087;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class D {

	static int n, m;

	public static void main(String[] args) throws IOException {

//		Scanner in = new Scanner(new File("/workspace/Atcoder/src/abc087/sampleD4.txt"));
		Scanner in = new Scanner(System.in);
		List<List<Vertex>> G = new ArrayList<>();
		n = in.nextInt();
		m = in.nextInt();
		for (int i = 0; i <= n; i++) {
			List<Vertex> v = new ArrayList<>();
			G.add(v);
		}

		// solve
		for (int i = 0; i < m; i++) {
			int l = in.nextInt();
			int r = in.nextInt();
			int d = in.nextInt();

			// l から r へのコストを取得する。経路が存在しない場合はそのまま追加する。
			// dfsないしはbfsで探索する
			int cost = bfs(G, l, r);

			// 取得したコストと d が一致する場合のみ追加
			if (cost == -1 || cost == d) {
				G.get(l).add(new Vertex(r, d));
				G.get(r).add(new Vertex(l, (-1)*d));
			} else {
				System.out.println("No");
				return;
			}
		}
		System.out.println("Yes");

	}
	static int bfs(List<List<Vertex>> G, int fromNode, int targetNodeNumber) {
		int cost = 0;
		boolean[] visit = new boolean[n+1];
		Queue<List<Vertex>> q = new ArrayDeque<>();
		q.add(G.get(fromNode));
		visit[fromNode] = true;

		while (!q.isEmpty()) {
			// 隣接ノードに立ち寄る
			List<Vertex> toList = q.remove();
			for (Vertex vertex : toList) {
				if (!visit[vertex.nodeNumber] && !visit[targetNodeNumber]) {
					q.add(G.get(vertex.nodeNumber));
					visit[vertex.nodeNumber] = true;
					cost += vertex.weight;
				}
			}
		}
		cost = visit[targetNodeNumber] ? cost : -1;
		return cost;
	}

	static class Vertex {
		int nodeNumber;
		int weight;

		public Vertex(int nodeNumber, int weight) {
			this.nodeNumber = nodeNumber;
			this.weight = weight;
		}
	}

}
