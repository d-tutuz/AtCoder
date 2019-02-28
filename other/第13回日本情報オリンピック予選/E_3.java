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
import java.util.stream.Stream;

public class E_3 {

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
		List<Integer>[] g;
		List<Integer>[] h;

		boolean[] vis;
		List<Integer> cur = new ArrayList<>();
		List<Integer> next = new ArrayList<>();

		@SuppressWarnings("unchecked")
		public void solve(int testNumber, MyInput in, PrintWriter out) {

			long start = System.currentTimeMillis();

			n = in.nextInt(); k = in.nextInt();
			c = new int[n]; r = new int[n];
			for (int i = 0; i < n; i++) {
				c[i] = in.nextInt();
				r[i] = in.nextInt();
			}

			g = new ArrayList[n];
			g = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			for (int i = 0; i < k; i++) {
				int a = in.nextInt()-1, b = in.nextInt()-1;
				g[a].add(b);
				g[b].add(a);
			}

			h = new ArrayList[n];
			h = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			vis = new boolean[n];
			cur = new ArrayList<>();
			next = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				bfs(i);
			}

			PriorityQueue<P> pq = new PriorityQueue<>();
			int[] cost = new int[n];
			Arrays.fill(cost, INF);
			cost[0] = 0;
			pq.add(new P(0, 0));

			while (!pq.isEmpty()) {

				P pp = pq.remove();
				if (cost[pp.first] != pp.second) continue;

				for (int to : h[pp.first]) {
					if (cost[pp.first] + c[pp.first] < cost[to]) {
						cost[to] = cost[pp.first] + c[pp.first];
						pq.add(new P(to, cost[to]));
						if (cost[n-1] != INF) break;
					}
				}
			}

			out.println(cost[n-1]);

			long end = System.currentTimeMillis();
			out.println(end - start + "ms");

		}

		void bfs(int s) {

			boolean[] visited = new boolean[n];
			visited[s] = true;
			Queue<P> q = new ArrayDeque<>();
			q.add(new P(s, r[s]));

			while (!q.isEmpty()) {
				P p = q.remove();
				int now = p.first;
				for (int to : g[now]) {
					if (visited[to] || p.second <= 0) continue;
					q.add(new P(to, p.second-1));
					h[s].add(to);
					visited[to] = true;
				}
			}
		}

		void bfs(int ss, boolean flag) {
			Arrays.fill(vis, false);
			cur.clear();
			cur.add(ss);
			vis[ss] = true;
			for (int i = 0; i < r[ss]; i++) {
				next.clear();
				for (int s : cur) {
					for (int t : g[s]) {
						if (vis[t]) continue;
						vis[t] = true;
						next.add(t);
						h[ss].add(t);
					}
				}
				List<Integer> tmp = cur;
				cur = next;
				next = tmp;
			}
		}

		class P implements Comparable<P> {
			int first, second;

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

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + first;
				result = prime * result + second;
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				P other = (P) obj;
				if (first != other.first)
					return false;
				if (second != other.second)
					return false;
				return true;
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

		public long nextLong() {
			int len = 0;
			str[len++] = nextChar();
			len = reads(len, isSpace);
			int i = 0;
			long ret = 0;
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

		public String nextString() {
			return new String(nextChars());
		}

		public char[] nextChars() {
			int len = 0;
			str[len++] = nextChar();
			len = reads(len, isSpace);
			return Arrays.copyOf(str, len);
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

		public int[] nextIntArray(final int n) {
			final int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt();
			}
			return res;
		}

		public int[] nextIntArray1Index(final int n) {
			final int[] res = new int[n + 1];
			for (int i = 1; i < n + 1; i++) {
				res[i] = nextInt();
			}
			return res;
		}

		public int[] nextIntArrayDec(final int n) {
			final int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt() - 1;
			}
			return res;
		}

		public long[] nextLongArray(final int n) {
			final long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong();
			}
			return res;
		}

		public long[] nextLongArray1Index(final int n) {
			final long[] res = new long[n + 1];
			for (int i = 1; i < n + 1; i++) {
				res[i] = nextLong();
			}
			return res;
		}

		public long[] nextLongArrayDec(final int n) {
			final long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong() - 1;
			}
			return res;
		}

		public double nextDouble() {
			return Double.parseDouble(nextString());
		}

		public double[] nextDoubleArray(int n) {
			double[] res = new double[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextDouble();
			}
			return res;
		}

		static class EndOfFileRuntimeException extends RuntimeException {
		}

	}

}
