package abc073;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {

		int ans = 0;

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			int l = sc.nextInt();
			int r = sc.nextInt();
			ans += (r - l + 1);
		}

		System.out.println(ans);
	}

}
