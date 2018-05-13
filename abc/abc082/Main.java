package abc082;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {


	public static void main(String[] args) throws IOException {

		String[] stdInStrings = new String[2];
		String stdInString;

//		for (int i = 0; i < 2; i++) {
//			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//			stdInStrings[i] = new String(in.readLine());
//		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		stdInString = new String(in.readLine());
		stdInStrings = stdInString.split("\\r\\n");

//		String s1 = new String(stdInStrings[0]);
//		String s2 = new String(stdInStrings[1]);
		String s1 = new String(stdInStrings[0]);
		String s2 = new String(stdInStrings[1]);

		String[] os1 = stringSort(s1);
		String[] os2 = stringRevSort(s2);

		String resMsg = "Yes";

		if (s1.length() < s2.length()) {
			for (int i = 0; i < s1.length(); i++) {
				if (os1[i].compareTo(os2[i]) < 0) {
					resMsg = "Yes";
					break;
				} else if (os1[i].compareTo(os2[i]) == 0) {
					resMsg = "No";
				} else {
					resMsg = "No";
				}
			}
		} else {
			for (int i = 0; i < s2.length(); i++) {
				if (os1[i].compareTo(os2[i]) < 0) {
					resMsg = "Yes";
					break;
				} else if (os1[i].compareTo(os2[i]) == 0) {
					resMsg = "No";
				} else {
					resMsg = "No";
				}
			}
		}

		System.out.println(resMsg);
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
