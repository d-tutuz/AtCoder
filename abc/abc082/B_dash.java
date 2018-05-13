package abc082;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class B_dash {


	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		String s1 = sc.next();
		String s2 = sc.next();

		int loopCount = Math.min(s1.length(), s2.length());

		String[] os1 = stringSort(s1);
		String[] os2 = stringRevSort(s2);
		String res = "No";

		if (String.join("", os1).compareTo(String.join("", os2)) < 0) {
			res = "Yes";
		} else {
			res = "No";
		}

		System.out.println(res);
		sc.close();
	}

	public static String[] stringSort(String inString) {

		String[] inStrings = inString.split("");
		String[] outStrings;

		Arrays.sort(inStrings, new Comparator<String>() {

			public int compare(String obj1, String obj2) {
				return obj1.compareTo(obj2);
			}
		});
		outStrings = inStrings;

		return outStrings;
	}

	public static String[] stringRevSort(String inString) {

		String[] inStrings = inString.split("");
		String[] outStrings;

		Arrays.sort(inStrings, new Comparator<String>() {

			public int compare(String obj1, String obj2) {
				return obj2.compareTo(obj1);
			}
		});
		outStrings = inStrings;

		return outStrings;
	}
}
