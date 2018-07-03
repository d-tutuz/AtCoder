package abc102;


public class Main {

	public static void main(String[] args) {

		int[] a = {1, 4, 7};

		System.out.println(lowerBound(a, -1)); // 0
		System.out.println(lowerBound(a, 3)); // 1
		System.out.println(lowerBound(a, 4)); // 2 -> 1
		System.out.println(lowerBound(a, 8)); // 3

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
