package agc001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class C {

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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;

	static class TaskX {

		@SuppressWarnings("unchecked")
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), k = in.nextInt();
			List<Integer>[] g = new ArrayList[n];
			g = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			for (int i = 0; i < n-1; i++) {
				int a = in.nextInt()-1, b = in.nextInt()-1;
				g[a].add(b);
				g[b].add(a);
			}

			boolean[] used = new boolean[n];
			int[] cost = new int[n];
			Queue<Integer> q = new ArrayDeque<>();
			q.add(0);
			used[0] = true;

			int max = 0, idx = -1;
			while (!q.isEmpty()) {
				int now = q.remove();

				for (int to : g[now]) {
					if (used[to]) continue;
					cost[to] = cost[now] + 1;
					used[to] = true;
					q.add(to);
					if (max < cost[to]) {
						max = cost[to];
						idx = to;
					}
				}
			}

			Arrays.fill(cost, 0);
			Arrays.fill(used, false);
			q.clear();

			q.add(idx);
			used[idx] = true;
			while (!q.isEmpty()) {
				int now = q.remove();

				for (int to : g[now]) {
					if (used[to]) continue;
					cost[to] = cost[now] + 1;
					used[to] = true;
					q.add(to);
				}
			}

			int d = Arrays.stream(cost).max().getAsInt();

			out.println(d);
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

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
