package math;

import java.util.Arrays;

public class MatrixUtils {

	// 行列A と 行列B の積を返す (mod)
	static long[][] mul(long[][] a, long[][] b , int mod) {

		int n = a.length, k = a[0].length, m = b[0].length;
		if (a[0].length != b.length) new RuntimeException("Input Matrix is invalid.");

		long[][] ret = new long[n][m];
		for (int i = 0; i < n; i++) {
			for (int t = 0; t < k; t++) {
				for (int j = 0; j < m; j++) {
					ret[i][j] += a[i][t] * b[t][j];
					ret[i][j] %= mod;
				}
			}
		}
		return ret;
	}

	// 正方行列A の e乗
	static long[][] pow(long[][] a, long e, int mod) {

		if (a.length != a[0].length) new RuntimeException("Matrix is not square.");
		int n = a.length;

		if (e == 0) {
			long[][] ret = new long[n][n];
			for (int i = 0; i < n; i++) Arrays.fill(ret[i], 1);
			return ret;
		} else if (e == 1) {
			return a;
		}

		if (e % 2 == 0) {
			long[][] ret = pow(a, e/2, mod);
			return mul(ret, ret, mod);
		} else {
			return mul(a, pow(a, e-1, mod), mod);
		}
	}
}
