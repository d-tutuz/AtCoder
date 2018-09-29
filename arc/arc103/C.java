package arc103;

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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			Map<Integer, Integer> m1 = new HashMap<>();
			Map<Integer, Integer> m2 = new HashMap<>();

			int n = in.nextInt();
			for (int i = 0; i < n; i++) {
				int a = in.nextInt();
				if (i % 2 == 0) {
					m1.merge(a, 1, Integer::sum);
				} else {
					m2.merge(a, 1, Integer::sum);
				}
			}

			int m1_max = 0;
			int m2_max = 0;

			int m1_key = 0;
			int m2_key = 0;

			for (int i : m1.keySet()) {
				if (m1_max < m1.get(i)) {
					m1_max = m1.get(i);
					m1_key = i;
				}
			}

			for (int i : m2.keySet()) {
				if (m2_max < m2.get(i)) {
					m2_max = m2.get(i);
					m2_key = i;
				}
			}

			List<Map.Entry<Integer, Integer>> list1 = new ArrayList<Map.Entry<Integer, Integer>>(m1.entrySet());
			Collections.sort(list1, new Comparator<Map.Entry<Integer, Integer>>() {

						@Override
						public int compare(Entry<Integer, Integer> entry1, Entry<Integer, Integer> entry2) {
							return ((Integer) entry2.getValue()).compareTo((Integer) entry1.getValue());
						}
					});

			List<Map.Entry<Integer, Integer>> list2 = new ArrayList<Map.Entry<Integer, Integer>>(m2.entrySet());
			Collections.sort(list2, new Comparator<Map.Entry<Integer, Integer>>() {

						@Override
						public int compare(Entry<Integer, Integer> entry1, Entry<Integer, Integer> entry2) {
							return ((Integer) entry2.getValue()).compareTo((Integer) entry1.getValue());
						}
					});

			if (m1_key != m2_key) {
				out.println(n - m1_max - m2_max);
			} else {
				int max = 0;
				if (list1.size() >= 2 && list2.size() >= 2) {
					max = Math.max(list1.get(1).getValue()+m2_max, list2.get(1).getValue()+ m1_max);
				} else if (list1.size() >= 2) {
					max = Math.max(list1.get(1).getValue()+m2_max, max);
				} else if (list2.size() >= 2) {
					max = Math.max(list2.get(1).getValue()+ m1_max, max);
				} else {
					out.println(n/2);
					return;
				}
				out.println(n - max);
			}

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

}
