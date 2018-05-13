package abc066;

import java.util.Arrays;
import java.util.Scanner;

public class C {

	public static void main(String[] args) {

		int[] stack = new int[400001];
		Arrays.fill(stack, -1);
		int kijun = 200000;

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}

		int upCount = kijun;
		int downCount = kijun;

		// 偶数番目のときは上へ、奇数番目のときは下へ
		boolean fromUpFlg = true;
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				stack[++upCount] = a[i];
				fromUpFlg = true;
			} else {
				stack[--downCount] = a[i];
				fromUpFlg = false;
			}
		}

		StringBuffer sb = new StringBuffer();
		if (fromUpFlg) {
			for (int i = stack.length - 1; i >= 0; i--) {
				if (stack[i] >= 0) {
					sb.append(stack[i]);
					if (i != 0) {
						sb.append(" ");
					}
				}
			}
		} else {
			for (int i = 0; i < stack.length; i++) {
				if (stack[i] >= 0) {
					sb.append(stack[i]);
					if (i != stack.length - 1) {
						sb.append(" ");
					}
				}
			}
		}

		System.out.println(sb.toString());

	}

}
