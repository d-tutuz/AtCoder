package 第4回ドワンゴからの挑戦状予選;

//package other2018.dwango2018.qual;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class D_2 {
	static final int INF = 1000000000;

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int n = in.nextInt();
		amount = new int[n];
		for (int i = 0; i < n; i++) {
			amount[i] = in.nextInt();
		}
		int[] amountSet = new int[1 << n];
		int[] dependencies = new int[n];
		for (int i = 0; i < (1 << n); i++) {
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) >= 1) {
					amountSet[i] += amount[j];
				}
			}
		}

		int[] parent = new int[n];
		int[] childrenAmount = new int[n];
		for (int i = 1; i < n; i++) {
			parent[i] = in.nextInt() - 1;
			dependencies[parent[i]] |= 1 << i;
			childrenAmount[parent[i]] += amount[i];
		}

		graph = new List[n];
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 1; i < n; i++) {
			graph[parent[i]].add(i);
		}
		coverAmount = new int[1 << n];
		for (int i = 0; i < coverAmount.length; i++) {
			dfs(0, i);
		}

		int[] dp = new int[1 << n];
		Arrays.fill(dp, INF);
		dp[0] = 0;

		for (int p = 0; p < (1 << n); p++) {
			if (dp[p] == INF) {
				continue;
			}
			for (int ne = 0; ne < n; ne++) {
				if ((p & (1 << ne)) >= 1) {
					continue;
				}
				if ((p & dependencies[ne]) == dependencies[ne]) {
					int tp = p | (1 << ne);
					int to = Math.max(dp[p], coverAmount[tp]
							+ childrenAmount[ne]);
					if (dp[tp] > to) {
						dp[tp] = to;
					}
				}
			}
		}

		out.println(dp[(1 << n) - 1]);
		out.flush();
	}

	static List<Integer>[] graph;
	static int[] amount;
	static int[] coverAmount;

	static void dfs(int now, int p) {
		if ((p & (1 << now)) >= 1) {
			coverAmount[p] += amount[now];
			return;
		}
		for (int to : graph[now]) {
			dfs(to, p);
		}
	}

	public static void debug(Object... o) {
		System.err.println(Arrays.deepToString(o));
	}

	public static class InputReader {
		private static final int BUFFER_LENGTH = 1 << 12;
		private InputStream stream;
		private byte[] buf = new byte[BUFFER_LENGTH];
		private int curChar;
		private int numChars;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		private int next() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public char nextChar() {
			return (char) skipWhileSpace();
		}

		public String nextToken() {
			int c = skipWhileSpace();
			StringBuilder res = new StringBuilder();
			do {
				res.append((char) c);
				c = next();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public int nextInt() {
			return (int) nextLong();
		}

		public long nextLong() {
			int c = skipWhileSpace();
			long sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = next();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = next();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public double nextDouble() {
			return Double.valueOf(nextToken());
		}

		int skipWhileSpace() {
			int c = next();
			while (isSpaceChar(c)) {
				c = next();
			}
			return c;
		}

		boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}
}
