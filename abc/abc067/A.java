package abc067;

import java.util.Scanner;

public class A {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int a,b;
		a = sc.nextInt();
		b = sc.nextInt();

		if (a % 3 == 0 || b % 3 == 0 || (a+b) % 3 == 0) {
			System.out.println("Possible");
		} else {
			System.out.println("Impossible");
		}

	}

}
