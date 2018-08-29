
	public class BIT {

		// [1, n]
		int n;
		int[] bit;

		public BIT(int n) {
			this.n = n;
			bit = new int[n+1];
		}

		// indexに値vを足す
		public void add(int i, int v) {
			for (int x = i; x < n+1; x += x & -x) {
				bit[x] += v;
			}
		}

		// 区間和 [1, v] を求める
		// v[1] + ... + v[i]
		public int sum(int i) {
			int ret = 0;
			for (int x = i; x > 0; x -= x & -x) {
				ret += bit[x];
			}
			return ret;
		}

		// 区間和 [i, j] = [1, j] - [1, i-1]を求める
		// v[i] + ... + v[j]
		public int query(int i, int j) {
			return sum(j) - sum(i-1);
		}
	}