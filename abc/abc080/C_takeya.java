package abc080;

import java.util.Scanner;

public class C_takeya {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();			// 店数
		int[][] f = new int[n][10];		// 店稼働状況
		int[][] p = new int[n][11];		// 利益

		// 店の稼働状況取得
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 10; j++) {
				f[i][j] = sc.nextInt();
			}
		}

		// 店稼働時の各利益取得
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 11; j++) {
				p[i][j] = sc.nextInt();
			}
		}
		int ans = (-1) * Integer.MAX_VALUE;

		// 1023通りの組み合わせを計算。数iを2進数として考える。
		// 例えば、0000100101にて、1の場所が稼働しているパターンと考える。
		for (int i = 1; i < (int) Math.pow(2, 10); i++) {

			// 店iが週に開店している数
			int[] kaiten = new int[n];

			// 稼働に応じて計算する
			for (int j = 0; j < 10; j++) {

				// ビットごとの積を取る
				if ((i & (1 << j)) != 0) {
					for (int k = 0; k < n; k++) {

						// 稼働している場合はここが1となる
						if (f[k][j] == 1)
							kaiten[k]++;
					}
				}
			}
			int sum = 0;
			for (int j = 0; j < n; j++) {
				// 稼働数に応じた店の利益を和する
				sum += (p[j][kaiten[j]]);
			}
			ans = Math.max(ans, sum);
		}
		System.out.println(ans);
	}
}
