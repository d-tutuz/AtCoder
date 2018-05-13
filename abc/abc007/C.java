package abc007;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class C {

	static int[] a = {-1, 1, 0, 0};
	static int[] b = {0, 0, -1, 1};
	static char ng = '#';
	static char ok = '.';
	static int INF = 1 << 29;

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		int r = in.nextInt();
		int c = in.nextInt();
		int sy = in.nextInt();
		int sx = in.nextInt();
		int gy = in.nextInt();
		int gx = in.nextInt();

		char[][] maze = new char[r+1][c+1];
		int[][] dist = new int[r+1][c+1];
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				dist[i][j] = INF;
			}
		}
		for (int i = 1; i <= r; i++) {
			maze[i] = (" "+in.next()).toCharArray();
		}

		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(sy, sx));
		dist[sy][sx] = 0;

		while (!q.isEmpty()) {
			Point p = q.remove();
			int ny = p.y;
			int nx = p.x;
			for (int i = 0; i < 4; i++) {
				int my = ny + a[i];
				int mx = nx + b[i];
				if (my < 1 || mx < 1 || my > r || mx > c
						|| maze[my][mx] == ng
						|| dist[my][mx] <= dist[ny][nx] + 1) {
					continue;
				}
				dist[my][mx] = dist[ny][nx] + 1;
				q.add(new Point(mx, my));
			}
		}
		System.out.println(dist[gy][gx]);
	}

	static class Point {
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
