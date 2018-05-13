package 京都大学プログラミングコンテスト2015;

import java.io.IOException;
import java.util.Scanner;

public class A {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		String[] moji = new String[t];
		int[] ans = new int[t];
		for (int i = 0; i < t; i++) {
			moji[i] = in.next();
		}
		for (int i = 0; i < t; i++) {
			int count = 0;
			String ins = moji[i];
			for (int j = 0; j <= ins.length() - 5; j++) {
				String insmoji = ins.substring(j, j+5);
				if (insmoji.equals("kyoto") || insmoji.equals("tokyo")) {
					count++;
					j += 4;
				}
			}
			ans[i] = count;
		}

		for (int i : ans) {
			System.out.println(i);
		}

	}

}
