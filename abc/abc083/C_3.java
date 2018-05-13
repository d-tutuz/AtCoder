package abc083;

import java.util.Scanner;

public class C_3 {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		long x = in.nextLong();
		long y = in.nextLong();
		int count = 0;
		while (x <= y) {
			x *= 2;
			count++;
		}
		System.out.println(count);

	}

}
