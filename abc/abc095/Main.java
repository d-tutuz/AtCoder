package abc095;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long c = sc.nextLong();
		long[] x = new long[n];
		long[] v = new long[n];

		for (int i = 0; i < n; i++) {
			x[i] = sc.nextLong();
			v[i] = sc.nextLong();
		}
		// right
		long answer1 = 0;
		long tmp = 0;
		for (int i = 0; i < n; i++) {
			tmp += (v[i] - (i == 0 ? x[i] : x[i] - x[i - 1]));
			answer1 = Math.max(answer1, tmp);
		}
		tmp = 0;
		// left
		long answer2 = 0;
		for (int i = n - 1; i >= 0; i--) {
			tmp += (v[i] - (i == n - 1 ? c - x[i] : x[i + 1] - x[i]));
			answer2 = Math.max(answer2, tmp);
		}
		// right and then left
		long answer3 = 0;
		long[] tmpMax = new long[n];
		tmp = 0;
		for (int i = 0; i < n; i++) {
			tmp += (v[i] - 2 * (i == 0 ? x[i] : x[i] - x[i - 1]));
			tmpMax[i] = i == 0 ? tmp : Math.max(tmpMax[i - 1], tmp);
		}
		tmp = 0;
		for (int i = n - 1; i >= 1; i--) {
			tmp += (v[i] - (i == n - 1 ? c - x[i] : x[i + 1] - x[i]));
			answer3 = Math.max(answer3, tmp + tmpMax[i - 1]);
		}
		// left and then right
		long answer4 = 0;
		tmpMax = new long[n];
		tmp = 0;
		for (int i = n - 1; i >= 0; i--) {
			tmp += (v[i] - 2 * (i == n - 1 ? c - x[i] : x[i + 1] - x[i]));
			tmpMax[i] = i == (n - 1) ? tmp : Math.max(tmpMax[i + 1], tmp);
		}
		tmp = 0;
		for (int i = 0; i < n - 1; i++) {
			tmp += (v[i] - (i == 0 ? x[i] : x[i] - x[i - 1]));
			answer4 = Math.max(answer4, tmp + tmpMax[i + 1]);
		}

		System.out.println(Math.max(Math.max(answer1, answer2), Math.max(answer3, answer4)));
	}

}
