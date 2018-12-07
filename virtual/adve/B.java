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
			int tmp = amida[a];
			amida[a] = amida[a+1];
			amida[a+1] = tmp;
		}

		// 解答を出力します
		int[] ans = new int[n];
		for (int i = 0; i < n; i++) {
			ans[amida[i]] = i+1;
		}

		for (int i : ans) {
			System.out.println(i);
		}

		in.close();
	}
}
