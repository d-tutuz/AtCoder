package abc059;

import java.util.Scanner;

public class C_3 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		long[] s1 = new long[n];
		long[] s2 = new long[n];
		int sum1 = 0;
		int sum2 = 0;

		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}

		s1[0] = a[0];
		for (int i = 1; i < n; i++) {
			s1[i] = s1[i-1] + a[i];
		}
		s2 = s1.clone();

		int count = 0;
		// - + - + となる場合
		while (s1[0] >= 0) {
			s1[0]--;
			count++; sum1++;
		}
		for (int i = 1; i < n; i++) {
			s1[i] -= count;
		}

		for (int i = 1; i < n; i++) {
			count = 0;
			// iが奇数の場合は正にする
			if (i % 2 != 0) {
				while (s1[i-1]*s1[i] >= 0) {
					s1[i]++;
					count++; sum1++;
				}
				for (int j = i+1; j < n; j++) {
					s1[j] += count;
				}
			}
			// iが偶数の場合は負にする
			else {
				while (s1[i-1]*s1[i] >= 0) {
					s1[i]--;
					count++; sum1++;
				}
				for (int j = i+1; j < n; j++) {
					s1[j] -= count;
				}
			}
		}

		count = 0;
		// + - + - となる場合
		while (s2[0] <= 0) {
			s2[0]++;
			count++; sum2++;
		}
		for (int i = 1; i < n; i++) {
			s2[i] += count;
		}

		for (int i = 1; i < n; i++) {
			count = 0;
			if (i % 2 != 0) {
				while (s2[i-1]*s2[i] >= 0) {
					s2[i]--;
					count++; sum2++;
				}
				for (int j = i+1; j < n; j++) {
					s2[j] -= count;
				}
			}
			else {
				while (s2[i-1]*s2[i] >= 0) {
					s2[i]++;
					count++; sum2++;
				}
				for (int j = i+1; j < n; j++) {
					s2[j] += count;
				}
			}
		}

		System.out.println(Math.min(sum1, sum2));
	}

}
