package abc027;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt(), a[] = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
		}

		Arrays.parallelPrefix(a, Math::addExact);

		if (a[n - 1] % n != 0) {
			System.out.println(-1);
			return;
		}

		int count = 0;

		for (int i = 0; i < n; i++) {
			if (a[i] / (i + 1.0) != a[n - 1] * 1.0 / n) count++;
		}
		System.out.println(count);
	}
}
