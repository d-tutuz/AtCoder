package abc086;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int num = Integer.parseInt(sc.next() + sc.next());

		if ((int)Math.sqrt(num)*(int)Math.sqrt(num) == num) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}

	}

}
