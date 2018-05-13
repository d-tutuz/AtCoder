package abc066;

import java.util.Scanner;

public class B_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();

		for (int i = s.length()-1; i >= 2; i--) {
			if (i % 2 != 0) continue;
			if(s.substring(0, i/2).equals(s.substring(i/2,i))){
				System.out.println(i);
				return;
			}
		}
	}

}
