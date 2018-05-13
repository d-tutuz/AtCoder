package abc077;

import java.util.Arrays;
import java.util.Scanner;

public class C3 {

	public static void main2(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		int[] c = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		for (int i = 0; i < n; i++) {
			b[i] = sc.nextInt();
		}
		for (int i = 0; i < n; i++) {
			c[i] = sc.nextInt();
		}

		Arrays.sort(a);
		Arrays.sort(c);

		long ans = 0;
		for (int i = 0; i < n; i++) {
			ans += binarySearch(b[i], a) * (n - binarySearch(b[i] + 1, c));
		}
		System.out.println(ans);
	}

	public static void main(String[] args) {
		int[] a = {1,2,2,2,3,3,3,3,4};
		int key = 2;
		System.out.println(binarySearch(key, a));
		System.out.println(a.length - binarySearch(key + 1, a));

	}

	private static long binarySearch(int key, int[] a) {

		int left = 0;
		int right = a.length;
		int mid;

		while (left < right) {
			mid = (left + right) / 2;
			if (key == a[mid]) {
				/*
				 * ここで、retun mid にするとNGで、right = mid にするとOKなのですが
				 * その理由が分かりませんでした。
				 * どちらも同じように思えてしまうのです。
				 */
//				return mid;
				right = mid;
			} else if (key < a[mid]) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return right;
	}
}
