package abc073;

import java.util.Scanner;

public class C {

	public static void main(String[] args) {

		boolean[] a = new boolean[1000000001];

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int t = sc.nextInt();
			if (a[t]) {
				a[t] = false;
			} else {
				a[t] = true;
			}
		}

		int count = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i]) count++;
		}
		System.out.println(count);
	}
}