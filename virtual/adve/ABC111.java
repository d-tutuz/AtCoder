package adve;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

public class ABC111 {

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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int[] v = in.nextIntArray(n);

			Map<Integer, Integer> odd = new HashMap<>();
			Map<Integer, Integer> even = new HashMap<>();

			for (int i = 0; i < n; i++) {
				if (i % 2 == 0) {
					even.merge(v[i], 1, Integer::sum);
				} else {
					odd.merge(v[i], 1, Integer::sum);
				}
			}

			odd.put(INF, 0);
			even.put(INF, 0);

			List<Entry<Integer, Integer>> oList = getSortMapList(odd);
			List<Entry<Integer, Integer>> eList = getSortMapList(even);

			int ans = INF;
			if (oList.get(0).getKey().equals(eList.get(0).getKey())) {
				ans = min(ans, n/2 - eList.get(1).getValue() + n/2 - oList.get(0).getValue());
				ans = min(ans, n/2 - oList.get(1).getValue() + n/2 - eList.get(0).getValue());
			} else {
				ans = min(ans, n/2 - eList.get(0).getValue() + n/2 - oList.get(0).getValue());
			}

			out.println(ans);
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

		public int[] nextIntArrayDec(int n) {
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt() - 1;
			}
			return res;
		}

		public int[] nextIntArray1Index(int n) {
			int[] res = new int[n + 1];
			for (int i = 0; i < n; i++) {
				res[i + 1] = nextInt();
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

		public long[] nextLongArrayDec(int n) {
			long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong() - 1;
			}
			return res;
		}

		public long[] nextLongArray1Index(int n) {
			long[] res = new long[n + 1];
			for (int i = 0; i < n; i++) {
				res[i + 1] = nextLong();
			}
			return res;
		}

		public double[] nextDoubleArray(int n) {
			double[] res = new double[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextDouble();
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

	static <T> List<Map.Entry<T, Integer>> getSortMapList(Map<T, Integer> map) {
		List<Map.Entry<T, Integer>> list = new ArrayList<Map.Entry<T, Integer>>(
				map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<T, Integer>>() {

			@Override
			public int compare(Entry<T, Integer> entry1,
					Entry<T, Integer> entry2) {
				return ((Integer) entry2.getValue()).compareTo((Integer) entry1
						.getValue());
			}
		});
		return list;
	}

}
