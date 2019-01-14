package array;

import java.util.Arrays;


public class ArrayUtils {

	/**
	 * 指定された要素以上の値が現れる最初の位置のイテレータを取得する
	 * */
	public static int lowerBound(int[] a, int obj) {
		int l = 0,r = a.length - 1;
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

	/**
	 * 指定された要素より大きい値が現れる最初の位置のイテレータを取得する
	 * */
	public static int upperBound(int[] a, int obj) {
		int l = 0,r = a.length - 1;
		while (r - l >= 0) {
			int c = (l + r) / 2;
			if (a[c] <= obj) {
				l = c + 1;
			} else {
				r = c - 1;
			}
		}
		return l;
	}

	/**
	 * 逆順ソート
	 * */
	void revSort(int[] a) {
		int n = a.length;
		int[] tmp = a.clone();
		Arrays.sort(tmp);
		for (int i = 0; i < n; i++) {
			a[i] = tmp[n-1-i];
		}
	}

	void revSort(long[] a) {
		int n = a.length;
		long[] tmp = a.clone();
		Arrays.sort(tmp);
		for (int i = 0; i < n; i++) {
			a[i] = tmp[n-1-i];
		}
	}


}
