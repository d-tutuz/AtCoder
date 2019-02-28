package 第13回日本情報オリンピック予選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class E_4 {

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		MyInput in = new MyInput(inputStream);
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

		int n, k;
		int[] c, r;
		List<List<Integer>> g = new ArrayList<>();
		List<List<Integer>> h = new ArrayList<>();
		boolean[] visited;
		Queue<P> q;

		public void solve(int testNumber, MyInput in, PrintWriter out) {

//			long start = System.currentTimeMillis();

			n = in.nextInt();
			k = in.nextInt();
			c = new int[n];
			r = new int[n];
			for (int i = 0; i < n; i++) {
				c[i] = in.nextInt();
				r[i] = in.nextInt();
			}

			for (int i = 0; i < n; i++) {
				g.add(new ArrayList<>());
				h.add(new ArrayList<>());
			}


			for (int i = 0; i < k; i++) {
				int a = in.nextInt() - 1, b = in.nextInt() - 1;
				g.get(a).add(b);
				g.get(b).add(a);
			}

//			long mid1 = System.currentTimeMillis();
//			 out.println("mid1:" + (mid1 - start) + "ms");

			visited = new boolean[n];
			q = new ArrayDeque<>();
			for (int i = 0; i < n; i++) {
				bfs(i);
			}

//			long mid2 = System.currentTimeMillis();
//			 out.println("mid2:" + (mid2 - start) + "ms");

			PriorityQueue<P> q = new PriorityQueue<>();
			int[] cost = new int[n];
			boolean[] used = new boolean[n];
			Arrays.fill(cost, INF);
			cost[0] = 0;
			q.add(new P(0, 0));

			while (!q.isEmpty()) {

				P pp = q.remove();
				if (used[pp.first]) continue;
				used[pp.first] = true;

				for (int to : h.get(pp.first)) {
					if (cost[pp.first] + c[pp.first] < cost[to]) {
						cost[to] = cost[pp.first] + c[pp.first];
						q.add(new P(to, cost[to]));
					}
				}
			}

            out.println(cost[n-1]);

//			long end = System.currentTimeMillis();
//			out.println(end - start + "ms");

		}

		void bfs(int s) {

			Arrays.fill(visited, false);
			visited[s] = true;
			q.clear();
			q.add(new P(s, r[s]));

			while (!q.isEmpty()) {
				P p = q.remove();
				int now = p.first;
				for (int to : g.get(now)) {
					if (visited[to] || p.second <= 0) continue;
					q.add(new P(to, p.second-1));
					h.get(s).add(to);
					visited[to] = true;
				}
			}
		}

		class P implements Comparable<P> {
			final int first, second;

			public P(int first, int second) {
				super();
				this.first = first;
				this.second = second;
			}

			@Override
			public String toString() {
				return "P [first=" + first + ", second=" + second + "]";
			}

			@Override
			public int compareTo(P o) {
				return Integer.compare(this.second, o.second);
			}
		}
	}

	static class MyInput {
		private final BufferedReader in;
		private static int pos;
		private static int readLen;
		private static final char[] buffer = new char[1024 * 8];
		private static char[] str = new char[500 * 8 * 2];
		private static boolean[] isDigit = new boolean[256];
		private static boolean[] isSpace = new boolean[256];
		private static boolean[] isLineSep = new boolean[256];

		static {
			for (int i = 0; i < 10; i++) {
				isDigit['0' + i] = true;
			}
			isDigit['-'] = true;
			isSpace[' '] = isSpace['\r'] = isSpace['\n'] = isSpace['\t'] = true;
			isLineSep['\r'] = isLineSep['\n'] = true;
		}

		public MyInput(InputStream is) {
			in = new BufferedReader(new InputStreamReader(is));
		}

		public int read() {
			if (pos >= readLen) {
				pos = 0;
				try {
					readLen = in.read(buffer);
				} catch (IOException e) {
					throw new RuntimeException();
				}
				if (readLen <= 0) {
					throw new MyInput.EndOfFileRuntimeException();
				}
			}
			return buffer[pos++];
		}

		public int nextInt() {
			int len = 0;
			str[len++] = nextChar();
			len = reads(len, isSpace);
			int i = 0;
			int ret = 0;
			if (str[0] == '-') {
				i = 1;
			}
			for (; i < len; i++)
				ret = ret * 10 + str[i] - '0';
			if (str[0] == '-') {
				ret = -ret;
			}
			return ret;
		}

		public char nextChar() {
			while (true) {
				final int c = read();
				if (!isSpace[c]) {
					return (char) c;
				}
			}
		}

		int reads(int len, boolean[] accept) {
			try {
				while (true) {
					final int c = read();
					if (accept[c]) {
						break;
					}
					if (str.length == len) {
						char[] rep = new char[str.length * 3 / 2];
						System.arraycopy(str, 0, rep, 0, str.length);
						str = rep;
					}
					str[len++] = (char) c;
				}
			} catch (MyInput.EndOfFileRuntimeException e) {
			}
			return len;
		}

		static class EndOfFileRuntimeException extends RuntimeException {
		}

	}

}
