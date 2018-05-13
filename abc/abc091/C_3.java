package abc091;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class C_3 {

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
			P[] ps = new P[2*n];
			for (int i = 0; i < n; i++) {
				ps[i] = new P(in.nextInt(), in.nextInt(), "red");
			}
			for (int i = n; i < 2*n; i++) {
				ps[i] = new P(in.nextInt(), in.nextInt(), "blue");
			}

			int count = 0;
			Arrays.sort(ps, new MyComparatorX());
			List<P> list = new ArrayList<>();
			for (int i = 0; i < 2*n; i++) {
				if ("red".equals(ps[i].color)) {
					list.add(ps[i]);
					Collections.sort(list, new MyComparatorY());
				} else if ("blue".equals(ps[i].color)) {
					for (int j = 0; j < list.size(); j++) {
						if (list.get(j).y < ps[i].y) {
							count++;
							list.remove(j);
							break;
						}
					}
				}
			}
			out.println(count);

		}
	}

	static class P {
		int x, y;
		String color;
		P(int x, int y, String color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}

	static class MyComparatorX implements Comparator<P>{
		public int compare(P p1, P p2) {
			return p1.x - p2.x;
		}
	}

	static class MyComparatorY implements Comparator<P>{
		public int compare(P p1, P p2) {
			return -(p1.y - p2.y);
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
