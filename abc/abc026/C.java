package abc026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.List;
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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			List<Integer>[] g = new ArrayList[n];
			g = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);
			for (int t = 1; t <= n-1; t++) {
				int f = in.nextInt()-1;
				g[f].add(t);
			}

			Deque<Integer> q = new ArrayDeque<Integer>();
			q.add(0);
			boolean[] visited = new boolean[n];
			int[] cost = new int[n];

			while (!q.isEmpty()) {

				int f = q.peek();
				boolean isAdd = false;
				for (int t : g[f]) {
					if (!visited[t]) {
						q.push(t);
						visited[t] = true;
						isAdd = true;
						break;
					}
				}
				if (isAdd) {
					continue;
				}
				f = q.pop();
				visited[f] = true;
				if (g[f].size() == 0) {
					cost[f] = 1;
				} else if (g[f].size() == 1) {
					cost[f] = cost[g[f].get(0)]*2 + 1;
				} else {
					int min = INF, max = -INF;
					for (int t : g[f]) {
						int c = cost[t];
						min = Math.min(min, c);
						max = Math.max(max, c);
					}
					cost[f] = max + min + 1;
				}

			}

			out.println(cost[0]);

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
