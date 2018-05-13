package kuramoti2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class C {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static int INF = 1 << 30;
	static int modP = 1000000007;

	@SuppressWarnings("unchecked")
	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			// input
			int n = in.nextInt(), m = in.nextInt();
			List<Edge>[] list = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				list[i] = new ArrayList<>();
			}
			Node[] nodes = new Node[n];
			for (int i = 0; i < m; i++) {
				int u = in.nextInt()-1;
				int k = in.nextInt()-1;
				for (int j = 0; j < k; j++) {
					list[u].add(new Edge(k, 1));
					list[k].add(new Edge(u, 1));
				}
			}

			// solve
			for (int i = 0; i < n; i++) {
				nodes[i] = new Node(i, Integer.MAX_VALUE);
			}

			PriorityQueue<Node> pq = new PriorityQueue<Node>(new MyComparator());

			nodes[0].cost = 0;
			pq.add(nodes[0]);

			while (!pq.isEmpty()) {
				Node node = pq.remove();

				for (int i = 0; i < list[node.nodeNum].size(); i++) {
					int toNodeNum = list[node.nodeNum].get(i).toNodeNum;
					int moveWeight = list[node.nodeNum].get(i).weight;
					if (node.cost + moveWeight < nodes[toNodeNum].cost) {
						nodes[toNodeNum].cost = node.cost + moveWeight;
						pq.add(nodes[toNodeNum]);
					}
				}
			}
			if (nodes[n-1].cost <= 2) {
				out.println("POSSIBLE");
			} else {
				out.println("IMPOSSIBLE");
			}
		}
	}

	@SuppressWarnings("rawtypes")
	/**
	 * 昇順ソート
	 * */
	static class MyComparator implements Comparator {

		public int compare(Object o1, Object o2) {

			Node n1 = (Node) o1;
			Node n2 = (Node) o2;

			int cost1 = n1.cost;
			int cost2 = n2.cost;

			if (cost1 > cost2) {
				return 1;
			} else if (cost1 < cost2) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	static class Node {
		int nodeNum;
		int cost;

		public Node(int nodeNum, int weight) {
			this.nodeNum = nodeNum;
			this.cost = weight;
		}
	}
	static class Edge {
		int toNodeNum;
		int weight;

		public Edge(int nodeNum, int weight) {
			this.toNodeNum = nodeNum;
			this.weight = weight;
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

		public double nextDouble() {
			return Double.parseDouble(nextString());
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
