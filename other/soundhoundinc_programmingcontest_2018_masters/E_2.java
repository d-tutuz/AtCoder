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
import java.util.Deque;
import java.util.List;
import java.util.stream.Stream;

public class E_2 {

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

		int n, m;
		List<P>[] g;
		@SuppressWarnings("unchecked")
		public void solve(int testNumber, MyInput in, PrintWriter out) {

			n = in.nextInt(); m = in.nextInt();
			int[] u = new int[m], v = new int[m], s = new int[m];
			g = new ArrayList[n];
			g = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			for (int i = 0; i < m; i++) {
				u[i] = in.nextInt()-1;
				v[i] = in.nextInt()-1;
				s[i] = in.nextInt();
				g[u[i]].add(new P(v[i], s[i]));
				g[v[i]].add(new P(u[i], s[i]));
			}

			// 二部グラフの場合
			if (isBipartite(0, 1, new int[n], g)) {

				// 仮として頂点 0 からコスト 1 で値を伝播させていく
				long[] cost = new long[n];
				Arrays.fill(cost, -LINF);
				cost[0] = 1;
				dfs(0, 1, cost);

				long[] turn = new long[n];
				boolean[] used = new boolean[n];
				Deque<P> q = new ArrayDeque<>();
				q.add(new P(0, 0));
				while (!q.isEmpty()) {
					P cur = q.removeLast();
					used[cur.t] = true;
					turn[cur.t] = cur.c;

					for (P nex : g[cur.t]) {
						int nexNode = nex.t;
						if (used[nexNode]) {
							turn[nexNode] = Math.max(turn[nexNode], turn[cur.t] + 1);
							continue;
						}
						q.addLast(new P(nexNode, (cur.c + 1)));
					}
				}

				// 偶数長の頂点でコストがマイナスの頂点は初期値を追加することで調整する
				long add = 0;
				for (int i = 0; i < n; i++) {
					if (turn[i] % 2 == 0 && cost[i] != -LINF && cost[i] < 0) {
						add = Math.max(add, -cost[i] + 1);
					}
				}

				dfs(0, 1 + add, cost);

				long ans = LINF;
				for (int i = 0; i < n; i++) {
					if (turn[i] % 2 == 1) {
						ans = Math.min(ans, cost[i]);
					}
				}

				out.println(Math.max(ans, 0));

			// 二部グラフでない場合
			// 偶数回/奇数回目で訪問する頂点が存在する
			} else {

				// 偶数回目で訪問する場合のコスト

				// 奇数回目で訪問する場合のコスト

				// その平均値でdfs
			}
		}

		void dfs(int cur, long v, long[] cost) {

			for (P p : g[cur]) {
				if (cost[p.t] == -LINF) {
					cost[p.t] = p.c - cost[cur];
					dfs(p.t, cost[p.t], cost);
				} else {
					if (Math.abs(cost[p.t] + cost[cur]) != p.c) {
						return;
					}
				}
			}
		}

		class P {
			int t;
			long c;

			public P(int t, long c) {
				super();
				this.t = t;
				this.c = c;
			}
		}

		boolean isBipartite(int v, int c, int[] color, List<P>[] g) {
			color[v] = c;
			for (P nex : g[v]) {
				if (color[nex.t] == c) {
					return false;
				}
				if (color[nex.t] == 0 && !isBipartite(nex.t, -c, color, g)) {
					return false;
				}
			}
			return true;
		}
	}

	static void fill(long[][][] a, long v) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				for (int k = 0; k < a[0][0].length; k++) {
					a[i][j][k] = v;
				}
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

		public char[][] next2DChars(int h, int w) {
			char[][] s = new char[h][w];
			for (int i = 0; i < h; i++) {
				s[i] = nextChars();
			}
			return s;
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
