package abc059;

import java.util.Arrays;
import java.util.Scanner;

public class C_32 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		long[] s1 = new long[n];

		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}

		long sum1 = 0;
		s1[0] = a[0];
		// - + - + となる場合
		if (s1[0] >= 0) {
			sum1 = sum1 + 1 + s1[0];
			s1[0] = -1;
		}
		for (int i = 1; i < n; i++) {
			s1[i] = s1[i-1] + a[i];
			// iが奇数の場合は正にする
			if (i % 2 != 0) {
				if (s1[i-1]*s1[i] >= 0) {
					sum1 = sum1 + 1 - s1[i];
					s1[i] = 1;
				}
			}
			// iが偶数の場合は負にする
			else {
				if (s1[i-1]*s1[i] >= 0) {
					sum1 = sum1 + 1 + s1[i];
					s1[i] = -1;
				}
			}
		}

		Arrays.fill(s1, 0);
		long sum2 = 0;
		s1[0] = a[0];
		// + - + - となる場合
		if (s1[0] <= 0) {
			sum2 = sum2 + 1 - s1[0];
			s1[0] = 1;
		}
		for (int i = 1; i < n; i++) {
			s1[i] = s1[i-1] + a[i];
			// iが奇数の場合は負にする
			if (i % 2 != 0) {
				if (s1[i-1]*s1[i] >= 0) {
					sum2 = sum2 + 1 + s1[i];
					s1[i] = -1;
				}
			}
			// iが偶数の場合は正にする
			else {
				if (s1[i-1]*s1[i] >= 0) {
					sum2 = sum2 + 1 - s1[i];
					s1[i] = 1;
				}
			}
		}

		System.out.println(Math.min(sum1, sum2));
	}

}
