package arc076;

import java.util.Arrays;
import java.util.Scanner;

public class D_packer_jp {

	int N;
	int par[], rank[];

	public static void main(String[] args) {
		new D_packer_jp().compute();
	}

	void init(int n) {
		for (int i = 0; i < n; i++) {
			par[i] = i;
			rank[i] = 0;
		}
	}

	int find(int x) {
		if (par[x] == x) {
			return x;
		} else {
			return par[x] = find(par[x]);
		}
	}

	void unite(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) {
			return;
		}
		if (rank[x] < rank[y]) {
			par[x] = y;
		} else {
			par[y] = x;
			if (rank[x] == rank[y]) {
				rank[x]++;
			}
		}
	}

	boolean same(int x, int y) {
		return find(x) == find(y);
	}

	void compute() {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		par = new int[N];
		rank = new int[N];
		int[] x = new int[N];
		int[] y = new int[N];
		Integer[] index = new Integer[N];
		for (int i = 0; i < N; i++) {
			x[i] = sc.nextInt();
			y[i] = sc.nextInt();
			index[i] = i;
		}
		int E = 2 * N - 2;
		Edge[] es = new Edge[E];
		Arrays.sort(index, (o1, o2) -> x[o1] - x[o2]);
		for (int i = 0; i < N - 1; i++) {
			es[i] = new Edge(index[i], index[i + 1], Math.min(
					Math.abs(x[index[i]] - x[index[i + 1]]),
					Math.abs(y[index[i]] - y[index[i + 1]])));
		}
		Arrays.sort(index, (o1, o2) -> y[o1] - y[o2]);
		for (int i = 0; i < N - 1; i++) {
			es[i + N - 1] = new Edge(index[i], index[i + 1], Math.min(
					Math.abs(x[index[i]] - x[index[i + 1]]),
					Math.abs(y[index[i]] - y[index[i + 1]])));
		}
		Arrays.sort(es, (o1, o2) -> o1.cost - o2.cost);
		init(N);
		int res = 0;
		for (int i = 0; i < E; i++) {
			Edge e = es[i];
			if (!same(e.u, e.v)) {
				unite(e.u, e.v);
				res += e.cost;
			}
		}
		System.out.println(res);

	}

	class Edge {

		int u, v, cost;

		Edge(int u, int v, int cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;
		}
	}
}
