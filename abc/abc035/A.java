package abc035;

import java.util.Scanner;

public class A {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt(), b = in.nextInt();
		int c = gcd(a, b);
		System.out.printf("%d:%d\n", a/c, b/c);
	}

	public static int gcd(int a, int b) {
		return (b == 0) ? a : gcd(b, a % b);
	}

}
