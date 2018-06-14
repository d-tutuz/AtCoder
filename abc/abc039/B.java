package abc039;
import java.util.Scanner;

public class B {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

//		char[] s = in.nextLine().toCharArray();
		char[] s = "823".toCharArray();
		int len = s.length;

		int[][] dp = new int[len+1][2];
		dp[0][0] = 1;

		for (int i = 0; i < len; i++) {
			// 確定させようとしている桁の数
			int D = s[i]-'0';

			// j -> 0：未満でない(同じ)、1：未満
			for (int j = 0; j < 2; j++) {

				// インクリメントで参照する桁の数。
				// すでにN未満が確定していれば常に9まで参照可能
				// 未確定の場合は、s[i]まで参照可能。
				int m = (j == 1 ? 9 : D);
				for (int d = 0; d <= m; d++) {
					dp[i+1][j | (d < D ? 1 : 0)] += dp[i][j];
				}
			}
		}

		System.out.println(dp[len][0]+dp[len][1]);
	}

}
