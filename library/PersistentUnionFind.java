import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

class PersistentUnionFind {
	final int inf = 1 << 30;
	int size, now;
	int[] par, rank, time;
	ArrayList<P>[] num;

	public PersistentUnionFind(int size) {
		this.size = size;
		init();
		for (int i = 0; i < size; i++) {
			par[i] = i;
		}
		Arrays.fill(time, inf);
		for (int i = 0; i < size; i++) {
			num[i].add(new P(0, 1));
		}
	}

	@SuppressWarnings("unchecked")
	void init() {
		par = new int[this.size];
		rank = new int[this.size];
		time = new int[this.size];
		num = new ArrayList[this.size];
		num = Stream.generate(ArrayList::new).limit(this.size).toArray(ArrayList[]::new);
	}

	// 時刻 t における x の親を返す
	int find(int x, int t) {
		return time[x] > t ? x : find(par[x], t);
	}

	// 時刻 t における x を含むグループの要素数を返す
	int size(int x, int t) {
		x = find(x, t);
		int ok = 0, ng = num[x].size();

		while (ng - ok > 1) {
			int mid = (ok + ng) / 2;
			if (num[x].get(mid).t <= t) {
				ok = mid;
			} else {
				ng = mid;
			}
		}
		return num[x].get(ok).cnt;
	}

	// x と y を含むグループを統合する
	void unite(int x, int y) {
		now++;
		x = find(x, now);
		y = find(y, now);

		if (x == y) return;

		if (rank[x] < rank[y]) {
			int t = x;
			x = y;
			y = t;
		}

		num[x].add(new P(now, size(x, now) + size(y, now)));

		par[y] = x;
		time[y] = now;
		if (rank[x] == rank[y]) ++rank[x];
	}

	// x と y が時刻 t において同じグループに属するか判定
	boolean same(int x, int y, int t) {
		return find(x, t) == find(y, t);
	}

	private class P {
		int t, cnt;

		public P(int t, int cnt) {
			super();
			this.t = t;
			this.cnt = cnt;
		}
	}
}
