package abc085;

import java.util.Scanner;

public class C {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int Y = sc.nextInt();

		for (int x = 0; x < 2223; x++) {
			for (int y = 0; y < 5000; y++) {
				if (9000 * x + 4000 * y == Y - 1000 * N) {
					if (N-x-y < 0) continue;
					System.out.printf("%d %d %d", x, y, N-x-y);
					return;
				}
			}
		}
		System.out.println("-1 -1 -1");
	}
}
