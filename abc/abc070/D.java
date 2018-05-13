package abc070;

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

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			// input
			int n = in.nextInt();
			List<Edge>[] list = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				list[i] = new ArrayList<>();
			}
			Node[] nodes = new Node[n];
			for (int i = 0; i < n-1; i++) {
				int a = in.nextInt()-1;
				int b = in.nextInt()-1;
				long c = in.nextLong();
				list[a].add(new Edge(b, c));
				list[b].add(new Edge(a, c));
			}

			int q = in.nextInt();
			int k = in.nextInt()-1;

			// solve
			for (int i = 0; i < n; i++) {
				nodes[i] = new Node(i, Long.MAX_VALUE);
			}

			PriorityQueue<Node> pq = new PriorityQueue<Node>(new MyComparator());

			nodes[k].cost = 0;
			pq.add(nodes[k]);

			while (!pq.isEmpty()) {
				Node node = pq.remove();

				for (int i = 0; i < list[node.nodeNum].size(); i++) {
					int toNodeNum = list[node.nodeNum].get(i).toNodeNum;
					long moveWeight = list[node.nodeNum].get(i).weight;
					if (node.cost + moveWeight < nodes[toNodeNum].cost) {
						nodes[toNodeNum].cost = node.cost + moveWeight;
						pq.add(nodes[toNodeNum]);
					}
				}
			}

			for (int i = 0; i < q; i++) {
				int x = in.nextInt()-1;
				int y = in.nextInt()-1;
				out.println(nodes[x].cost+nodes[y].cost);
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

			long cost1 = n1.cost;
			long cost2 = n2.cost;

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
		long cost;

		public Node(int nodeNum, long weight) {
			this.nodeNum = nodeNum;
			this.cost = weight;
		}
	}
	static class Edge {
		int toNodeNum;
		long weight;

		public Edge(int nodeNum, long weight) {
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
