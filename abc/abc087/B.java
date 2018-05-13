package abc087;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		int X = sc.nextInt();
		int ans = 0;

		// 500
		for (int i = 0; i <= A; i++) {
			// 100
			for (int j = 0; j <= B; j++) {
				// 50
				for (int k = 0; k <= C; k++) {
					if (500*i+100*j+50*k == X) ans++;
				}

			}
		}
		System.out.println(ans);
		sc.close();
	}

}
