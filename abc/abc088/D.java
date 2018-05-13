package abc088;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class D {

	static int sx, sy, gx, gy, h, w;
	static int[][] d;

	// 4 近傍
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};

	static char[][] maze;

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		h = in.nextInt();
		w = in.nextInt();

		// d[y][x] := (sx,sy) から (x,y) までの最短距離
		d = new int[h][w];

		// 迷路を表す文字列の配列
		maze = new char[h][w];
		for (int i = 0; i < h; i++) {
			maze[i] = in.next().toCharArray();
		}

		int countw = 0;
		for (int x = 0; x < h; x++) {
			for (int y = 0; y < w; y++) {
				if (maze[x][y] == '.') {
					countw++;
				}
			}
		}

		// ゴール
		gx = h-1;
		gy = w-1;
		int ans = bfs();
		if (ans == -1) {
			System.out.println(ans);
		} else {
			System.out.println(countw-ans);
		}
		System.out.println("ans:" + ans);

	}

	static int bfs() {

		Deque<P> q = new ArrayDeque<>();

		// 全ての点をINFで初期化
		for (int x = 0; x < h; x++) {
			for (int y = 0; y < w; y++) {
				d[x][y] = Integer.MAX_VALUE;
			}
		}

		// スタート地点をキューに入れて、その点の距離を0にする
		q.push(new P(sx,sy));
		d[sx][sy] = 1;

		while (!q.isEmpty()) {
			P p = q.pop();
			int x = p.first;
			int y = p.second;

			if (x == gx && y == gy) break;
			for (int i = 0; i < 4; i++) {
				int mx = x + dx[i];
				int my = y + dy[i];

				// 範囲外に出たときは次の処理へ
				if( mx < 0 || my < 0 || mx >= h || my >= w ) continue;



				// 移動可能でまだ訪れたことがなければキューに入れる
//				if(maze[mx][my] != '#' && d[mx][my] == Integer.MAX_VALUE){
				if(maze[mx][my] != '#' && d[mx][my] < d[x][y] + 1){
					q.push(new P(mx,my));
					d[mx][my] = d[x][y] + 1;
				}
			}
		}
		return d[gx][gy];
	}

	public static class P {
		int first = 0;
		int second = 0;

		public P(int x, int y) {
			first = x;
			second = y;
		}
	}

}
