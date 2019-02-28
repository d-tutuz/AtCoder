package 第13回日本情報オリンピック予選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Built using CHelper plug-in Actual solution is at the top
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		MyInput in = new MyInput(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskE solver = new TaskE();
		solver.solve(1, in, out);
		out.close();
	}

	static class TaskE {
		int n;
		int m;
		int[] C;
		int[] R;
		List<List<Integer>> graph = new ArrayList<>();

		public void solve(int testNumber, MyInput in, PrintWriter out) {

//			long start = System.currentTimeMillis();

			n = in.nextInt();
			m = in.nextInt();
			for (int i = 0; i < n; i++) {
				graph.add(new ArrayList<>());
			}
			C = new int[n];
			R = new int[n];
			for (int i = 0; i < n; i++) {
				C[i] = in.nextInt();
				R[i] = in.nextInt();
			}
			for (int i = 0; i < m; i++) {
				int u = in.nextInt() - 1;
				int v = in.nextInt() - 1;
				graph.get(u).add(v);
				graph.get(v).add(u);
			}

			PriorityQueue<Move> que = new PriorityQueue<>();
			que.add(new Move(0, 0));
			int[] cost = new int[n];
			Arrays.fill(cost, Integer.MAX_VALUE);
			cost[0] = 0;
			final boolean[] vis = new boolean[n];
			List<Integer> cur = new ArrayList<>();
			List<Integer> next = new ArrayList<>();

			while (!que.isEmpty()) {
				Move mv = que.poll();
				if (mv.cost != cost[mv.pos]) continue;

				Arrays.fill(vis, false);
				cur.clear();
				cur.add(mv.pos);
				vis[mv.pos] = true;
				for (int i = 0; i < R[mv.pos]; i++) {
					next.clear();
					for (int s : cur) {
						for (int t : graph.get(s)) {
							if (vis[t]) continue;
							vis[t] = true;
							next.add(t);
							if (cost[t] > mv.cost + C[mv.pos]) {
								cost[t] = mv.cost + C[mv.pos];
								que.add(new Move(t, cost[t]));
							}
						}
					}
					List<Integer> tmp = cur;
					cur = next;
					next = tmp;
				}
			}

			out.println(cost[n - 1]);

//			long end = System.currentTimeMillis();
//			out.println(end - start + "ms");
		}

		class Move implements Comparable<Move> {
			final int pos;
			final int cost;

			public Move(int pos, int cost) {
				this.pos = pos;
				this.cost = cost;
			}

			public int compareTo(Move o) {
				return Integer.compare(cost, o.cost);
			}

			@Override
			public String toString() {
				return "Move [pos=" + pos + ", cost=" + cost + "]";
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
