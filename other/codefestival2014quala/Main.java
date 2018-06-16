package codefestival2014quala;

import java.util.Arrays;

public class Main {

	static long[][][][] memo = new long[2][20][1 << 10][2];
	static int[] a;
	static int k;

	private static void solve() {
		long A = nl();
		k = ni();
		System.out.println(solve(A));
	}

	private static long solve(long A) {

		// input
		long src = A;

		// 桁数算出(1234)
		int n = (A + "").length();
		a = new int[n];
		for (int i = 0; i < n; i++) {
			// 下の桁の数字からa[i]に入れる。a[0]=4
			a[i] = (int) (A % 10);
			A /= 10;
		}

		for (long[][][] v : memo)
			for (long[][] u : v)
				for (long[] w : u)
					Arrays.fill(w, -10);

		// inputの数字の桁数-1の最大値(999)
		long lv = 0;
		for (int i = 0; i < n - 1; i++) {
			lv *= 10;
			lv += 9;
		}

		long min = Math.abs(src - recMin(n - 1, 0, 1));
		long max = Math.abs(src - recMax(n - 1, 0, 1));
		long mid = Math.abs(src - lv);
		long[] arr = { min, max, mid };
		return Arrays.stream(arr).min().getAsLong();
	}

	private static long recMin(int i, int used, int tight) {

		// 桁を最後まで見た場合
		if (i < 0) return 0;

		// すでに参照したことがある場合
		if (memo[1][i][used][tight] >= 0)
			return memo[1][i][used][tight];

		int cnt = Integer.bitCount(used);
		long ret = Long.MAX_VALUE;

		long dd = 1;
		for (int j = 0; j < i; j++) {
			dd *= 10;
		}

		for (int d = 0; d < 10; d++) {

			// 使用しているビットが k 未満または、すでに使用しているビットを使う場合
			if (cnt < k || (used & (1 << d)) != 0) {

				// すでに N 未満または 桁の数字以下
				// 上位の桁の数字に最も近いものを選択する
				if (tight == 0 || a[i] <= d) {
					long v = recMin(i - 1, used | (1 << d), tight == 1 && a[i] == d ? 1 : 0);
					if (v < Long.MAX_VALUE) ret = Math.min(v + d * dd, ret);
				}
			}
		}
		memo[1][i][used][tight] = ret;
		return ret;
	}

	private static long recMax(int i, int used, int tight) {
		if (i < 0)
			return 0;
		if (memo[0][i][used][tight] >= -1)
			return memo[0][i][used][tight];

		int cnt = Integer.bitCount(used);
		long ret = -1;

		long dd = 1;
		for (int j = 0; j < i; j++) {
			dd *= 10;
		}

		for (int d = 0; d < 10; d++) {
			if (cnt < k || (used & (1 << d)) != 0) {
				if (tight == 0 || a[i] >= d) {
					long v = recMax(i - 1, used | (1 << d), tight == 1 && a[i] == d ? 1 : 0);
					if (v >= 0)
						ret = Math.max(v + d * dd, ret);
				}
			}
		}
		memo[0][i][used][tight] = ret;
		return ret;
	}

	public static void main(String[] args) {
		new Thread(null, new Runnable() {
			@Override
			public void run() {
				long start = System.currentTimeMillis();
				String debug = System.getProperty("debug");
				if (debug != null) {
					try {
						is = java.nio.file.Files
								.newInputStream(java.nio.file.Paths.get(debug));
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				reader = new java.io.BufferedReader(
						new java.io.InputStreamReader(is), 32768);
				solve();
				out.flush();
				tr((System.currentTimeMillis() - start) + "ms");
			}
		}, "", 64000000).start();
	}

	private static java.io.InputStream is = System.in;
	private static java.io.PrintWriter out = new java.io.PrintWriter(System.out);
	private static java.util.StringTokenizer tokenizer = null;
	private static java.io.BufferedReader reader;

	public static String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new java.util.StringTokenizer(reader.readLine());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	private static double nd() {
		return Double.parseDouble(next());
	}

	private static long nl() {
		return Long.parseLong(next());
	}

	private static int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	private static char[] ns() {
		return next().toCharArray();
	}

	private static long[] nal(int n) {
		long[] a = new long[n];
		for (int i = 0; i < n; i++)
			a[i] = nl();
		return a;
	}

	private static int[][] ntable(int n, int m) {
		int[][] table = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				table[i][j] = ni();
			}
		}
		return table;
	}

	private static int[][] nlist(int n, int m) {
		int[][] table = new int[m][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				table[j][i] = ni();
			}
		}
		return table;
	}

	private static int ni() {
		return Integer.parseInt(next());
	}

	private static void tr(Object... o) {
		if (is != System.in)
			System.out.println(java.util.Arrays.deepToString(o));
	}
}
