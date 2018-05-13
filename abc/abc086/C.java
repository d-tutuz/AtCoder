package abc086;

import java.util.Scanner;

public class C {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] time = new int[N+1];		// 時間 t
		int[] x = new int[N+1];			// 場所 x
		int[] y = new int[N+1];			// 場所 y
		int[] point = new int[N+1];		// x + y の和

		int[] timeDiff = new int[N];	// tのi+1とiの差分
		int[] pointDiff = new int[N];	// x + y の和のi+1とiの差分の絶対値


		for (int i = 1; i <= N; i++) {
			time[i] = sc.nextInt();
			x[i] = sc.nextInt();
			y[i] = sc.nextInt();
			point[i] = x[i] + y[i];
		}

		for (int i = 0; i < N; i++) {
			timeDiff[i] = time[i+1] - time[i];
			pointDiff[i] = Math.abs(point[i+1] - point[i]);
		}

		for (int i = 0; i < N; i++) {
			if (timeDiff[i] < pointDiff[i]) {
				System.out.println("No"); return;
			} else if ((timeDiff[i] - pointDiff[i]) % 2 != 0) {
				System.out.println("No"); return;
			}
		}
		System.out.println("Yes");
		sc.close();

	}

}
