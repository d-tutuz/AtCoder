package 第3回ドワンゴからの挑戦状予選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
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

			int n = in.nextInt();
			int[] a = in.nextIntArray(n);

			Map<Integer, Integer> map = new HashMap<>();
			for (int l : a) {
				map.merge(l, 1, Integer::sum);
			}

			int ans = 0;
			for (int i = 0; i < n; i++) {
				int cur = a[i];
				if (map.get(cur) == 0) {
					continue;
				}
				map.merge(cur, -1, Integer::sum);

				if (cur == 4) {
					ans++;
					continue;
				}

				if (cur == 3) {
					if (map.containsKey(1) && map.get(1) > 0) {
						map.merge(1,  -1, Integer::sum);
					}
					ans++;
					continue;
				}

				if (cur == 2) {
					int sum = cur;

					while (sum + 2 <= 4 && (map.containsKey(2) && map.get(2) > 0)) {
						map.merge(2,  -1, Integer::sum);
						sum += 2;
					}

					while (sum + 1 <= 4 && (map.containsKey(1) && map.get(1) > 0)) {
						map.merge(1,  -1, Integer::sum);
						sum += 1;
					}

					ans++;
					continue;
				}

				if (cur == 1) {
					int sum = cur;

					while (sum + 3 <= 4 && (map.containsKey(3) && map.get(3) > 0)) {
						map.merge(3,  -1, Integer::sum);
						sum += 3;
					}

					while (sum + 2 <= 4 && (map.containsKey(2) && map.get(2) > 0)) {
						map.merge(2,  -1, Integer::sum);
						sum += 2;
					}

					while (sum + 1 <= 4 && (map.containsKey(1) && map.get(1) > 0)) {
						map.merge(1,  -1, Integer::sum);
						sum += 1;
					}
					ans++;
					continue;
				}
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

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
