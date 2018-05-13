package agc012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class B {

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

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			Node[] nodes = new Node[n];
			for (int i = 0; i < n; i++) {
				nodes[i] = new Node(i);
			}

			int m = in.nextInt();
			List<Integer>[] list = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				list[i] = new ArrayList<>();
			}
			for (int i = 0; i < m; i++) {
				int a = in.nextInt()-1;
				int b = in.nextInt()-1;
				list[a].add(b);
				list[b].add(a);
			}


			// solve
			int q = in.nextInt();
			int[][] info = new int[q][3];
			for (int i = 0; i < q; i++) {
				int v = in.nextInt()-1;
				int d = in.nextInt();
				int c = in.nextInt();

				info[i][0] = v;
				info[i][1] = d;
				info[i][2] = c;
			}

			Queue<Node> queue = new ArrayDeque<>();
			for (int i = q-1; i >= 0; i--) {

				int v = info[i][0];
				int d = info[i][1];
				int c = info[i][2];

				nodes[v].cost = 0;
				if (nodes[v].color == 0) {
					nodes[v].color = c;
				}
				queue.add(nodes[v]);

				while (!queue.isEmpty()) {

					Node fnode = queue.remove();

					for (int j = 0; j < list[fnode.num].size(); j++) {
						int tnode = list[fnode.num].get(j);
						int tcost = nodes[fnode.num].cost + 1;

						if (tcost <= d) {
							nodes[tnode].cost = tcost;
							if (nodes[tnode].color == 0) {
								nodes[tnode].color = c;
							}
							queue.add(nodes[tnode]);
						}
					}
				}
			}

			for (int i = 0; i < n; i++) {
				out.println(nodes[i].color);
			}

		}
	}

	static class Node {
		int num, cost, color;
		Node (int num) {
			this.num = num;
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
