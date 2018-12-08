package adve;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {

		// 標準入力からデータを受け取ります
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(), m = in.nextInt(), d = in.nextInt();

		// あみだくじに対応する配列 {0, 1, 2, 3, 4} (0-indexed) としておきます
		int[] amida = new int[n];
		for (int i = 0; i < n; i++) {
			amida[i] = i;
		}
		for (int i = 0; i < m; i++) {
			int a = in.nextInt()-1;

			// あみだくじの横棒によって swap させます
			int swap = amida[a];
			amida[a] = amida[a+1];
			amida[a+1] = swap;
		}

		// あみだくじをはじめる状態 {0, 1, 2, 3, 4} からあみだくじを 1 回適応させます
		int[][] next = new int[32][n];
		for (int i = 0; i < n; i++) {
			next[0][i] = amida[i];
		}

		// 2^k 回の適応した結果から 2^{k+1} 回適応した結果を求めておきます
		for (int k = 0; k < 31; k++) {
			for (int j = 0; j < n; j++) {
				next[k+1][j] = next[k][next[k][j]];
			}
		}

		// k ビット目が 1 であるビットについて 2^k 回あみだくじを適応します
		int[] b = new int[n];
		for (int i = 0; i < n; i++) {
			int p = i;
			for (int j = 31; j >= 0; j--) {
				if ((d >> j & 1) == 1) {
					p = next[j][p];
				}
			}
			b[i] = p;
		}

		// 形に整形して解答を出力します。
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[b[i]] = i;
		}
		for (int i : ans) {
			System.out.println(i+1);
		}


		in.close();

	}
}
