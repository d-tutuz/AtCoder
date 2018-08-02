package abc066;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;
import java.util.StringTokenizer;

public class D_2 {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		inputStream = new FileInputStream(new File("/workspace/Atcoder/abc/abc066/input.txt"));

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

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int[] a = in.nextIntArray(n+1);
			int idx = -1;
			Set<Integer> set = new HashSet<>();
			for (int i = 0; i < n+1; i++) {
				if (set.contains(a[i])) {
					idx = i;
				} else {
					set.add(a[i]);
				}
			}

			for (int k = 1; k <= n; k++) {
				if (k == 1) {
					out.println(n);
				} else if (k == 2) {
					long ret = 0;
					ret += comb(n+1-2, k);
					ret += comb(n+1-2, k-1) * 2 - comb(n-idx, k-1) * 2;
					ret += comb(n-2, k-2) * 2;
					out.println(ret);
				} else {
					long ret = 0;
					if (n+1-2-k >= 0) ret += comb(n+1-2, k);
					if (n-idx - (k-1) >= 0) ret += comb(n+1-2, k-1) * 2 - comb(n-idx, k-1) * 2;
					ret += comb(n-2, k-2) * 2;
					out.println(ret);
				}

			}

//			int k = 1;
//			long ans = comb(n+1-2, k);
//			if (k-1 >= 0) ans += comb(n+1-2, k-1);
//			if (k-2 >= 0) ans += comb(n+1-2, k-2);

//			System.out.println(ans);

		}


	}

	/**
	 * 二項係数
	 * 前提 n < modP
	 * nCr = n!/(r!*(n-r)!)である。この時分子分母にMODが来る場合は以下のように使用する
	 * */
	public static long comb(int n, int r) {
		return fact[n] % MOD * factInv[r] % MOD * factInv[n - r] % MOD;
	}

	/**
	 * 階乗数の逆元
	 *
	 * */
	public static int MAXN = 200000;

	static long[] fact = factorialArray(MAXN, MOD);
	static long[] factInv = factorialInverseArray(MAXN, MOD,
			inverseArray(MAXN, MOD));

	// 階乗の mod P テーブル
	public static long[] factorialArray(int maxN, long mod) {
		long[] fact = new long[maxN + 1];
		fact[0] = 1 % mod;
		for (int i = 1; i <= maxN; i++) {
			fact[i] = fact[i - 1] * i % mod;
		}
		return fact;
	}

	// 数 i に対する mod P での逆元テーブル
	public static long[] inverseArray(int maxN, long modP) {
		long[] inv = new long[maxN + 1];
		inv[1] = 1;
		for (int i = 2; i <= maxN; i++) {
			inv[i] = modP - (modP / i) * inv[(int) (modP % i)] % modP;
		}
		return inv;
	}

	// 階乗の逆元テーブル
	public static long[] factorialInverseArray(int maxN, long modP,
			long[] inverseArray) {
		long[] factInv = new long[maxN + 1];
		factInv[0] = 1;
		for (int i = 1; i <= maxN; i++) {
			factInv[i] = factInv[i - 1] * inverseArray[i] % modP;
		}
		return factInv;
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
