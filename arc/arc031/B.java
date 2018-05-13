package arc031;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class B {

	static int[] a = {-1, 1, 0, 0};
	static int[] b = {0, 0, -1, 1};
	static int len = 10;
	static char ng = 'x';
	static char ok = 'o';

	public static void main(String[] args) throws IOException {

//		Scanner in = new Scanner(new File("/workspace/Atcoder/src/arc031/sampleB_1.txt"));
		Scanner in = new Scanner(System.in);
		int mapOkCount = 0;
		char[][] map = new char[len][len];
		for (int i = 0; i < len; i++) {
			map[i] = in.next().toCharArray();
		}
		boolean isFirst = true;
		int sx = 0, sy = 0;
		for (int x = 0; x < len; x++) {
			for (int y = 0; y < len; y++) {
				if (map[x][y] == ok) {
					mapOkCount++;
					if (isFirst) {
						sx = x;
						sy = y;
						isFirst = false;
					}
				}
			}
		}

		// solve
		boolean[][] visited = new boolean[len][len];
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(sx, sy, true));
		visited[sx][sy] = true;
		int okCount = 0;
		int ng2okCount = 0;
		okCount++;
		while (!q.isEmpty()) {
			Point p = q.remove();
			int nx = p.x;
			int ny = p.y;
			for (int i = 0; i < 4; i++) {
				int mx = nx + a[i];
				int my = ny + b[i];
				boolean isOk = p.isOk;
				if (mx < 0 || my < 0 || mx >= len || my >= len || visited[mx][my]) {
					continue;
				}
				if (!isOk && map[mx][my] == ng) {
					continue;
				}
				if (map[mx][my] == ng) {
					isOk = false;
					q.add(new Point(mx, my, isOk));
				} else if (map[mx][my] == ok){
					if (!isOk) ng2okCount++;
					isOk = true;
					q.add(new Point(mx, my, isOk));
					okCount++;
					visited[mx][my] = true;
				}
			}
		}

		System.out.println(okCount);
		System.out.println(mapOkCount);
		System.out.println(ng2okCount);


		if (okCount == mapOkCount && ng2okCount <= 1) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}

	}
	static class Point {
		int x;
		int y;
		boolean isOk;
		public Point(int x, int y, boolean isOk) {
			this.x = x;
			this.y = y;
			this.isOk = isOk;
		}
	}

}
