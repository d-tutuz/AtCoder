package abc084;

import java.util.Scanner;

public class D {

	public static void main(String[] args) {

		int Q;

		Scanner sc = new Scanner(System.in);
		Q = sc.nextInt();
		int[] r = new int[Q];
		int[] l = new int[Q];
		for (int i = 0; i < Q; i++) {
			l[i] = sc.nextInt();
			r[i] = sc.nextInt();
		}

		for (int i = 0; i < Q; i++) {
			int ans = 0;
			for (int j = l[i]; j <= r[i]; j = j + 2) {
				ans += simiar2017Number(j);
			}
			System.out.println(ans);
		}
	}

	private static int simiar2017Number(int N) {

		if (isPrime(N) && isPrime((N + 1) / 2)) {
			return 1;
		} else return 0;
	}

	private static boolean isPrime(int N) {

		if (N == 1)return false;
		if (N == 2)return true;
		if (N % 2 == 0)return false;
		for (int i = 3; i < N; i = i + 2)
			if (N % i != 0) continue;
			else return false;

		return true;
	}
}
