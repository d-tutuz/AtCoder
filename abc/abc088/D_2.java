package abc088;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class D_2 {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		int h = in.nextInt();
		int w = in.nextInt();
		char[][] maze = new char[h][w];
		for (int x = 0; x < h; x++) {
			maze[x] = in.next().toCharArray();
		}
		int countdot = 0;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (maze[i][j] == '.') countdot++;
			}
		}
		int[][] v = new int[h][w];
		for (int y = 0; y < h; y++) {
			Arrays.fill(v[y], -1);
		}
		int sx = 0, sy = 0, gx = h-1, gy = w-1;
		int[] dx = {0,0,1,-1};
		int[] dy = {1,-1,0,0};
		Deque<P> q = new ArrayDeque<>();
		q.push(new P(sx, sy));
		v[sx][sy] = 1;

		while (!q.isEmpty()) {
			P p = q.pop();
			int x = p.x;
			int y = p.y;

			for (int i = 0; i < 4; i++) {
				int mx = x + dx[i];
				int my = y + dy[i];
				if (mx < 0 || mx >= h || my < 0 || my >= w || maze[mx][my] == '#') {
					continue;
				}
				if (v[mx][my] != -1) {
					continue;
				}
				q.add(new P(mx, my));
				v[mx][my] = v[x][y] + 1;
			}
		}
		int ans = v[gx][gy] != -1 ? countdot - v[gx][gy] : -1;
		System.out.println(ans);
	}

	public static class P {
		int x;	// 縦
		int y;	// 横

		public P(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
