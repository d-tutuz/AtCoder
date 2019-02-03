package math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class IntegerUtils {

	/**
	 * 最大公約数
	 * */
	public static long gcd(long a, long b) {
		return (b == 0) ? a : gcd(b, a % b);
	}

	/**
	 * 最小公倍数
	 * */
	public static long lcm(long a, long b) {
		return a * b / gcd(a, b);
	}

	/**
	 * 桁和
	 * */
	public static int digitSum(int n, long base) {
		int ret = 0;
		while (n > 0) {
			ret += n % base;
			n /= base;
		}
		return ret;
	}

	/**
	 * エラトステネスの篩
	 *  => 遅いので元の数が大きい場合は使えないっぽい。
	 *
	 * @return 素数リスト
	 * */
	public static Integer[] getPrimes(int n) {
		boolean[] isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i < isPrime.length; i++) {
			if (!isPrime[i])
				continue;
			for (int j = i + i; j < isPrime.length; j += i) {
				isPrime[j] = false;
			}
		}

		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < isPrime.length; i++) {
			if (isPrime[i]) {
				list.add(i);
			}
		}
		return list.toArray(new Integer[list.size()]);
	}

	/**
	 * エラトステネスの篩
	 *
	 * @return boolean[]のバケット配列
	 * */
	public static void getPrimes(boolean[] isPrime, int n) {
		isPrime = new boolean[n];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i <= Math.sqrt(isPrime.length); i++) {
			if (!isPrime[i])
				continue;
			for (int j = i + i; j < isPrime.length; j += i) {
				isPrime[j] = false;
			}
		}
	}

	/**
	 * 素数判定
	 * 計算量：O(logN)
	 * */
	boolean isPrime(int n) {

		if (n == 1) return false;
		if (n == 2) return true;

		boolean ret = true;

		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				ret = false;
			}
		}

		return ret;
	}

	/**
	 * 素因数分解
	 * 計算量：O(logN)
	 *
	 * p[i]^c の Map<P[i], c> を返す
	 * */
	Map<Long, Integer> getPrimeFactorization(long n) {
		Map<Long, Integer> map = new TreeMap<>();
		for (long i = 2; i * i <= n; i++) {
			while (n % i == 0) {
				map.merge(i, 1, Integer::sum);
				n /= i;
			}
		}
		if (n > 1) map.merge(n, 1, Integer::sum);
		return map;
	}

	/**
	 * 二項係数：パスカルの三角形
	 * 計算量：O(N^2)
	 * r * nCr = n * n-1Cr-1
	 * */
	static long pascal(int a, int b) {
		if (b == 0) return 1;
		long res = pascal(a - 1, b - 1);
		res *= a;
		res /= b;
		return res;
	}

	/**
	 * 累乗[a^e (mod modP)]
	 *
	 * @param a : 被累乗数
	 * @param e : 累乗数
	 * @param modP : mod数
	 * */
	static long power(long a, long e, long modP) {
		long ret = 1;
		for (; e > 0; e /= 2) {
			if (e % 2 != 0) {
				ret = (ret * a) % modP;
			}
			a = (a * a) % modP;
		}
		return ret;
	}

	/**
	 * 二項係数
	 * 前提 n < modP
	 * nCr = n!/(r!*(n-r)!)である。この時分子分母にMODが来る場合は以下のように使用する
	 * */
	public static long comb(int n,int r) {
		if (r < 0 || r > n) return 0L;
		return fact[n] % MOD * factInv[r] % MOD * factInv[n-r] % MOD;
	}

	/**
	 * 階乗数の逆元
	 *
	 * */
	public static int MAXN = 200000;
	public static long MOD = 1000000007;

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

	/**
	 * 順列(N!)
	 *
	 * @input {0 ～ n-1}の数が入った配列 ※配列は sort 済であること！！
	 * @output 順列に並びられた1回分の配列 a[]
	 *         配列 a[] の中の値が順列される。(次の順列になる)
	 *
	 * @sample
	 * while(Permutation.next(a)) {
	 * 		array[a[i]][a[i+1]]
	 * }
	 *
	 * */
	static class Permutation {

		// array ver
		public static boolean next(int[] a) {
			int n = a.length;

			int i = n - 1;
			while (i > 0 && a[i - 1] >= a[i])
				i--;
			if (i <= 0)
				return false;

			int j = n - 1;
			while (a[j] <= a[i - 1])
				j--;
			swap(a, i - 1, j);

			int k = n - 1;
			while (i < k)
				swap(a, i++, k--);

			return true;
		}

		private static void swap(int[] a, int i, int j) {
			int tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
		}

		// strings ver
		public static boolean next(char[] a) {
			int n = a.length;

			int i = n - 1;
			while (i > 0 && a[i - 1] >= a[i])
				i--;
			if (i <= 0)
				return false;

			int j = n - 1;
			while (a[j] <= a[i - 1])
				j--;
			swap(a, i - 1, j);

			int k = n - 1;
			while (i < k)
				swap(a, i++, k--);

			return true;
		}

		private static void swap(char[] a, int i, int j) {
			char tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
		}
	}

	// 多次元 min, max
	static long max(long... n) {
		long ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.max(ret, n[i]);
		}
		return ret;
	}

	static int max(int... n) {
		int ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.max(ret, n[i]);
		}
		return ret;
	}

	static long min(long... n) {
		long ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.min(ret, n[i]);
		}
		return ret;
	}

	static int min(int... n) {
		int ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.min(ret, n[i]);
		}
		return ret;
	}

	// 分割数：j を i に分割するときの組み合わせの数
	int[][] partition(int sum, int part, final int mod) {
		int[][] dp = new int[part + 1][sum + 1];
		dp[0][0] = 1;
		for (int i = 1; i <= part; i++) {
			for (int j = 0; j <= sum; j++) {
				dp[i][j] = dp[i - 1][j] + (j < i ? 0 : dp[i][j - i]);
				dp[i][j] %= mod;
			}
		}
		return dp;
	}


}
