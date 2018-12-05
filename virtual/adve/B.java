package adve;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long x = in.nextLong(), y = in.nextLong();
		if ((-x + 3*y) % 8 == 0 && (3*x - y) % 8 == 0 && (-x + 3*y) / 8 >= 0 && (3*x - y) / 8 >= 0) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
		in.close();
	}

}
