package abc089;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
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
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				char str = in.nextString().toCharArray()[0];
				if (str == 'M' || str == 'A' || str == 'R' || str == 'C' || str == 'H') {
					list.add((int)str);
				}
			}
			Integer[] s = list.toArray(new Integer[list.size()]);
			Map<Integer, Integer> map = ArrayUtils.getCountMap(s);

			long ans = 0;
			long count77 = 0;
			long count65 = 0;
			long count82 = 0;
			long count67 = 0;
			long count72 = 0;
			if (map.get(77) != null) count77 = map.get(77);
			if (map.get(65) != null) count65 = map.get(65);
			if (map.get(82) != null) count82 = map.get(82);
			if (map.get(67) != null) count67 = map.get(67);
			if (map.get(72) != null) count72 = map.get(72);

			ans += (count77*count67*count72);
			ans += (count77*count65*count82);
			ans += (count77*count65*count67);
			ans += (count77*count65*count72);
			ans += (count77*count82*count67);
			ans += (count77*count82*count72);

			ans += (count65*count82*count67);
			ans += (count65*count82*count72);
			ans += (count65*count67*count72);

			ans += (count82*count67*count72);

			System.out.println(ans);

		}
	}

	static class ArrayUtils {
		public static Map<Integer, Integer> getCountMap(Integer[] s) {
			Map<Integer, Integer> map = new TreeMap<>();
			for (int x : s)
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
