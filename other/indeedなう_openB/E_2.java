package indeedなう_openB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;

public class E_2 {

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

			Random r = new Random();
//			int n = r.nextInt(100)+1;
			int n = in.nextInt();
//			System.out.println(n);

			int[] h = new int[n];
			Set<Integer> set = new HashSet<>();
			Map<Integer, Integer> map = new HashMap<>();
			for (int i = n-1; i >= 0; i--) {
//				int t = r.nextInt(100000000)+1;
//				if (set.contains(t)) {
//					i++;
//					continue;
//				}
//				set.add(t);
//				h[i] = t;
				h[i] = in.nextInt();
				map.merge(h[i], 1, Integer::sum);
			}

//			printArray(h);

			for (int v : map.values()) {
				if (v >= 2) {
					out.println(-1);
					return;
				}
			}

			BIT bit = new BIT((int)1e8);
			long ans = 0;
			for (int i = 0; i < n; i++) {
				ans += bit.sum(h[i]);
				bit.add(h[i], h[i]);
			}

			out.println(ans);

		}

	}

	static void printArray(int[] a) {
		for (int i : a) {
			System.out.printf("%d ", i);
		}
		System.out.print("\n");
	}

	/**
	 * 1-indexed BinaryIndexTree
	 * */
	public static class BIT {

		// [1, n]
		int n;
		long[] bit;

		public BIT(int n) {
			this.n = n;
			bit = new long[n + 1];
		}

		// indexに値vを足す
		public void add(int i, int v) {
			for (int x = i; x < n + 1; x += x & -x) {
				bit[x] += v;
			}
		}

		// 区間和 [1, v] を求める
		// v[1] + ... + v[i]
		public long sum(int i) {
			long ret = 0;
			for (int x = i; x > 0; x -= x & -x) {
				ret += bit[x];
			}
			return ret;
		}

		// 区間和 [i, j] = [1, j] - [1, i-1]を求める
		// v[i] + ... + v[j]
		public long query(int i, int j) {
			return sum(j) - sum(i - 1);
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

