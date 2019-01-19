

class RangeMinimumQuery {
	final int inf = (1 << 31) - 1;

	int size;
	int[] dat;

	// 初期化
	public RangeMinimumQuery(int n) {
		size = 1;
		while (size < n) {
			size *= 2;
		}
		dat = new int[size * 2];
		for (int i = 0; i < size * 2; i++) {
			dat[i] = inf;
		}
	}

	// k 番目(0-indexed) を a に更新
	void update(int k, int a) {
		k += size;
		dat[k] = a;
		while (k > 0) {
			k /= 2;
			dat[k] = Math.min(dat[2 * k], dat[2 * k + 1]);
		}
	}

	// [a, b) の最小値を求める
	private int query(int a, int b, int k, int l, int r) {
		if (r <= a || b <= l) return inf;

		if (a <= l && r <= b) {
			return dat[k];
		} else {
			int vl = query(a, b, 2 * k, l, (l + r) / 2);
			int vr = query(a, b, 2 * k + 1, (l + r) / 2, r);
			return Math.min(vl, vr);
		}
	}

	int query(int a, int b) {
		return query(a, b, 1, 0, size);
	}
}
