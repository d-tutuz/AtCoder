package abc051;

import java.io.BufferedReader;
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

public class D_2 {

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

			int n = in.nextInt();
			int m = in.nextInt();
			int[][] d = new int[n][n];
			int[] a = new int[m];
			int[] b = new int[m];
			int[] c = new int[m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (i == j) {
						d[i][j] = 0;
					} else {
						d[i][j] = Integer.MAX_VALUE;
					}
				}
			}
			for (int i = 0; i < m; i++) {
				int ta = in.nextInt() - 1;
				int tb = in.nextInt() - 1;
				int tc = in.nextInt();
				a[i] = ta;
				b[i] = tb;
				c[i] = tc;
				d[ta][tb] = tc;
				d[tb][ta] = tc;
			}
			Set<Integer> set = new HashSet<>();
			for (int i = 0; i < m; i++) {
				set.add((a[i]+1)*1000+b[i]+1);
			}

			warshallFloyd(d);

			for (int s = 0; s < n; s++) {
				for (int i = 0; i < m; i++) {
					if (d[s][a[i]] + c[i] == d[s][b[i]]) {
						set.remove((a[i]+1)*1000+b[i]+1);
					}
				}
			}

			out.println(set.size());

		}

		void warshallFloyd(int[][] d) {
			int INF = Integer.MAX_VALUE;
			int len = d.length;
			for (int i = 0; i < len; i++) {
				for (int j = 0; j < len; j++) {
					if (d[j][i] == INF)
						continue;
					for (int k = 0; k < len; k++) {
						if (d[i][k] == INF)
							continue;
						d[j][k] = Math.min(d[j][k], d[j][i] + d[i][k]);
					}
				}
			}
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
