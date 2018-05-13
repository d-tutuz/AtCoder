package abc080;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C {

	public static void main(String[] args) throws IOException {

		// input
		Scanner in = new Scanner(System.in);
//		in = new Scanner(new File("/workspace/Atcoder/src/abc080/C_sample1.txt"));
		int n = in.nextInt();
		char[][] f = new char[n][10];
		int[][] p = new int[n][11];
		for (int i = 0; i < n; i++) {
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < 10; j++) {
				sb.append(in.next());
			}
			f[i] = sb.toString().toCharArray();
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 11; j++) {
				p[i][j] = in.nextInt();
			}
		}

		// solve
		int pow = 10;
		int num_to = (int) Math.pow(2, pow) - 1;
		List<String> bitList = new ArrayList<>();
		for (int i = num_to; i >= 1; i--) {
			String bin = String
					.format("%" + pow + "s", Integer.toBinaryString(i)).replace(
							' ', '0');
			bitList.add(bin);
		}

		int sum = 0;
		int ans = Integer.MIN_VALUE;
		for (String bits : bitList) {
			char[] bit = bits.toCharArray();
			sum = 0;
			for (int i = 0; i < n; i++) {
				int count = 0;
				for (int j = 0; j < 10; j++) {
					if (f[i][j] == '1' && bit[j] == f[i][j]) {
						count++;
					}
				}
				sum += p[i][count];
			}
			ans = Math.max(ans, sum);
		}
		System.out.println(ans);

	}

}
