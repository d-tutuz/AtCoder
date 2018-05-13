package arc078;

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
import java.util.stream.Stream;

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

	static int INF = 1 << 30;
	static int modP = 1000000007;

	static List<Integer>[] G;
	static int FE = 1;
	static int NU = 2;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			G = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);
			for (int i = 0; i < n - 1; i++) {
				int a = in.nextInt() - 1;
				int b = in.nextInt() - 1;
				G[a].add(b);
				G[b].add(a);
			}
			int[] color = new int[n];
			Queue<Integer> q1 = new ArrayDeque<>();
			Queue<Integer> q2 = new ArrayDeque<>();
			q1.add(0);
			color[0] = FE;
			q2.add(n-1);
			color[n-1] = NU;

			while (!q1.isEmpty() || !q2.isEmpty()) {
				if (!q1.isEmpty()) {
					int now = q1.remove();
					for (int i = 0; i < G[now].size(); i++) {
						int to = G[now].get(i);
						if (color[to] == 0) {
							color[to] = FE;
							q1.add(to);
						}
					}
				}
				if (!q2.isEmpty()) {
					int now = q2.remove();
					for (int i = 0; i < G[now].size(); i++) {
						int to = G[now].get(i);
						if (color[to] == 0) {
							color[to] = NU;
							q2.add(to);
						}
					}
				}
			}

			int cf = 0, cn = 0;
			for (int i = 0; i < n; i++) {
				if (color[i] == NU) {
					cn++;
				} else if (color[i] == FE) {
					cf++;
				} else {
					new RuntimeException();
				}
			}
			out.println(cf > cn ? "Fennec" : "Snuke");

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
