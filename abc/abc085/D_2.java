package abc085;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class D_2 {

	static int n;
	public static void main(String[] args) throws IOException {

		// input
//		Scanner in = new Scanner(new File("/workspace/Atcoder/src/abc085/sample_D4.txt"));
//		Scanner in = new Scanner(new File("/workspace/Atcoder/src/abc085/sample_D3.txt"));
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
		System.out.println("aMax:"+aMax);
		int iterator = upperBound(b, aMax);
		System.out.println("iterator:"+iterator);

		for (int i = n-1; i >= iterator; i--) {
			if (h > 0) {
				System.out.println("b[i]:"+b[i]);
				h -= b[i];
				count++;
			}
		}

		while (h > 0) {
			h -= aMax;
			count++;
		}
		System.out.println("h:"+h);
		System.out.println("count:"+count);

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

