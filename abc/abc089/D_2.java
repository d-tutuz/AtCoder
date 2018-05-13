package abc089;

import java.io.IOException;
import java.util.Scanner;

public class D_2 {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
//		in = new Scanner(new File("/workspace/Atcoder/src/abc089/d_sample1.txt"));

		// input
		int h = in.nextInt();
		int w = in.nextInt();
		int d = in.nextInt();

		Point[] p = new Point[90001];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int a = in.nextInt();
				p[a] = new Point(i, j);
			}
		}
		int q = in.nextInt();
		int[] l = new int[q];
		int[] r = new int[q];
		for (int i = 0; i < q; i++) {
			l[i] = in.nextInt();
			r[i] = in.nextInt();
		}

		// solve
		for (int i = 0; i < q; i++) {
			long ans = 0;
			int left = l[i];
			int right = r[i];
			while (left != right) {

				int moveLeft = left + d;
				ans += move(
						p[left].x
						, p[left].y
						, p[moveLeft].x
						, p[moveLeft].y);

				left = moveLeft;

			}
			System.out.println(ans);
		}
	}
	static class Point {
		int x;
		int y;
		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int move (int x, int y, int i, int j) {
		int abs = 0;
		abs = Math.abs(x-i) + Math.abs(y-j);
		return abs;
	}
}
