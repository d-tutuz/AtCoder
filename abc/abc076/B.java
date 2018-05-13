package abc076;

import java.io.IOException;
import java.util.Scanner;

public class B {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int k = in.nextInt();

		int ans = 1;
		for (int i = 0; i < n; i++) {
			ans = Math.min(2 * ans, ans + k);
		}
		System.out.println(ans);

	}

}
