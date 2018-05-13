package abc084;

import java.util.Scanner;
public class A {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String m = sc.nextLine();
		System.out.println(48-Integer.parseInt(m));
	}

}