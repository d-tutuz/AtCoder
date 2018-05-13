package abc063;

import java.util.Scanner;

public class B {

	public static void main(String[] args) {

		int[] a = new int[26];
		Scanner sc = new Scanner(System.in);
		char[] s = sc.next().toCharArray();

		for (int i = 0; i < s.length; i++) {
			a[s[i]-97]++;
			if (a[s[i]-97] >= 2) {
				System.out.println("no");
				return;
			}
		}
		System.out.println("yes");
	}
}
