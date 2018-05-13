package abc087;

import java.util.Scanner;

public class C {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] candyCount = new int[2][N];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N; j++) {
				candyCount[i][j] = sc.nextInt();
			}
		}
		int sum = 0;
		int ans = 0;

		for (int i = 0; i < N; i++) {
			// 上段
			sum = 0;
			for (int j = 0; j <= i; j++) {
				sum += candyCount[0][j];
			}
			for (int j = N-1; j >= i; j--) {
				// 下段
				sum += candyCount[1][j];
			}
			ans = Math.max(ans, sum);
		}
		System.out.println(ans);
	}

}
