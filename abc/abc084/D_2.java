package abc084;

import java.util.Arrays;
import java.util.Scanner;


public class D_2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int q = sc.nextInt();

		boolean[] primes = new boolean[100010];
		int[] primeCount = new int[100010];

		Arrays.fill(primes, true);
		primes[0] = primes[1] = false;
		for (int i = 2; i < Math.sqrt(primes.length); i++) {
			for (int j = i; j < primes.length; j = j+i) {
				if (j == i) continue;
				primes[j] = false;
			}
		}

		for (int i = 1; i < primeCount.length; i++) {
			primeCount[i] = primeCount[i-1];
			if (primes[i] && primes[(i+1)/2]) primeCount[i]++;
		}

		// TODO:累積和？を取る意味がわからない。
		// http://satanic0258.hatenablog.com/entry/2016/04/10/151315
//		for (int i = 1; i < primeCount.length; i++) {
//			primeCount[i] += primeCount[i-1];
//		}

		for (int i = 0; i < q; i++) {
			int l = sc.nextInt(), r = sc.nextInt();
			System.out.println(primeCount[r] - primeCount[l-1]);
		}
	}

}
