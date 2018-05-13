package abc060;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();

		if (c % (calcGcd(a, b)) == 0) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}

	private static int calcGcd(int a, int b) {

		return (b == 0) ? a : calcGcd(b, a % b);

	}

}
