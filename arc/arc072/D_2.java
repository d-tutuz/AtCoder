package arc072;

import java.util.Scanner;

public class D_2 {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long x = in.nextLong(), y = in.nextLong();
		System.out.println(Math.abs(x - y) <= 1 ? "Brown" : "Alice");
	}
}
