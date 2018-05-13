package abc064;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class C {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		Set<Integer> colors = new HashSet<>();
		int allCount = 0;
		for (int i = 0; i < n; i++) {
			int color = calcColor(in.nextInt());
			if (color == 100) {
				allCount += 1;
			} else if (color < 100) {
				colors.add(color);
			}
		}
		System.out.println(Math.max(colors.size(), 1) + " " + (colors.size()+allCount));

	}
	static int calcColor(int rate) {
		int color = Integer.MAX_VALUE;
		if (1 <= rate && rate <= 399) {
			color = 1;
		} else if (400 <= rate && rate <= 799) {
			color = 2;
		} else if (800 <= rate && rate <= 1199) {
			color = 3;
		} else if (1200 <= rate && rate <= 1599) {
			color = 4;
		} else if (1600 <= rate && rate <= 1999) {
			color = 5;
		} else if (2000 <= rate && rate <= 2399) {
			color = 6;
		} else if (2400 <= rate && rate <= 2799) {
			color = 7;
		} else if (2800 <= rate && rate <= 3199) {
			color = 8;
		} else {
			color = 100;
		}
		return color;
	}

}
