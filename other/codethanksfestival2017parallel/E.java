package codethanksfestival2017parallel;

import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class E {

	static int INF = 1 << 30;
	static boolean isDebug = false;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		boolean[] isReal = new boolean[n];
		boolean[] isJudged = new boolean[n];

		int total = 0;
		List<String> qs = new ArrayList<>();

		while (total < n) {
			StringBuffer sb = new StringBuffer();
			sb.append("?");
			int count = 4;
			for (int i = 0; i < n; i++) {
				if (count >= 0 && !isJudged[i]) {
					sb.append(" ");
					sb.append(String.valueOf((int)Math.pow(5, count)));
					count--;
					isJudged[i] = true;
					total++;
				} else {
					sb.append(" 0");
				}
			}
			qs.add(sb.toString());
		}

		for (String query : qs) {
			String tmp = new String(query);
			String[] tmps = tmp.split(" ");
			int l = INF;
			int r = -1;
			for (int i = 1; i < tmps.length; i++) {
				if (!tmps[i].equals("0")) {
					l = min(l, i);
					r = max(r, i);
				}
			}
			l--; r--;
			int num = 0;
			for (int i = l%5; i <= r%5; i++) {
				num += pow(5, 4-i);
			}
			if (isDebug) System.out.println(l +" " + r);
			System.out.println(query);
			int ans = sc.nextInt();
			ans -= num*8;
			String base5 = String.format("%5s", Integer.toString(ans, 5)).replace(" ", "0");
			if (isDebug) System.out.println(base5);
			String[] base5s = base5.split("");
			for (int i = 0; i < base5s.length; i++) {
				if (i+l >= n) {
					continue;
				}
				if (base5s[i].equals("1") || base5s[i].equals("3")) {
					isReal[i+l] = true;
				} else {
					isReal[i+l] = false;
				}
			}
		}

		StringBuffer ret = new StringBuffer();
		ret.append("!");
		for (boolean b : isReal) {
			if (b) {
				ret.append(" 1");
			} else {
				ret.append(" 0");
			}
		}

		System.out.println(ret.toString());
	}

}
