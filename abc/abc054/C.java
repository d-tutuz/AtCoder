package abc054;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C {

	public static void main(String[] args) throws IOException {

		// input
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String numString = "";
		for (int i = 2; i <= n; i++) {
			numString = numString + i;
		}
		int m = in.nextInt();
		int[][] mat = new int[n + 1][n + 1];
		for (int i = 0; i < m; i++) {
			int ai = in.nextInt();
			int bi = in.nextInt();
			mat[ai][bi] = 1;
			mat[bi][ai] = 1;
		}

		// solve
		List<String> numList = new ArrayList<String>();
		numList = MathUtils.permute(numString.toCharArray(), 0, numString.length() - 1, numList);

		int ans = 0;
		for (String num : numList) {
			num = (1 + num);
			boolean isConnect = true;
			for (int i = 0; i < num.length() - 1; i++) {
				int from = Integer.parseInt(num.substring(i, i+1));
				int to = Integer.parseInt(num.substring(i+1, i+2));
				if (mat[from][to] != 1) {
					isConnect = false;
					break;
				}
			}
			if (isConnect) ans++;
		}
		System.out.println(ans);

	}

	static class MathUtils {

		public static List<String> permute(char[] ary, int startIndex, int endIndex, List<String> list) {
			if (startIndex == endIndex) {
				list.add(String.valueOf(ary));
			} else {
				for (int i = startIndex; i <= endIndex; i++) {
					swap(ary, startIndex, i);
					permute(ary, startIndex + 1, endIndex, list);
					swap(ary, startIndex, i);
				}
			}
			return list;
		}

		public static void swap(char[] ary, int x, int y) {
			char temp = ary[x];
			ary[x] = ary[y];
			ary[y] = temp;
		}
	}

}
