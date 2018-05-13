package abc063;

import java.util.Arrays;
import java.util.Scanner;

public class C {

	public static void main(String[] args) {

		int n = 0;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		int[] s = new int[n];
		for (int i = 0; i < n; i++) {
			s[i] = sc.nextInt();
		}

		Arrays.sort(s);
		int sum = 0;
		for (int i = n - 1; i >= 0; i--) {
			sum += s[i];
		}
		if (sum % 10 == 0) {
			for (int i = 0; i < n; i++) {
				if (s[i] % 10 != 0) {
					sum -= s[i];
					break;
				}
				if (i == n - 1) {
					sum = 0;
				}
			}
		}
		System.out.println(sum);

	}

}
