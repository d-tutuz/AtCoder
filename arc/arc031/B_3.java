package arc031;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class B_3 {

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
		for (int x = 0; x < len; x++) {
			for (int y = 0; y < len; y++) {
				if (map[x][y] == ok) {
					mapOkCount++;
				}
			}
		}

		// solve
		for (int sx = 0; sx < len; sx++) {
			for (int sy = 0; sy < len; sy++) {
				int count = 0;
				char beforeMapString = map[sx][sy];
				map[sx][sy] = ok;
				boolean[][] visited = new boolean[len][len];
				visited[sx][sy] = true;
				Deque<Point> stack = new ArrayDeque<>();
				stack.push(new Point(sx, sy));

				while (!stack.isEmpty()) {

					Point p = stack.peek();
					boolean isNext = false;
					int nx = p.x;
					int ny = p.y;
					for (int i = 0; i < 4; i++) {
						int mx = nx + a[i];
						int my = ny + b[i];

						if (mx < 0 || my < 0 || mx >= len || my >= len
								|| visited[mx][my] || map[mx][my] == ng) {
							continue;
						}
						isNext = true;
						count++;
						visited[mx][my] = true;
						stack.push(new Point(mx, my));
						break;
					}
					if (!isNext) {
						stack.pop();
					}
				}
				map[sx][sy] = beforeMapString;
				if (mapOkCount == count) {
					System.out.println("YES");
					return;
				}

			}
		}
		System.out.println("NO");

	}
	static class Point {
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
