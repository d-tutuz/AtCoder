package abc051;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
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

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			// input
			int n = in.nextInt();
			int myN = 101;
			List<Edge>[] list = new ArrayList[myN];
			int m = in.nextInt();
			for (int i = 0; i < myN; i++) {
				list[i] = new ArrayList<>();
			}
			int[][] edge = new int[myN][myN];
			Node[] nodes = new Node[myN];
			Set<Integer> s = new HashSet<>();
			for (int i = 0; i < m; i++) {
				int a = in.nextInt()-1;
				int b = in.nextInt()-1;
				int c = in.nextInt();
				list[a].add(new Edge(b, c));
				list[b].add(new Edge(a, c));
				edge[a][b] = c;
				s.add((a+1)*1000+(b+1));
			}

			// solve
			int[][] dist = new int[myN][myN];
			for (int l = 0; l < n; l++) {
				for (int i = 0; i < n; i++) {
					nodes[i] = new Node(i, Integer.MAX_VALUE);
				}

				PriorityQueue<Node> pq = new PriorityQueue<Node>(new MyComparator());

				nodes[l].cost = 0;
				pq.add(nodes[l]);

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

				for (int i = 0; i < n; i++) {
//					out.println((l+1) + " " + (i+1) + " " + nodes[i].cost);
					dist[l][i] = nodes[i].cost;
				}
			}

			for (int k = 0; k < n; k++) {
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < n; j++) {
						if (dist[k][i] + edge[i][j] == dist[k][j]) {
							s.remove((i+1)*1000+(j+1));
						}
					}
				}
			}
			out.println(s.size());

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
