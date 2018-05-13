package abc091;

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
			String[] blue = new String[n];
			for (int i = 0; i < n; i++) {
				blue[i] = in.nextString();
			}
			int k = in.nextInt();
			String[] red = new String[k];
			for (int i = 0; i < k; i++) {
				red[i] = in.nextString();
			}

			Map<String, Integer> mapB = ArrayUtils.getCountMap(blue);
			Map<String, Integer> mapA = ArrayUtils.getCountMap(red);

			int ans = 0;
			for (Map.Entry<String, Integer> entry : mapB.entrySet()) {
				int b = entry.getValue();
				int r = 0;
				if (mapA.get(entry.getKey()) != null) {
					r = mapA.get(entry.getKey());
				}
				ans = Math.max(ans, b-r);
			}
			out.println(ans);
		}
	}

	static class ArrayUtils {
		public static Map<String, Integer> getCountMap(String[] array) {
			Map<String, Integer> map = new TreeMap<>();
			for (String x : array)
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
