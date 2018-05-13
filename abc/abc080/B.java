package abc080;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String N = sc.next();
		int divNum = 0;
		for (int i = 0; i < N.length(); i++) {
			divNum += Integer.parseInt(N.substring(i, i+1));
		}
		if (Integer.parseInt(N) % divNum == 0) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}

	}

}
