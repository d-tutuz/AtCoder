package indeedなう_openB;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Test {

	public static void main(String[] args) {

		Random r = new Random();
		int n = 10000;
		System.out.println(n);

		int[] h = new int[n];
		Set<Integer> set = new HashSet<>();
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = n-1; i >= 0; i--) {
			int t = r.nextInt(10000000)+1;
			if (set.contains(t)) {
				i++;
				continue;
			}
			set.add(t);
			h[i] = t;
			map.merge(h[i], 1, Integer::sum);
		}

		printArray(h);

		long ans = 0;
		for (int i = 0; i < n-1; i++) {
			for (int j = n-1; j > i; j--) {
				if (h[j-1] > h[j]) {
					ans += h[j];
					int tmp = h[j];
					h[j] = h[j-1];
					h[j-1] = tmp;
				}
			}
		}

		System.out.println(ans);
	}

	static void printArray(int[] a) {
		for (int i : a) {
			System.out.printf("%d ", i);
		}
		System.out.print("\n");
	}

}
