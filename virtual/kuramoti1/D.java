package kuramoti1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class D {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static int maxN = 1000;
	static int maxM = 2000;
	static int n;
	static int m;
	static long INF = Long.MAX_VALUE/10;
	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			// input
			n = in.nextInt();
			m = in.nextInt();

			Node[] nodes = new Node[n];
			Edge[] edges = new Edge[m];
			for (int i = 0; i < m; i++) {
				int a = in.nextInt()-1;
				int b = in.nextInt()-1;
				long c = in.nextLong();
				edges[i] = new Edge(a, b, c);
			}

			for (int i = 0; i < n; i++) {
				nodes[i] = new Node(i, INF);
			}
			nodes[0].cost = 0;

			boolean isNegative = false;

			bellmanFord(edges, nodes, 0);

			isNegative = findNegativeCycleByBellmanFord(edges, nodes, 0);

			if (isNegative) {
				out.println("inf");
			} else {
				out.println(nodes[n-1].cost);
			}
		}
	}



	static class Node {
		int nodeNum;
		long cost;

		public Node(int nodeNum, long cost) {
			this.nodeNum = nodeNum;
			this.cost = cost;
		}
	}
	static class Edge {
		int fromNodeNum;
		int toNodeNum;
		long weight;

		public Edge(int fromNodeNum, int toNodeNum, long weight) {
			this.fromNodeNum = fromNodeNum;
			this.toNodeNum = toNodeNum;
			this.weight = weight;
		}
	}

	/**
	 * ベルマンフォード法
	 * 負のコストを持つ辺がある場合の最短経路探索
	 *
	 * @input Edge[]
	 * @input Node[]
	 * @input fromNode[]
	 *
	 * */
	static void bellmanFord(Edge[] edges, Node[] nodes, int fromNode) {

		int n = nodes.length;
		int m = edges.length;
		long INF = Long.MAX_VALUE/10;

		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i, INF);
		}
		nodes[fromNode].cost = 0;

		for (int loop = 0; loop < n - 1; loop++) {
			for (int i = 0; i < m; i++) {
				if (nodes[edges[i].fromNodeNum].cost == INF) {
					continue;
				}
				if (nodes[edges[i].toNodeNum].cost > nodes[edges[i].fromNodeNum].cost + edges[i].weight) {
					nodes[edges[i].toNodeNum].cost = nodes[edges[i].fromNodeNum].cost + edges[i].weight;
				}
			}
		}
	}

	/**
	 * 負閉路を検知
	 * */
	static boolean findNegativeCycleByBellmanFord(Edge[] edges, Node[] nodes, int fromNode) {

		int n = nodes.length;
		int m = edges.length;
		long INF = Long.MAX_VALUE/10;

		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i, INF);
		}
		nodes[fromNode].cost = 0;

		boolean isNegative = false;
		for (int loop = 0; loop < n; loop++) {
			for (int i = 0; i < m; i++) {
				if (nodes[edges[i].fromNodeNum].cost == INF) {
					continue;
				}
				if (nodes[edges[i].toNodeNum].cost > nodes[edges[i].fromNodeNum].cost + edges[i].weight) {
					nodes[edges[i].toNodeNum].cost = nodes[edges[i].fromNodeNum].cost + edges[i].weight;
					if (loop == n - 1) isNegative = true;
				}
			}
		}

		return isNegative;

	}

	static class ArrayUtils {
		public static Map<Integer, Integer> getCountMap(int[] array) {
			Map<Integer, Integer> map = new TreeMap<>();
			for (int x : array)
				map.merge(x, 1, Integer::sum);
			return map;
		}
	}

	static class InputReader {
		BufferedReader in;
		StringTokenizer tok;

		public String nextString() {
			while (!tok.hasMoreTokens()) {
				try {
					tok = new StringTokenizer(in.readLine(), " ");
				} catch (IOException e) {
					throw new InputMismatchException();
				}
			}
			return tok.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(nextString());
		}

		public long nextLong() {
			return Long.parseLong(nextString());
		}

		public int[] nextIntArray(int n) {
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt();
			}
			return res;
		}

		public long[] nextLongArray(int n) {
			long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong();
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}

	}

}