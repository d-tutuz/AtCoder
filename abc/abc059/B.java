package abc059;

import java.math.BigInteger;
import java.util.Scanner;

public class B {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		BigInteger a = new BigInteger(sc.next());
		BigInteger b = new BigInteger(sc.next());

//		BigInteger a = new BigInteger("123456789012345678901234567890");
//		BigInteger b = new BigInteger("234567890123456789012345678901");

		int ans = a.compareTo(b);

		if (ans < 0) {
			System.out.println("LESS");
		} else if (ans == 0) {
			System.out.println("EQUAL");
		} else {
			System.out.println("GREATER");
		}
	}

}
