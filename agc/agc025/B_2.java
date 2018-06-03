package agc025;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class B_2 {

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
	static int MOD = 998244353;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			long a = in.nextLong(), b = in.nextLong(), k = in.nextLong();

			long ans = 0;

			for (int x = 0; x <= n; x++) {

				if ((k - a*x) % b != 0) {
					continue;
				} else if ((k - a*x) / b > n || (k - a*x) / b < 0) {
					continue;
				} else {
					int y = (int)((k - a*x) / b);
					ans += comb(n, x) % MOD * comb(n, y) % MOD;
				}
			}

			out.println(ans % MOD);

		}
	}

	public static long comb(int n,int r) {
		return fact[n] % MOD * factInv[r] % MOD * factInv[n-r] % MOD;
	}

	public static int MAXN = 400000;

	static long[] fact = factorialArray(MAXN, MOD);
	static long[] factInv = factorialInverseArray(MAXN, MOD, inverseArray(MAXN, MOD));

	// 階乗の mod P テーブル
	public static long[] factorialArray(int maxN,long mod) {
		long[] fact = new long[maxN+1];
		fact[0] = 1 % mod;
		for(int i=1;i<=maxN;i++) {
			fact[i] = fact[i-1] * i % mod;
		}
		return fact;
	}
	// 数 i に対する mod P での逆元テーブル
	public static long[] inverseArray(int maxN,long modP) {
		long[] inv = new long[maxN+1];
		inv[1] = 1;
		for(int i=2;i<=maxN;i++) {
			inv[i] = modP - (modP / i) * inv[(int) (modP%i)] % modP;
		}
		return inv;
	}

	// 階乗の逆元テーブル
	public static long[] factorialInverseArray(int maxN,long modP,long[] inverseArray) {
		long[] factInv = new long[maxN+1];
		factInv[0] = 1;
		for(int i=1;i<=maxN;i++) {
			factInv[i] = factInv[i-1] * inverseArray[i] % modP;
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
