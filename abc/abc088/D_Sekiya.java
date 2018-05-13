package abc088;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * Built using CHelper plug-in Actual solution is at the top
 */
public class D_Sekiya {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		Scanner in = new Scanner(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskD solver = new TaskD();
		solver.solve(1, in, out);
		out.close();
	}

	static class TaskD {
		final int[] dx = new int[] { 1, 0, 0, -1 };
		final int[] dy = new int[] { 0, 1, -1, 0 };
		private Pair<Integer, Pair<Integer, Integer>> now;

		public void solve(int testNumber, Scanner in, PrintWriter out) {
			int H = in.nextInt();
			int W = in.nextInt();
			char[][] S = new char[H][];
			for (int i = 0; i < H; i++) {
				S[i] = in.next().toCharArray();
			}
			Integer[][] vis = new Integer[H][W];
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					vis[i][j] = (int) 1e9;
				}
			}
			Queue<Pair<Integer, Pair<Integer, Integer>>> que = new ArrayDeque<>();
			que.add(Pair.MakePair(1, Pair.MakePair(0, 0)));
			while (!que.isEmpty()) {
				now = que.poll();
				if (vis[now.second.first][now.second.second] <= now.first) {
					continue;
				}
				vis[now.second.first][now.second.second] = now.first;
				for (int d = 0; d < 4; d++) {
					int nx = now.second.first + dx[d];
					int ny = now.second.second + dy[d];
					if (0 <= nx && nx < H && 0 <= ny && ny < W
							&& S[nx][ny] == '.') {
						que.add(Pair.MakePair(now.first + 1,
								Pair.MakePair(nx, ny)));
					}
				}
			}
			if (vis[H - 1][W - 1] < (int) 1e9) {
				int cnt = 0;
				for (int i = 0; i < H; i++) {
					for (int j = 0; j < W; j++) {
						if (S[i][j] == '#') {
							cnt++;
						}
					}
				}
				out.println(H * W - cnt - vis[H - 1][W - 1]);
			} else {
				out.println(-1);
			}
		}

	}

	static class Pair<T1, T2> {
		public T1 first;
		public T2 second;

		public Pair(T1 f, T2 s) {
			this.first = f;
			this.second = s;
		}

		public static <T1, T2> Pair MakePair(T1 f, T2 s) {
			return new Pair<T1, T2>(f, s);
		}

	}
}