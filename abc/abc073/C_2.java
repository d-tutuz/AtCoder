package abc073;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class C_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}

		Map<Integer, Integer> countMap = ArrayUtils.getCountMap(a);
		List<Integer> list = new ArrayList<>();
		Set<Integer> set = countMap.keySet();
		int ans = 0;
		for (int x : set) {
			int cnt = countMap.get(x);
			if (cnt % 2 != 0) {
				ans++;
			}
		}
		System.out.println(ans);
	}

	static class ArrayUtils {
		public static Map<Integer, Integer> getCountMap(int[] array) {
			Map<Integer, Integer> map = new TreeMap<>();
			for (int x : array)
				map.merge(x, 1, Integer::sum);
			return map;
		}

	}
}

