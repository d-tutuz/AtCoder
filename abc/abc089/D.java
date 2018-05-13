package abc089;

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

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			// input
			int h = in.nextInt();
			int w = in.nextInt();
			int d = in.nextInt();

			Point[] p = new Point[90001];
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					int a = in.nextInt();
					p[a] = new Point(i, j);
				}
			}
			int q = in.nextInt();
			int[] l = new int[q];
			int[] r = new int[q];
			for (int i = 0; i < q; i++) {
				l[i] = in.nextInt();
				r[i] = in.nextInt();
			}

			// solve
			for (int i = 0; i < q; i++) {
				int ans = 0;
				int left = l[i];
				int right = r[i];
				int moveCost = 0;
				while (left < right) {
					int moveLeft = left + d;
					moveCost += move(
							p[left].x
							, p[left].y
							, p[moveLeft].x
							, p[moveLeft].y);

					left = moveLeft;
				}
				ans += moveCost;
				out.println(ans);
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

	static class Point {
		int x;
		int y;
		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static class Cost {
		int left;
		int right;
		Cost (int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
	static int move (int x, int y, int i, int j) {
		int abs = 0;
		abs = Math.abs(x-i) + Math.abs(y-j);
		return abs;
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
