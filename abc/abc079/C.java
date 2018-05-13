package abc079;

import java.util.Scanner;



public class C {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		int a = Integer.parseInt(str.substring(0, 1));
		int b = Integer.parseInt(str.substring(1, 2));
		int c = Integer.parseInt(str.substring(2, 3));
		int d = Integer.parseInt(str.substring(3, 4));

		if (a+b+c+d == 7) {System.out.println(a+"+"+b+"+"+c+"+"+d+"=7"); return;}
		if (a+b+c-d == 7) {System.out.println(a+"+"+b+"+"+c+"-"+d+"=7"); return;}
		if (a+b-c+d == 7) {System.out.println(a+"+"+b+"-"+c+"+"+d+"=7"); return;}
		if (a+b-c-d == 7) {System.out.println(a+"+"+b+"-"+c+"-"+d+"=7"); return;}
		if (a-b+c+d == 7) {System.out.println(a+"-"+b+"+"+c+"+"+d+"=7"); return;}
		if (a-b+c-d == 7) {System.out.println(a+"-"+b+"+"+c+"-"+d+"=7"); return;}
		if (a-b-c+d == 7) {System.out.println(a+"-"+b+"-"+c+"+"+d+"=7"); return;}
		if (a-b-c-d == 7) {System.out.println(a+"-"+b+"-"+c+"-"+d+"=7"); return;}

	}

}