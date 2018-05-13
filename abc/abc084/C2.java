package abc084;

import java.util.Scanner;
public class C2 {

	public static void main(String[] args) {

		int N, time;
		int[] C = new int[500];
		int[] S = new int[500];
		int[] F = new int[500];

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for (int i = 0; i < N - 1; i++) {
			C[i] = sc.nextInt();
			S[i] = sc.nextInt();
			F[i] = sc.nextInt();
		}

		for (int i = 0; i < N; i++) {
			time = 0;
			for (int j = i; j < N - 1; j++) {
				time += calcTime(time, C[j], S[j], F[j]);
			}
			System.out.println(time);
		}
	}

	private static int calcTime(int ft, int C, int S, int F) {
		int tt;

		if (ft < S) tt = S;
		else if (ft % F == 0) tt = ft;
		else tt = ft + F - ft % F;

		return tt + C - ft;
	}
}
