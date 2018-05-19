package practice_5_19;

import java.util.Scanner;

public class J_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char[] s = sc.next().toCharArray();
		int N = s.length;

		int ans = N;
		for (int i = 1; i < N; i++) {
			if (s[i] != s[i - 1]) {
				ans = Math.min(ans, Math.max(i, N - i));
			}
		}

		System.out.println(ans);
		sc.close();
	}

}
