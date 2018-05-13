package abc057;

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

public class B {

	public static void main(String[] args) {
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
			int[][] sp = new int[n][2];
			int[][] cp = new int[m][2];

			for (int i = 0; i < n; i++) {
				sp[i] = in.nextIntArray(2);
			}
			for (int i = 0; i < m; i++) {
				cp[i] = in.nextIntArray(2);
			}

			int[] ans = new int[n];
			for (int i = 0; i < n; i++) {
				int min = Integer.MAX_VALUE;
				for (int j = 0; j < m; j++) {
					int dis = MathUtils.calcDistance(sp[i][0], sp[i][1], cp[j][0], cp[j][1]);
					if (min > dis) {
						min = dis;
						ans[i] = j;
					}

				}
			}

			for (int i : ans) {
				out.println(i+1);
			}

		}

	}

	static class MathUtils {
		public static int calcDistance(int x1, int y1, int x2, int y2) {
			return Math.abs(x1-x2) + Math.abs(y1-y2);
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

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}

	}

}
