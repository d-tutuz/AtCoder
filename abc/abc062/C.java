package abc062;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class C {

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

			long h = in.nextLong();
			long w = in.nextLong();
			long[] sa1h = new long[(int) h];
			long[] sa2h = new long[(int) h];
			long[] sa1w = new long[(int) w];
			long[] sa2w = new long[(int) w];

			for (int i = 1; i <= h - 1; i++) {
				long[] s = new long[3];
				s[0] = i * w;
				s[1] = (h - i) * (w - (w / 2));
				s[2] = (h - i) * (w / 2);
				Arrays.sort(s);
				sa1h[i] = s[2] - s[0];
			}
			Arrays.sort(sa1h);
			long sa1hmin = sa1h[1];

			for (int i = 1; i <= h - 1; i++) {
				long[] s = new long[3];
				s[0] = i * w;
				s[1] = ((h - i) / 2) * w;
				s[2] = (h - ((h - i) / 2) - i) * w;
				Arrays.sort(s);
				sa2h[i] = s[2] - s[0];
			}
			Arrays.sort(sa2h);
			long sa2hmin = sa2h[1];

			for (int i = 1; i <= w - 1; i++) {
				long[] s = new long[3];
				s[0] = i * h;
				s[1] = (w - i) * (h - (h / 2));
				s[2] = (w - i) * (h / 2);
				Arrays.sort(s);
				sa1w[i] = s[2] - s[0];
			}
			Arrays.sort(sa1w);
			long sa1wmin = sa1w[1];

			for (int i = 1; i <= w - 1; i++) {
				long[] s = new long[3];
				s[0] = i * h;
				s[1] = ((w - i) / 2) * h;
				s[2] = (w - ((w - i) / 2) - i) * h;
				Arrays.sort(s);
				sa2w[i] = s[2] - s[0];
			}
			Arrays.sort(sa2w);
			long sa2wmin = sa2w[1];
			out.print(Math.min(Math.min(sa1hmin, sa2hmin),Math.min(sa1wmin, sa2wmin)));
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
