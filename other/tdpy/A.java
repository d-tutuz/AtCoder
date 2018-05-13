package tdpy;

import java.io.IOException;
import java.util.Scanner;

public class A {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] p = new int[n];
		for (int i = 0; i < n; i++) {
			p[i] = in.nextInt();
		}

		// dp
		boolean[][] dp = new boolean[n+1][10010];
		dp[0][0] = true;
		for (int k = 0; k < 10010; k++) {
			for (int j = 0; j < n; j++) {
				if (k >= p[j]) {
					dp[j+1][k] = dp[j][k-p[j]] | dp[j][k];
				} else {
					dp[j+1][k] = dp[j][k];
				}
			}
		}
		int count = 0;
		for (int i = 0; i < 10010; i++) {
			if (dp[n][i]) {
				count++;
			}
		}
		System.out.println(count);

	}

}
