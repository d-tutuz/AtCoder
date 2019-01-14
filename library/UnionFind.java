import java.util.Arrays;

/**
 * UnionFind
 * 集合サイズを使ったUnionFind
 * */
class UnionFind {
	int[] data;

	public UnionFind(int size) {
		data = new int[size];
		clear();
	}

	public void clear() {
		Arrays.fill(data, -1);
	}

	// data[x] < 0 の場合は x 自身が根になっている
	public int root(int x) {
		return data[x] < 0 ? x : (data[x] = root(data[x]));
	}

	public void union(int x, int y) {
		x = root(x);
		y = root(y);

		if (x != y) {
			if (data[y] > data[x]) {
				final int t = x;
				x = y;
				y = t;
			}

			// 負数の合計が連結成分の要素数
			data[x] += data[y];
			data[y] = x;
		}
	}

	boolean same(int x, int y) {
		return root(x) == root(y);
	}

	public int size(int x) {
		return -data[root(x)];
	}
}