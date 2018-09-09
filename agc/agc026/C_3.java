package agc026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class C_3 {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskC solver = new TaskC();
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

	static class TaskC {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			String s = in.nextString();


			Map<Pair<String, String>, Long> m1 = new TreeMap<>();
			Map<Pair<String, String>, Long> m2 = new TreeMap<>();

			int pad = n;
			for (int i = 0; i < 1 << n; i++) {
				StringBuilder red = new StringBuilder();
				StringBuilder blue = new StringBuilder();

				for (int j = 0; j < n; j++) {
					if ((i >> j & 1) == 1) {
						red.append(s.charAt(j));
					} else {
						blue.append(s.charAt(j));
					}
				}
				m1.merge(new Pair<String, String>(red.toString(), blue.toString()), 1L, Long::sum);
			}

			for (int i = 0; i < 1 << n; i++) {
				StringBuilder red = new StringBuilder();
				StringBuilder blue = new StringBuilder();

				for (int j = 0; j < n; j++) {
					if ((i >> j & 1) == 1) {
						red.append(s.charAt(pad+j));
					} else {
						blue.append(s.charAt(pad+j));
					}
				}
				m2.merge(new Pair<String, String>(rev(red.toString()), rev(blue.toString())), 1L, Long::sum);
			}

			long ans = 0;

			for (Pair<String, String> p : m1.keySet()) {
				if (m1.containsKey(p) && m2.containsKey(p)) {
					ans += m1.get(p) * m2.get(p);
				}
			}

			out.println(ans);

		}
	}

	public static class Pair<U, V> implements Comparable<Pair<U, V>> {

		public final U first;
		public final V second;

		public static <U, V> Pair<U, V> makePair(U first, V second) {
			return new Pair<U, V>(first, second);
		}

		private Pair(U first, V second) {
			this.first = first;
			this.second = second;
		}

		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}

			Pair pair = (Pair) o;

			return !(first != null ? !first.equals(pair.first)
					: pair.first != null)
					&& !(second != null ? !second.equals(pair.second)
							: pair.second != null);
		}

		public int hashCode() {
			int result = first != null ? first.hashCode() : 0;
			result = 31 * result + (second != null ? second.hashCode() : 0);
			return result;
		}

		public String toString() {
			return "(" + first + "," + second + ")";
		}

		public int compareTo(Pair<U, V> o) {
			int value = ((Comparable<U>) first).compareTo(o.first);
			if (value != 0) {
				return value;
			}
			return ((Comparable<V>) second).compareTo(o.second);
		}

	}

	static String rev(String s) {
		StringBuilder sb = new StringBuilder();
		sb.append(s);
		return sb.reverse().toString();
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

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

	static long max(long... n) {
		long ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.max(ret, n[i]);
		}
		return ret;
	}

	static int max(int... n) {
		int ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.max(ret, n[i]);
		}
		return ret;
	}

	static long min(long... n) {
		long ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.min(ret, n[i]);
		}
		return ret;
	}

	static int min(int... n) {
		int ret = n[0];
		for (int i = 0; i < n.length; i++) {
			ret = Math.min(ret, n[i]);
		}
		return ret;
	}

}
