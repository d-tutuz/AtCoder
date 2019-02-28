package 第5回ドワンゴからの挑戦状予選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class B_2 {

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

		@SuppressWarnings("unchecked")
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), k = in.nextInt();
			long[] a = in.nextLongArray(n);
			for (int i = 1; i < n; i++) {
				a[i] += a[i-1];
			}

			long max = 0;
			Set<Long> set = new HashSet<>();
			for (int i = 0; i < n; i++) {
				for (int j = i+1; j < n; j++) {
					max |= (a[j] - (i > 0 ? a[i-1] : 0));
					set.add(a[j] - (i > 0 ? a[i-1] : 0));
				}
			}

			List<Long>[] g = new ArrayList[64];
			g = Stream.generate(ArrayList::new).limit(64).toArray(List[]::new);
			int[] bit = new int[64];
			List<Long> list = new ArrayList<>(set);
			for (long l : list) {
				for (int i = (int)Long.highestOneBit(max); i >= 0; i--) {
					if ((l >> i & 1) == 1) {
						bit[i]++;
						g[i].add(l);
					}
				}
			}

			list = new ArrayList<>();
			int idx = 63;
			for (; idx >= 0; idx--) {
				if (bit[idx] >= k) {
					list = new ArrayList<>(g[idx]);
					break;
				}
			}
			while (idx > 0) {
				bit = new int[64];
				g = Stream.generate(ArrayList::new).limit(64).toArray(List[]::new);
				for (long l : list) {
					for (int i = idx; i >= 0; i--) {
						if ((l >> i & 1) == 1) {
							bit[i]++;
							g[i].add(l);
						}
					}
				}

				for (int i = idx; i >= 0; i--) {
					if (bit[i] >= k) {
						list = new ArrayList<>(g[i]);
						break;
					}
				}
				idx--;
			}

			Collections.sort(list, Comparator.reverseOrder());

			long ans = list.get(0);
			for (int i = 0; i < k; i++) {
				ans &= list.get(i);
			}

			out.println(ans);
		}
	}

	static void printArrayLine(int[] a, PrintWriter out) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				out.print(a[i]);
			} else {
				out.print(" " + a[i]);
			}
		}
		out.print("\n");
	}

	static class P implements Comparable<P> {
		String bit;
		long l;

		public P(String bit, long l) {
			super();
			this.bit = bit;
			this.l = l;
		}
		@Override
		public String toString() {
			return "P [bit=" + bit + ", l=" + l + "]";
		}

		@Override
		public int compareTo(P o) {
			return o.bit.compareTo(this.bit);
		}
	}

	static String zeroPad(String str, int len) {
		return String.format("%" + len + "s", str).replace(" ", "0");
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
