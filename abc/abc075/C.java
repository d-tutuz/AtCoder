package abc075;

import java.util.Arrays;
import java.util.Scanner;

public class C {

	public static void main(String[] args) {

		int nodeCount = 0;
		int edgeCount = 0;

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		nodeCount = sc.nextInt();
		edgeCount = sc.nextInt();

		int[][] matrix = new int[nodeCount+1][nodeCount+1];
		// 入力される2辺(a, b)
		int[] a = new int[edgeCount];
		int[] b = new int[edgeCount];

		// 辺を取得
		for (int i = 0; i < edgeCount; i++) {
			a[i] = sc.nextInt();
			b[i] = sc.nextInt();
		}

		// 辺の情報をもとに無向グラフの行列を作成
		for (int i = 0; i < edgeCount; i++) {
			matrix[a[i]][b[i]] = 1;
			matrix[b[i]][a[i]] = 1;
		}

		// 訪問済かどうかを保持
		boolean[] d = new boolean[nodeCount+1];

		// 非連結になる辺の数
		int bridgeCount = 0;
		for (int i = 0; i < edgeCount; i++) {
			// 1辺を取り除く
			matrix[a[i]][b[i]] = 0;
			matrix[b[i]][a[i]] = 0;

			// 頂点1から順番に各頂点を探索
			d = dfs(matrix, d, 1);

			// 各頂点を訪問できなかった場合は非連結
			for (int j = 1; j < d.length; j++) {
				if (d[j]) {
					continue;
				} else {
					bridgeCount++;
					break;
				}
			}

			// 1辺を元に戻す
			matrix[a[i]][b[i]] = 1;
			matrix[b[i]][a[i]] = 1;
			Arrays.fill(d, false);

		}
		System.out.println(bridgeCount);
	}

	static boolean[] dfs(int[][] matrix, boolean[] d, int now) {
		// すでに初回訪問済の場合はとばす
		if (d[now]) {
			return d;
		}
		// 初回訪問時の記録
		for (int i = 1; i < matrix.length; i++) {
			// 遷移先頂点が存在する場合は遷移して、再帰的に探索
			if (matrix[now][i] == 1) {
				d[now] = true;
				d = dfs(matrix, d, i);
			}
		}
		return d;
	}

}
