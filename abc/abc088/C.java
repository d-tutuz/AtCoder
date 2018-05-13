package abc088;

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

public class C {

	static int[][] c;

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

			c = new int[4][4];
			boolean flg = false;

			for (int i = 1; i < 4; i++) {
				for (int j = 1; j < 4; j++) {
					c[i][j] = in.nextInt();
				}
			}
			int[][] a = new int[101][3];
			int[][] b = new int[101][3];

			for (int x = 0; x <= 100; x++) {
				a[x][0] = x;
				a[x][1] = c[2][1] - (c[1][1] - x);
				a[x][2] = c[3][1] - (c[1][1] - x);
				b[x][0] = c[1][1] - x;
				b[x][1] = c[1][2] - x;
				b[x][2] = c[1][3] - x;

				if (judge(a[x][0], a[x][1], a[x][2], b[x][0], b[x][1], b[x][2])) {
					flg = true;
					break;
				}
			}

			String str = flg ? "Yes" : "No";
			out.println(str);

		}
		static boolean judge(int a1,int a2, int a3, int b1, int b2, int b3) {
			boolean isSame = true;
			if (a1 + b1 != c[1][1]) {
				isSame = false;
			}
			if (a1 + b2 != c[1][2]) {
				isSame = false;
			}
			if (a1 + b3 != c[1][3]) {
				isSame = false;
			}
			if (a2 + b1 != c[2][1]) {
				isSame = false;
			}
			if (a2 + b2 != c[2][2]) {
				isSame = false;
			}
			if (a2 + b3 != c[2][3]) {
				isSame = false;
			}
			if (a3 + b1 != c[3][1]) {
				isSame = false;
			}
			if (a3 + b2 != c[3][2]) {
				isSame = false;
			}
			if (a3 + b3 != c[3][3]) {
				isSame = false;
			}
			return isSame;
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
