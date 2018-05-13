package abc055;

import java.io.IOException;
import java.util.Scanner;

public class B {

	static int mod = (int)Math.pow(10, 9)+7;

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long ans = 1;
		for (long i = 1; i <= n; i++) {
			ans = (ans*i)%mod;
		}
		System.out.println(ans);

	}

}
