package soundhoundinc_programmingcontest_2018_masters;

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

public class D {

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
	static long LINF = 1L << 50;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		@SuppressWarnings("unchecked")
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt();
			int s = in.nextInt()-1, t = in.nextInt()-1;

			List<P>[] gs = new ArrayList[n], gy = new ArrayList[n];;
			gs = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);
			gy = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			for (int i = 0; i < m; i++) {
				int u = in.nextInt()-1;
				int v = in.nextInt()-1;
				long a = in.nextLong();
				long b = in.nextLong();

				gy[u].add(new P(v, a));
				gy[v].add(new P(u, a));

				gs[u].add(new P(v, b));
				gs[v].add(new P(u, b));
			}


			// スタートから円についてのダイクストラ
			Queue<Integer> q = new ArrayDeque<>();
			long[] costYen = new long[n];
			Arrays.fill(costYen, LINF);

			q.add(s);
			costYen[s] = 0;

			while (!q.isEmpty()) {
				int from = q.remove();
				for (P p : gy[from]) {
					if (costYen[from] + p.cost < costYen[p.node]) {
						costYen[p.node] = costYen[from] + p.cost;
						q.add(p.node);
					}
				}
			}

			// ゴールからスヌーケについてのダイクストラ
			q = new ArrayDeque<>();
			long[] costSnuke = new long[n];
			Arrays.fill(costSnuke, LINF);

			q.add(t);
			costSnuke[t] = 0;

			while (!q.isEmpty()) {
				int from = q.remove();
				for (P p : gs[from]) {
					if (costSnuke[from] + p.cost < costSnuke[p.node]) {
						costSnuke[p.node] = costSnuke[from] + p.cost;
						q.add(p.node);
					}
				}
			}

			long min = LINF;

			long[] ans = new long[n];
			for (int i = n-1; i >= 0; i--) {
				long tmp = 0;
				tmp += costYen[i];
				tmp += costSnuke[i];
				min = Math.min(min, tmp);
				ans[i] = (long)Math.pow(10, 15)-min;
			}

			for (long l : ans) {
				out.println(l);
			}

		}

		class P {
			int node;
			long cost;

			public P(int node, long cost) {
				super();
				this.node = node;
				this.cost = cost;
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
