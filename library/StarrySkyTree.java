import java.util.Arrays;

class StarrySkyTree {
	int n;
	long[] dat;
	long[] lazy;

	public StarrySkyTree(int n_) {
		int n = 1;
		while (n < n_) {
			n *= 2;
		}

		this.n = n;
		dat = new long[this.n * 2 - 1];
		lazy = new long[this.n * 2 - 1];
		for (int i = 0; i < this.n * 2 - 1; i++) {
			dat[i] = 0;
			lazy[i] = 0;
		}
	}

	public void update_node(int k) {
		dat[k] = dat[2 * k + 1] + dat[2 * k + 2];
	}

	// 区間 [a, b) の最大値を求める
	long max(int a, int b, int k, int l, int r) {
		if (b <= l || r <= a) {
			return 0;
		} else if (a <= l && r <= b) {
			return dat[k] + lazy[k];
		}

		long lch = max(a, b, k * 2 + 1, l, (l + r) / 2);
		long rch = max(a, b, k * 2 + 2, (l + r) / 2, r);

		return (Math.max(lch, rch) + lazy[k]);
	}

	// 区間 [a, b) に x を加算する
	void add(long x, int a, int b, int k, int l, int r) {
		if (b <= l || r <= a)
			return;

		if (a <= l && r <= b) {
			lazy[k] += x;
			while (k > 0) {
				k = (k - 1) / 2;
				dat[k] = Math.max(dat[k * 2 + 1] + lazy[k * 2 + 1],
						dat[k * 2 + 2] + lazy[k * 2 + 2]);
			}
		} else {
			add(x, a, b, k * 2 + 1, l, (l + r) / 2);
			add(x, a, b, k * 2 + 2, (l + r) / 2, r);
		}
	}

	public int size() {
		return this.n;
	}

	public String toString() {
		return Arrays.toString(dat) + "\n" + Arrays.toString(lazy);
	}
}
