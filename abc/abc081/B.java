package abc081;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;


public class B {

	public static void main(String[] args) {

		String regex = ".*1(0+)$";
		Pattern p = Pattern.compile(regex);

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		String[] b = new String[n];
		String[] nb = new String[n];

		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}

		for (int i = 0; i < a.length; i++) {
			b[i] = Integer.toBinaryString(a[i]);
			if ("1".equals(b[i].substring(b[i].length()-2, b[i].length()-1))) {
				System.out.println("0");
				return;
			}
			nb[i] = p.matcher(b[i]).replaceAll("$1");
		}

		Arrays.sort(nb);

		System.out.println(nb[0].length());
	}

}