package 天下一プログラマーコンテスト2016予選A;

import static java.lang.Math.*;

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

public class B {

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
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		long[] cost;
		List<Integer>[] g;
		@SuppressWarnings("unchecked")
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt();
			g = new ArrayList[n];
			g = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			List<Integer>[] g2 = new ArrayList[n];
			g2 = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			for (int i = 0; i < n-1; i++) {
				int p = in.nextInt();
				g[i+1].add(p);
				g2[p].add(i+1);
			}

			int[] b = new int[m];
			int[] c = new int[m];
			cost = new long[n];
			Arrays.fill(cost, INF);
			cost[0] = 0;

			for (int i = 0; i < m; i++) {
				int leaf = in.nextInt();
				int weight = in.nextInt();
				b[i] = leaf;
				c[i] = weight;
				cost[leaf] = weight;
			}

			for (int i = 0; i < m; i++) {
				dfs(b[i]);
			}

			long ans = 0;
			Queue<Integer> q = new ArrayDeque<>();
			q.add(0);

			while (!q.isEmpty()) {
				int cur = q.remove();

				for (int next : g2[cur]) {
					ans += abs(cost[next] - cost[cur]);
					q.add(next);
				}
			}

			out.println(ans);

		}

		void dfs(int from) {

			int to = g[from].get(0);
			if (to == 0) return;

			cost[to] = Math.min(cost[to], cost[from]);
			dfs(to);
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

		public int[] nextIntArrayDec(int n) {
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt() - 1;
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

		public long[] nextLongArrayDec(int n) {
			long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong() - 1;
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
