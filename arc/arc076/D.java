package arc076;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class D {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		inputStream = new FileInputStream("/workspace/Atcoder/arc/arc076/D_sample1.txt");

		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			Node[] nodes = new Node[n];
			for (int i = 0; i < n; i++) {
				int x = in.nextInt();
				int y = in.nextInt();
				nodes[i] = new Node(x, y);
			}
			Set<Node> mst = new HashSet<>();
			mst.add(nodes[0]);
			Set<Node> tar = new HashSet<>();
			for (int i = 1; i < n; i++) {
				tar.add(nodes[i]);
			}

			// solve
			long cost = 0;
			while (!tar.isEmpty()) {
				int min = Integer.MAX_VALUE;
				int visitCost = 0;
				Node toNextNode = null;
				for (Node fromNode : mst) {
					for (Node toNode : tar) {
						int dist = getDist(fromNode, toNode);
						if (dist <= min) {
							min = dist;
							toNextNode = toNode;
							visitCost = dist;
						}
					}
				}
				cost += visitCost;
				mst.add(toNextNode);
				tar.remove(toNextNode);
			}
			out.println(cost);
		}
	}

	static class Node {
		int x;
		int y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int getDist(Node n1, Node n2) {
		return Math.min(Math.abs(n1.x - n2.x), Math.abs(n1.y - n2.y));
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
