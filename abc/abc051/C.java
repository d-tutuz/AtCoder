package abc051;

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

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static String U = "U";
	static String D = "D";
	static String L = "L";
	static String R = "R";

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int sx = in.nextInt();
			int sy = in.nextInt();
			int tx = in.nextInt();
			int ty = in.nextInt();
			int dw = tx - sx;
			int dh = ty - sy;

			// solve
			StringBuffer sb = new StringBuffer();
			sb.append(L);
			for (int i = 0; i < dh+1; i++) {
				sb.append(U);
			}
			for (int i = 0; i < dw+1; i++) {
				sb.append(R);
			}
			for (int i = 0; i < dh+1; i++) {
				sb.append(D);
			}
			for (int i = 0; i < dw; i++) {
				sb.append(L);
			}
			for (int i = 0; i < dh; i++) {
				sb.append(U);
			}
			for (int i = 0; i < dw+1; i++) {
				sb.append(R);
			}
			for (int i = 0; i < dh+1; i++) {
				sb.append(D);
			}
			for (int i = 0; i < dw+1; i++) {
				sb.append(L);
			}
			sb.append(U);

			out.println(sb.toString());

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
