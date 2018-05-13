package abc069;

import java.util.Scanner;

public class C {

	public static void main(String[] args) {

		int count4n = 0, count2n = 0, countOhter = 0;
		int n;
		boolean flg = true;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		for (int i = 0; i < n; i++) {
			int a = sc.nextInt();
			if (a % 4 == 0) {
				count4n++;
			} else if (a % 2 == 0) {
				count2n++;
			} else {
				countOhter++;
			}
		}

		// 4の倍数がないとき
		if (count4n == 0 && count2n != n) {
			flg = false;
		}

		// ここに来るときは必ず 4の倍数が1つ以上存在することになる
		// n が偶数
		if (n % 2 == 0) {
			if (count4n < countOhter) {
				flg = false;
			}
		}
		// n が奇数
		else {
			if (count4n < countOhter - 1) {
				flg = false;
			}
		}

		if (flg) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}

	}

}
