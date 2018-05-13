package abc071;

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
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * Built using CHelper plug-in Actual solution is at the top
 */
public class C_CrazyBBB {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskC solver = new TaskC();
		solver.solve(1, in, out);
		out.close();
	}

	static class TaskC {
		public void solve(int testNumber, InputReader in, PrintWriter out) {
			int n = in.nextInt();
			int[] a = in.nextIntArray(n);

			Map<Integer, Integer> countMap = ArrayUtils.getCountMap(a);
			List<Integer> list = new ArrayList<>();
			Set<Integer> set = countMap.keySet();
			for (int x : set) {
				int cnt = countMap.get(x);
				if (cnt >= 4) {
					list.add(x);
					list.add(x);
				} else if (cnt >= 2) {
					list.add(x);
				}
			}
			Collections.sort(list, Collections.reverseOrder());

			if (list.size() < 2) {
				out.println(0);
			} else {
				out.println((long) list.get(0) * list.get(1));
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
