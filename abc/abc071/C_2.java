package abc071;

import java.util.Arrays;
import java.util.Scanner;

public class C_2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		long firstMax = 0;
		long secondMax = 0;
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		Arrays.sort(a);
		boolean firstFound = false;
		for (int i = n-2; i >= 0; i--) {
			if (a[i] == a[i+1]) {
				if (firstFound) {
					secondMax = (long)a[i];
					break;
				} else {
					firstFound = true;
					firstMax = (long)a[i];
					a[i] = 0;
				}
			}
		}
		System.out.println(firstMax * secondMax);
	}

}
