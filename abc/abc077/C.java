package abc077;

import java.util.Arrays;
import java.util.Scanner;
public class C {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Long[] a = new Long[n];
		Long[] b = new Long[n];
		Long[] c = new Long[n];

		for (int i = 0; i < n; i++) {
			a[i] = sc.nextLong();
		}
		for (int i = 0; i < n; i++) {
			b[i] = sc.nextLong();
		}
		for (int i = 0; i < n; i++) {
			c[i] = sc.nextLong();
		}

		Arrays.sort(a);
		Arrays.sort(b);
		Arrays.sort(c);

//		System.out.println(n);
//		for (Long string : a) {
//			System.out.println(string);
//		}
//		for (Long string : b) {
//			System.out.println(string);
//		}
//		for (Long string : c) {
//			System.out.println(string);
//		}

		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = n-1; j >= 0; j--) {
				// 1段目と2段目の比較
				System.out.println("1d:" + (a[i]) +" - 2d:"+ (b[j]));
				if ((a[i]) < (b[j])) {
					for (int k = n - 1; k >= 0; k--) {
						// 2段目と3段目の比較
						System.out.println("2d:"+(b[j]) +" - 3d:"+ (c[k]));
						if ((b[j]) < (c[k])) {
							count++;
						} else {
							break;
						}
					}
				} else {
					break;
				}
			}
		}

//		System.out.println("count:"+count);
		System.out.println(count);
	}
}
