package arc074;

import java.io.BufferedReader;
import java.io.FileInputStream;
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

public class D {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		inputStream = new FileInputStream("/workspace/Atcoder/arc/arc074/D_sample1.txt");

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
			int[] a = in.nextIntArray(3 * n);

			long ans = 0;
			if (n % 2 == 0) {
				int l = 3 * n / 2;
				int[] b = new int[l];
				int[] c = new int[l];
				for (int i = 0; i < l; i++) {
					b[i] = a[i];
					c[i] = a[i+l];
				}
				Arrays.sort(b);
				Arrays.sort(c);

				long sumB = 0;
				long sumC = 0;

				for (int i = l - 1; i >= l - n; i--) {
					sumB += b[i];
				}
				for (int i = 0; i < n; i++) {
					sumC += c[i];
				}
				ans = sumB - sumC;

			} else {
				for (int k = 0; k <= 1; k++) {
					int l = 3 * n / 2 + k;
					int[] b = new int[l];
					int[] c = new int[3*n-l];
					for (int i = 0; i < l; i++) {
						b[i] = a[i];
					}
					for (int i = 0; i < 3*n-l; i++) {
						c[i] = a[l+i];
					}
					Arrays.sort(b);
					Arrays.sort(c);

					long sumB = 0;
					long sumC = 0;

					for (int i = l - 1; i >= l - n; i--) {
						sumB += b[i];
					}
					for (int i = 0; i < n; i++) {
						sumC += c[i];
					}

					ans = Math.max(ans, sumB - sumC);
				}

			}

			out.println(ans);

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
