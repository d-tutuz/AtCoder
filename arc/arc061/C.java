package arc061;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class C {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		char[] str = in.next().toCharArray();
		int len = str.length - 1;
		int num = (1 << len) - 1;
		char[][] bit = new char[num+1][];
		for (int i = 0; i <= num; i++) {
			if (len > 0) {
				bit[i] = String.format("%"+len+"s",Integer.toBinaryString(i))
						.replace(' ', '0').toCharArray();
			}
		}

		List<String> list = new ArrayList<>();

		for (int i = 0; i <= num; i++) {
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < str.length; j++) {
				sb.append(str[j]);
				if (bit[i] != null && j < bit[i].length && bit[i][j] == '1') {
					sb.append('a');
				}
			}
			list.add(sb.toString());
		}

		long sum = 0;
		for (String string : list) {
			String[] strings = string.split("a");
			for (String moji : strings) {
				sum += Long.parseLong(moji);
			}
		}
		System.out.println(sum);
	}
}
