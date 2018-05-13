package abc088;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.InputMismatchException;
import java.util.Queue;
import java.util.StringTokenizer;

public class D_3 {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static int INF = 1 << 30;
	static int modP = 1000000007;

	static int[] mx = {1, -1, 0, 0};
	static int[] my = {0, 0, 1, -1};

	static char WHITE = '.';
	static char BLACK = '#';

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int H = in.nextInt(), W = in.nextInt();
			char[][] maze = new char[H][W];
			for (int i = 0; i < H; i++) {
				maze[i] = in.nextString().toCharArray();
			}

			int blackCount = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (maze[i][j] == BLACK) blackCount++;
				}
			}

			int sy = 0, sx = 0;
			int gy = H-1, gx = W-1;
			boolean[][] isVisited = new boolean[H][W];
			int[][] cost = new int[H][W];


			Queue<P> q = new ArrayDeque<>();
			q.add(new P(sx, sy));
			cost[sy][sx] = 1;
			isVisited[sy][sx] = true;

			while (!q.isEmpty()) {
				P p = q.remove();
				int px = p.x;
				int py = p.y;

				for (int i = 0; i < 4; i++) {
					int tx = px + mx[i];
					int ty = py + my[i];

					if (tx < 0 || ty < 0 || tx >= W || ty >= H || maze[ty][tx] == BLACK || isVisited[ty][tx]) {
						continue;
					}

					q.add(new P(tx, ty));
					cost[ty][tx] = cost[py][px] + 1;
					isVisited[ty][tx] = true;
				}
			}

			if (cost[gy][gx] == 0) {
				out.println(-1);
				return;
			}

			int ans = H*W-blackCount-cost[gy][gx];
			out.println(ans);

		}
	}
	static class P {
		int x, y, cost;
		P (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class InputReader {
		BufferedReader in;
		StringTokenizer tok;

		public String nextString() {
			while (!tok.hasMoreTokens()) {
				try {
					tok = new StringTokenizer(in.readLine(), " ");
				} catch (IOException e) {
					throw new InputMismatchException();
				}
			}
			return tok.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(nextString());
		}

		public long nextLong() {
			return Long.parseLong(nextString());
		}

		public double nextDouble() {
			return Double.parseDouble(nextString());
		}

		public int[] nextIntArray(int n) {
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt();
			}
			return res;
		}

		public long[] nextLongArray(int n) {
			long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong();
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}

	}

}
