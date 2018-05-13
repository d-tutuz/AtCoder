package abc021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class C_2 {

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
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int a = in.nextInt()-1, b = in.nextInt()-1;
			int m = in.nextInt();
			int[][] g = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					g[i][j] = i==j ? 0 : INF;
				}
			}
			int[] xs = new int[n], ys = new int[n];
			for (int i = 0; i < m; i++) {
				int x = in.nextInt()-1, y = in.nextInt()-1;
				xs[i] = x; ys[i] = y;
				g[x][y] = 1;
			}

			for (int k = 0; k < n; k++) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
					}
				}
			}

			List<Integer>[] dag = new ArrayList[n];
			dag = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);
			int[] count = new int[n];
			for (int i = 0; i < m; i++) {
				int x = xs[i], y = ys[i];
				if (g[a][b] == g[a][x]+g[y][b]+1) {
					dag[x].add(y);
					count[y]++;
				}
			}

			Queue<Integer> q = new ArrayDeque<>();
			List<Integer> topologicalSortList = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				if (count[i] == 0) {
					q.add(i);
					topologicalSortList.add(i);
				}
			}

			while (!q.isEmpty()) {
				int from = q.remove();
				for (int i = 0; i < dag[from].size(); i++) {
					int to = dag[from].get(i);
					count[to]--;
					if (count[to] == 0) {
						q.add(to);
						topologicalSortList.add(to);
					}
				}
			}

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
