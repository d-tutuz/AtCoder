package array;

import java.util.Map;
import java.util.TreeMap;

public class ArrayUtils {

	/**
	 * 要素の個数を集計する
	 *
	 * @output map キー、個数が入ったmap
	 * */
	public static Map<Integer, Integer> getCountMap(int[] array) {
		Map<Integer, Integer> map = new TreeMap<>();
		for (int x : array)
			map.merge(x, 1, Integer::sum);
		return map;
	}

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


}
