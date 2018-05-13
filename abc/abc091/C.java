package abc091;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
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

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			List<Integer> x1 = new ArrayList<>();
			List<Integer> y1 = new ArrayList<>();
			List<Integer> x2 = new ArrayList<>();
			List<Integer> y2 = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				x1.add(in.nextInt());
				y1.add(in.nextInt());
			}
			for (int i = 0; i < n; i++) {
				x2.add(in.nextInt());
				y2.add(in.nextInt());
			}

			Collections.sort(x1);
			Collections.sort(y1);
			Collections.sort(x2);
			Collections.sort(y2);

			int count1 = 0;
			int count2 = 0;

			for (int i = x1.size() - 1; i >= 0; i--) {
				for (int j = x2.size()-1; j >= 0; j--) {
					if (x1.get(i) < x2.get(j)) {
						count1++;
						x2.remove(j);
						break;
					}
				}
			}

			for (int i = y1.size() - 1; i >= 0; i--) {
				for (int j = y2.size()-1; j >= 0; j--) {
					if (y1.get(i) < y2.get(j)) {
						count2++;
						y2.remove(j);
						break;
					}
				}
			}

			out.println(Math.min(count1, count2));
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
