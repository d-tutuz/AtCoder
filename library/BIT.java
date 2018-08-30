
	/**
	 * 1-indexed BinaryIndexTree
	 * */
	public class BIT {

		// [1, n]
		int n;
		long[] bit;

		public BIT(int n) {
			this.n = n;
			bit = new long[n+1];
		}

		// indexに値vを足す
		public void add(int i, long v) {
			for (int x = i; x < n+1; x += x & -x) {
				bit[x] += v;
			}
		}

		// 区間和 [1, v] を求める
		// v[1] + ... + v[i]
		public long sum(int i) {
			long ret = 0;
			for (int x = i; x > 0; x -= x & -x) {
				ret += bit[x];
			}
			return ret;
		}

		// 区間和 [i, j] = [1, j] - [1, i-1]を求める
		// v[i] + ... + v[j]
		public long query(int i, int j) {
			return sum(j) - sum(i-1);
		}
	}