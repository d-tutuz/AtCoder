import java.util.Arrays;


public class LIS {

	private LIS() {
	}

	/**
	 * LISを求める。
	 * 計算量：O(NlogN)
	 * */
	int lis(int[] a) {
		int inf = 1 << 30;
		int n = a.length;
		int[] dp = new int[n];
		Arrays.fill(dp, inf);

		for (int i = 0; i < n; i++) {
			int idx = lowerBound(dp, a[i]);
			dp[idx] = a[i];
		}

		return lowerBound(dp, inf);
	}

	public static int lowerBound(int[] a, int obj) {
		int l = 0, r = a.length - 1;
		while (r - l >= 0) {
			int c = (l + r) / 2;
			if (obj <= a[c]) {
				r = c - 1;
			} else {
				l = c + 1;
			}
		}
		return l;
	}

}
