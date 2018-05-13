package abc077;

import java.util.Arrays;
import java.util.Scanner;

public class C2 {

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

		long count = 0L;
		long n1 ,n2;

		// 2段目の値
		for (int i = 0; i < n; i++) {
//			System.out.println("b[i]:"+b[i]);

			// 1段目の条件を満たす要素数を取得
			n1 = binarySearch(b[i], a, "d");
//			System.out.println("n1:"+n1);

			// 3段目の条件を満たす要素数を取得
			n2 = n - binarySearch(b[i], c, "u");
//			System.out.println("n2:"+n2);

			count += n1 * n2;
		}

//		System.out.println("count:"+count);
		System.out.println(count);
	}

	public static void main2(String[] args) {
		long v = 10L;
		Long[][] c2 = {{1L, 10L, 100L},{100L,110L,120L}}; //=>2


		System.out.println(binarySearch(v, c2[0], "d"));
		System.out.println("-----------");
		System.out.println(c2[1].length - binarySearch(v, c2[1], "u"));
		}

	private static long binarySearch(long key, Long[] compareVaules, String flag) {

		int left = 0;
		int right = compareVaules.length;
		int mid;

		while (left < right) {
			mid = (left + right) / 2;
//			System.out.println("[bf]left:"+left+",mid:"+mid+",right:"+right);
//			System.out.println("key:"+key+",value:"+compareVaules[mid]);
			if (key == compareVaules[mid]) {
				if ("d".equals(flag)) {
					return Math.max(mid, 0);
				} else {
					return Math.min(compareVaules.length,mid+1);
				}
			} else if (key < compareVaules[mid]) {
				right = mid;
			} else {
				left = mid + 1;
			}
//			System.out.println("[af]left:"+left+",mid:"+mid+",right:"+right);
//			System.out.println("------------------");
		}
		return right;
	}
}
