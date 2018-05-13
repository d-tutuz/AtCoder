package abc082;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class B {


	public static void main(String[] args) throws IOException {

		String[] stdInStrings = new String[2];
		String[] stdInString;
//		for (int i = 0; i < 2; i++) {
//			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//			stdInStrings[i] = new String(in.readLine());
//		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		stdInStrings = new String(in.readLine()).split("\\r\\n");

		String s1 = new String(stdInStrings[0]);
		String s2 = new String(stdInStrings[1]);
		int loopCount = Math.min(s1.length(), s2.length());

		String[] os1 = stringSort(s1);
		String[] os2 = stringRevSort(s2);
		String res = "No";


		for (int i = 0; i < loopCount; i++) {
//			System.out.println("Loop:" + i);
			if (os1[i].compareTo(os2[i]) < 0) {
				res = "Yes";
				break;
			} else if (os1[i].compareTo(os2[i]) == 0) {
				continue;
			} else {
				continue;
			}
		}

//		System.out.println(String.join("", os1));
//		System.out.println(String.join("", os2));

		System.out.println(res);

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
