package abc078;

import java.util.Scanner;

public class A {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int x = (int)sc.next().toCharArray()[0];
		int y = (int)sc.next().toCharArray()[0];

		if (x < y) {
			System.out.println("<");
		} else if ( x == y) {
			System.out.println("=");
		} else {
			System.out.println(">");
		}
	}

}
