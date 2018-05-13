package abc077;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
public class B {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String[] str1 = sc.nextLine().split("");
		String[] str2 = sc.nextLine().split("");

		List list = Arrays.asList(str2);
		Collections.reverse(list);
		str2 = (String[]) list.toArray(new String[3]);

		if (String.join("", str1).equals(String.join("", str2))) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}

}
