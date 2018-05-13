package abc069;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		System.out.println(s.substring(0, 1) + String.valueOf(s.length() - 2) + s.substring(s.length()-1, s.length()));
	}

}
