package abc006;

import java.io.IOException;
import java.util.Scanner;

public class B {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		long[] a = new long[1000001];

		a[1] = a[2] = 0;
		a[3] = 1;
		for (int i = 4; i <= n; i++) {
			a[i] = ((a[i-1] + a[i-2] + a[i-3])%10007);
		}
		System.out.println(a[n]);
	}
}
