
/**
 * 追加クエリの直線の傾きが単調減少の場合
 * - insert (a, b): add y = ax + b
 * - query (x)    : min_i{a[i]x + b}
 * */
class ConvexHullTrick {
	long[] A, B;
	int len;

	public ConvexHullTrick(int n) {
		A = new long[n];
		B = new long[n];
	}

	private boolean check(long a, long b) {
		return (B[len - 2] - B[len - 1]) * (a - A[len - 1]) >= (B[len - 1] - b) * (A[len - 1] - A[len - 2]);
	}

	public void addLine(long a, long b) {
		while (len >= 2 && check(a, b)) {
			len--;
		}
		A[len] = a;
		B[len] = b;
		len++;
	}

	public long query(long x) {
		int l = -1, r = len - 1;
		while (r - l > 1) {
			int mid = (r + l) / 2;
			if (get(mid, x) >= get(mid + 1, x)) {
				l = mid;
			} else {
				r = mid;
			}
		}
		return get(r, x);
	}

	private long get(int k, long x) {
		return A[k] * x + B[k];
	}
}