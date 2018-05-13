package abc085;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int n;
	public static void main(String[] args) throws IOException {

		// input
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		int h = in.nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = in.nextInt();
			b[i] = in.nextInt();
		}

		// solve
		int count = 0;
		Arrays.sort(a);
		Arrays.sort(b);
		int aMax = a[n-1];
		int iterator = upperBound(b, aMax-1);

		for (int i = n-1; i >= iterator; i--) {
			if (h > 0) {
				h -= b[i];
				count++;
			}
		}

		while (h > 0) {
			h -= aMax;
			count++;
		}
		System.out.println(count);

	}
	/**
	 * 指定された要素より大きい値が現れる最初の位置のイテレータを取得する
	 * */
	public static int upperBound(int[] a, int obj) {
		int l = 0,r = a.length - 1;
		while (r - l >= 0) {
			int c = (l + r) / 2;
			if (a[c] <= obj) {
				l = c + 1;
			} else {
				r = c - 1;
			}
		}
		return l;
	}

}
