

/**
 * セグメントツリーのノードにT型のデータを扱えるRMQ
 * 主に (idx, x) の形のデータを格納することを想定
 *
 * 使う場合は
 *
 * 		class RMQ extends AbstractSegmentTree<P> {
			public RMQ(int size, P initial_value) {
				super(size, initial_value);
			}

			@Override
			P merge(P x, P y) {
				return x.cost <= y.cost ? x : y;
			}
		}
 *
 * のように extends して利用する。
 * */

@SuppressWarnings("unchecked")
abstract class AbstractSegmentTree<T> {
	int size;
	T[] dat;
	T INITIAL_VALUE;

	abstract T merge(T x, T y);

	public AbstractSegmentTree(int n, T initial_value) {
		size = 1;
		this.INITIAL_VALUE = initial_value;
		while (size < n) {
			size *= 2;
		}
		dat = (T[])new Object[size * 2];
		for (int i = 0; i < size * 2; i++) {
			dat[i] = INITIAL_VALUE;
		}
	}

	// k 番目(0-indexed) を a に更新
	void update(int k, T a) {
		k += size;
		dat[k] = a;
		while (k > 0) {
			k /= 2;
			dat[k] = merge(dat[2 * k], dat[2 * k + 1]);
		}
	}

	// [a, b) の最小値を求める
	private T query(int a, int b, int k, int l, int r) {
		if (r <= a || b <= l) return INITIAL_VALUE;

		if (a <= l && r <= b) {
			return dat[k];
		} else {
			T vl = query(a, b, 2 * k, l, (l + r) / 2);
			T vr = query(a, b, 2 * k + 1, (l + r) / 2, r);
			return merge(vl, vr);
		}
	}

	// [a, b) の最小値を求める
	T query(int a, int b) {
		return query(a, b, 1, 0, size);
	}
}