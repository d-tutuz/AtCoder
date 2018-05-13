package abc077;

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

public class C_4 {

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

			int n = in.nextInt();
			int[] a = in.nextIntArray(n);
			Arrays.sort(a);
			int[] b = in.nextIntArray(n);
			int[] c = in.nextIntArray(n);
			Arrays.sort(c);

			long count = 0L;
			for (int i = 0; i < n; i++) {
				long ubc = ArrayUtils.lowerBound(a, b[i]);
				long obc = n - (ArrayUtils.upperBound(c, b[i]));
				count += (ubc * obc);
			}
			out.println(count);

		}

	}

	static class ArrayUtils {
		public static Map<Integer, Integer> getCountMap(int[] array) {
			Map<Integer, Integer> map = new TreeMap<>();
			for (int x : array)
				map.merge(x, 1, Integer::sum);
			return map;
		}
		/**
		 * 指定された要素以上の値が現れる最初の位置のイテレータを取得する
		 * */
		public static int lowerBound(int[] a, int obj) {
			int l = 0,r = a.length - 1;
			while (r - l >= 0) {
				int c = (l + r) / 2;
				if (obj <= a[c]) {
					r = c - 1;
				} else {
					l = c + 1;
				}
			}
			return l;
		}

		/**
		 * 指定された要素より大きい値が現れる最初の位置のイテレータを取得する
		 * */
		public static int upperBound(int[] a, int obj) {
			int l = 0,r = a.length - 1;
			while (r - l >= 0) {
				int c = (l + r) / 2;
				if (a[c] <= obj) {
					l = c + 1;
				} else {
					r = c - 1;
				}
			}
			return l;
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
