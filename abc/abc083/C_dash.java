package abc083;

import java.io.IOException;
import java.util.Scanner;

public class C_dash {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		double x = sc.nextDouble();
		double y = sc.nextDouble();

		double baseNum = 2;
		double threshold = x;

		int count = -1;

		for (double i = 0; threshold <= y; i++) {

			double n = Math.pow(baseNum, i);
			threshold = x * n;
			count++;
		}
		System.out.println(count);
	}

}
