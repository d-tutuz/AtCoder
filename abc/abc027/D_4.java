package abc027;

import java.util.Arrays;
import java.util.Scanner;

public class D_4 {

	static int INF = 1 << 30;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		char[] s = in.next().toCharArray();
		int n = s.length;

		int offset = n;
		int[][] dp = new int[n+1][n+1+offset];

		for (int i = 0; i <= n; i++) {
			Arrays.fill(dp[i], -INF);
		}

		dp[0][offset] = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 1; j < n+offset; j++) {
				if (s[i] == 'M') {
					dp[i+1][j] = Math.max(dp[i][j+1], dp[i][j-1]);
				} else if (s[i] == '+') {
					dp[i+1][j] = dp[i][j] + (j-offset);
				} else if (s[i] == '-') {
					dp[i+1][j] = dp[i][j] - (j-offset);
				}
			}
		}

		System.out.println(dp[n][offset]);
	}

}
