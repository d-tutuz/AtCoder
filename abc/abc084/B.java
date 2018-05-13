package abc084;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class B {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		String s = sc.next();

		Pattern p = Pattern.compile("^[0-9]{"+a+"}-[0-9]{"+b+"}");
		Matcher m = p.matcher(s);
		if (m.find()) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

}