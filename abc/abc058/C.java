package abc058;

import java.io.IOException;
import java.util.Scanner;

public class C {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		char[][] strings = new char[n][];
		for (int i = 0; i < n; i++) {
			strings[i] = in.next().toCharArray();
		}

		int[][] list = new int[n][26];
		for (int i = 0; i < n; i++) {
			int[] moji = new int[26];
			for (int j = 0; j < strings[i].length; j++) {
				int num = (int)(strings[i][j]-'a');
				moji[num]++;
			}
			list[i] = moji;
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 26; i++) {
			int count = Integer.MAX_VALUE;
			for (int j = 0; j < n; j++) {
				if (list[j][i] != 0) {
					count = Math.min(list[j][i], count);
				} else {
					count = 0;
					break;
				}
			}

			for (int j = 0; count != Integer.MAX_VALUE && j < count; j++) {
				sb.append((char)(i+'a'));
			}
		}
		System.out.println(sb.toString());

	}

}
