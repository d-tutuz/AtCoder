package abc085;

import java.io.IOException;
import java.util.Scanner;

public class A {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);

		char[] c = in.next().toCharArray();

		StringBuffer sb = new StringBuffer();
		sb.append("2018");
		for (int i = 4; i < c.length; i++) {
			sb.append(c[i]);
		}

		System.out.println(sb.toString());

	}

}
