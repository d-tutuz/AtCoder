package abc078;

import java.util.Scanner;

public class C {

	public static void main(String[] args) {

		int n = 0, m = 0;
		Scanner sc =new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();

		int time = 0;
		time = ((1900*m+100*(n-m))*(int)Math.pow(2, m));
		System.out.println(time);

	}

}
