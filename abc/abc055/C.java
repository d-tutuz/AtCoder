package abc055;

import java.io.IOException;
import java.util.Scanner;

public class C {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		long n = in.nextLong();
		long m = in.nextLong();

		long ans = 0;
		if (2 * n <= m) {
			ans += n;
			m -= (2 * n);
			ans += (long)m/4;
		} else {
			ans += (long)m/2;
		}
		System.out.println(ans);
	}

}
