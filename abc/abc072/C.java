package abc072;

import java.util.Arrays;
import java.util.Scanner;

public class C {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] X = new int[100010];
		int ans = -1;

		for (int i = 0; i < N; i++) {
			int ai = sc.nextInt() + 1;
			X[ai-1]++;
			X[ai]++;
			X[ai+1]++;
		}

		Arrays.sort(X);
		System.out.println(X[X.length-1]);
	}

}
