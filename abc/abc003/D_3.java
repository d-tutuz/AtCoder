package abc003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class D_3 {

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

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int r = in.nextInt(), c = in.nextInt(), x = in.nextInt(), y = in.nextInt();
			int d = in.nextInt(), l = in.nextInt();

			long sum = 0;
			for (int i = 0; i < 1 << 4; i++) {
				int tx = x;
				int ty = y;

				if ((i >> 0 & 1) == 1) ty--;
				if ((i >> 1 & 1) == 1) ty--;
				if ((i >> 2 & 1) == 1) tx--;
				if ((i >> 3 & 1) == 1) tx--;

				if (tx < 0 || ty < 0) continue;

				if (Integer.bitCount(i) % 2 == 0) {
					sum += comb(tx * ty, d + l) * comb(d + l, d);
				} else {
					sum -= comb(tx * ty, d + l) * comb(d + l, d) % MOD;
				}
				sum = (sum + MOD) % MOD;
			}

			sum *= (r - x + 1) * (c - y + 1);

			out.println(sum % MOD);

		}

	}

	/**
	 * 二項係数
	 * 前提 n < modP
	 * nCr = n!/(r!*(n-r)!)である。この時分子分母にMODが来る場合は以下のように使用する
	 * */
	public static long comb(int n, int r) {
		if (r < 0 || r > n) return 0L;
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
