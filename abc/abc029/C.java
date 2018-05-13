package abc029;

import java.io.IOException;
import java.util.Scanner;

public class C {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		for (int i = 0; i < Math.pow(3, N); i++) {
			String num = String.format("%"+N+"s",Integer.toString(i, 3)).replace(' ', '0');
			num = num.replace('0', 'a');
			num = num.replace('1', 'b');
			num = num.replace('2', 'c');
			System.out.println(num);
		}
	}
}

//public class Main {
//	static int N;
//
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		N = sc.nextInt();
//		dfs("");
//	}
//
//	public static void dfs(String s) {
//		if (s.length() == N) {
//			System.out.println(s);
//		} else {
//			dfs(s + "a");
//			dfs(s + "b");
//			dfs(s + "c");
//		}
//	}
//}