package abc066;

import java.util.Scanner;

public class B {


	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		char[] sArray = sc.next().toCharArray();
		int[] alphabetNum = new int[26];

		for (int i = 0; i < sArray.length; i++) {
			alphabetNum[(int)sArray[i]-97]++;
		}

		for (int i = sArray.length-1; i >= 0 ; i--) {
			alphabetNum[(int)sArray[i]-97]--;
			if(checkCount(alphabetNum)) {
				System.out.println(i);
				return;
			}
		}

	}

	private static boolean checkCount(int[] array) {

		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 == 0) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
}
