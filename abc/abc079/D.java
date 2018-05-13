package abc079;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class D {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		int h = in.nextInt();
		int w = in.nextInt();

		int u = 10;
		int[][] mat = new int[u][u];
		for (int i = 0; i < u; i++) {
			Arrays.fill(mat[i], -1);
		}
		for (int i = 0; i < u; i++) {
			for (int j = 0; j < u; j++) {
				mat[i][j] = in.nextInt();
			}
		}
		int[] wall = new int[h*w];
		for (int i = 0; i < h*w; i++) {
			wall[i] = in.nextInt();
		}

		// solve
		int[] transCost = new int[10];

		for (int fromNode = 0; fromNode < 10; fromNode++) {
			if (fromNode == 1) {
				continue;
			}
			Set<Integer> x = new HashSet<>();
			Set<Integer> y = new HashSet<>();
			boolean[] visit = new boolean[u];
			for (int i = 0; i < u; i++) {
				if (fromNode == i) {
					continue;
				}
				y.add(i);
			}
			int[] nodeCost = new int[u];
			Arrays.fill(nodeCost, Integer.MAX_VALUE);
			x.add(fromNode);
			nodeCost[fromNode] = 0;
			visit[fromNode] = true;

			while (!y.isEmpty()) {
				int min = Integer.MAX_VALUE;
				int v = -1;
				for (int from : x) {
					for (int to = 0; to < u; to++) {
						int visitCost = mat[from][to] + nodeCost[from];
						// toのノードが最小コストで訪問済でない場合
						if (mat[from][to] != -1 && !visit[to] && visitCost <= nodeCost[to]) {
							nodeCost[to] = visitCost;
							if (nodeCost[to] < min) {
								min = nodeCost[to];
								v = to;
							}
						}
					}
				}
				x.add(v);
				y.remove(v);
				visit[v] = true;
			}
			transCost[fromNode] = nodeCost[1];
		}

		int ans = 0;
		for (int wa : wall) {
			if (wa == -1 || wa == 1) {
				continue;
			}
			ans += transCost[wa];
		}
		System.out.println(ans);
	}
}
