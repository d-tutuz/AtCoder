package abc015;

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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static int n, k;
	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			n = in.nextInt(); k = in.nextInt();
			int[][] mat = new int[n][k];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < k; j++) {
					mat[i][j] = in.nextInt();
				}
			}

			Queue<List<Integer>> q = new ArrayDeque<>();
			for (int i = 0; i < k; i++) {
				List<Integer> list = new ArrayList<>();
				list.add(mat[0][i]);
				q.add(list);
			}

			while (!q.isEmpty()) {

				int now = q.peek().size();
				if (now >= n) {
					break;
				}
				List<Integer> list = q.remove();

				for (int i = 0; i < k; i++) {
					List<Integer> nlist = new ArrayList<>();
					for (int j = 0; j < list.size(); j++) {
						nlist.add(list.get(j));
					}
					nlist.add(mat[now][i]);
					q.add(nlist);
				}
			}

			for (List<Integer> list : q) {
				int s = list.get(0);
				for (int i = 1; i < list.size(); i++) {
					s ^= list.get(i);
				}
				if (s == 0) {
					out.println("Found");
					return;
				}
			}

			out.println("Nothing");

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
