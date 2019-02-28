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
import java.util.stream.Stream;

public class E_6 {

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		MyInput in = new MyInput(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static class TaskX {

		int n, k;
		int[] c, r;

		boolean[] visited;

		@SuppressWarnings("unchecked")
		public void solve(int testNumber, MyInput in, PrintWriter out) {

			n = in.nextInt(); k = in.nextInt();
			c = new int[n];
			r = new int[n];

			for (int i = 0; i < n; i++) {
				c[i] = in.nextInt();
				r[i] = in.nextInt();
			}

			List<Integer>[] g = new ArrayList[n];
			g = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			for (int i = 0; i < k; i++) {
				int a = in.nextInt()-1, b = in.nextInt()-1;
				g[a].add(b);
				g[b].add(a);
			}

			PriorityQueue<P> pq = new PriorityQueue<>();
			visited = new boolean[n];
			int[] cost = new int[n];
			Arrays.fill(cost, Integer.MAX_VALUE);
			cost[0] = 0;
			pq.add(new P(0, cost[0]));

			List<Integer> cur = new ArrayList<>();
			List<Integer> nex = new ArrayList<>();

			while (!pq.isEmpty()) {
				P pp = pq.remove();
				if (pp.second != cost[pp.first]) continue;

				cur.clear();
				cur.add(pp.first);
				Arrays.fill(visited, false);
				visited[pp.first] = true;

				for (int i = 0; i < r[pp.first]; i++) {

					nex.clear();

					for (int now : cur) {
						for (int to : g[now]) {
							if (visited[to]) continue;
							visited[to] = true;
							nex.add(to);
							if (cost[pp.first] + c[pp.first] < cost[to]) {
								cost[to] = cost[pp.first] + c[pp.first];
								pq.add(new P(to, cost[to]));
							}
						}
					}

					List<Integer> tmp = cur;
					cur = nex;
					nex = tmp;
				}
			}

			out.println(cost[n-1]);

		}
	}

	static class P implements Comparable<P> {
		int first, second;

		public P(int first, int second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public int compareTo(P o) {
			return Integer.compare(this.second, o.second);
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
