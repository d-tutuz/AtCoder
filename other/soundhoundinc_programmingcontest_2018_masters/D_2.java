package soundhoundinc_programmingcontest_2018_masters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class D_2 {

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

		@SuppressWarnings("unchecked")
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			long m = in.nextLong();
			int s = in.nextInt()-1, t = in.nextInt()-1;
			List<P>[] gy = new ArrayList[n], gs = new ArrayList[n];
			gy = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);
			gs = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

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

			PriorityQueue<P> q = new PriorityQueue<>();

			// スタートから円についてのダイクストラ
			P[] costYen = new P[n];
			for (int i = 0; i < n; i++) {
				costYen[i] = new P(i, LINF);
			}
			costYen[s].cost = 0;
			q.add(costYen[s]);

			while (!q.isEmpty()) {

				P p = q.remove();
				int now = p.to;

				for (P pt : gy[now]) {
					if (costYen[now].cost + pt.cost < costYen[pt.to].cost) {
						costYen[pt.to].cost = costYen[now].cost + pt.cost;
						q.add(costYen[pt.to]);
					}
				}
			}


			// ゴールからスヌーケについてのダイクストラ
			q = new PriorityQueue<>();
			P[] costSnuke = new P[n];
			for (int i = 0; i < n; i++) {
				costSnuke[i] = new P(i, LINF);
			}
			costSnuke[t].cost = 0;
			q.add(costSnuke[t]);

			while (!q.isEmpty()) {

				P p = q.remove();
				int now = p.to;

				for (P pt : gs[now]) {
					if (costSnuke[now].cost + pt.cost < costSnuke[pt.to].cost) {
						costSnuke[pt.to].cost = costSnuke[now].cost + pt.cost;
						q.add(costSnuke[pt.to]);
					}
				}
			}

			// i-1 年から順番に s -> i-1 -> t のコストを求める
			long[] ans = new long[n];
			long min = LINF;
			for (int i = n-1; i >= 0; i--) {
				long tmp = 0;
				tmp += costYen[i].cost;
				tmp += costSnuke[i].cost;
				min = Math.min(min, tmp);
				ans[i] = min;
			}

			for (long l : ans) {
				out.println((long)Math.pow(10, 15) - l);
			}


		}

		class P implements Comparable<P> {
			int to;
			long cost;

			public P(int to, long cost) {
				super();
				this.to = to;
				this.cost = cost;
			}

			@Override
			public int compareTo(P o) {
				return (int)(this.cost - o.cost);
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
