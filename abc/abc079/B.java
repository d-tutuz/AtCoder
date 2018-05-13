package abc079;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class B {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long[] l = new long[87];
		Arrays.fill(l, -1);

		l[0] = 2;
		l[1] = 1;
		for (int i = 2; i <= n; i++) {
			l[i] = l[i-1] + l[i-2];
		}
		System.out.println(l[n]);

	}

}
