package abc059;

import java.util.Arrays;
import java.util.Scanner;


public class C {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		int count = 0,sabun = 0;

		int[] imos = new int[a.length];
		Arrays.fill(imos, 0);
		for (int i = 0; i < a.length; i++) {
			imos[i] = a[i];
		}
		for (int i = 0; i < a.length-1; i++) {
			imos[i+1] += imos[i];
		}

//		System.out.println("init");

//		for (int i = 0; i < imos.length; i++) {
//			System.out.println(imos[i]);
//		}

//		System.out.println("init:finish");
		//
		if (imos[0] == 0) {
			for (int i = 0; i < imos.length; i++) {
				imos[i] += 1;
			}
		}

		for (int i = 1; i < a.length; i++) {
			if (imos[i-1]*imos[i] > 0) {
				if (imos[i-1] < 0) {
					sabun = -imos[i-1]-1;
				} else {
					sabun = -imos[i-1]-1;
				}
				count += Math.abs(sabun);
				for (int j = i; j < imos.length; j++) {
					imos[j] += sabun;
				}
			} if (imos[i-1]*imos[i] == 0) {
				count += 1;
				if (imos[i-1] < 0) {
					sabun = 1;
				} else {
					sabun = -1;
				}
				for (int j = i; j < imos.length; j++) {
					imos[j] += sabun ;
				}
			}
		}

		for (int i = 0; i < a.length; i++) {
//			System.out.println(imos[i]);
		}

		System.out.println(count);

	}

}
