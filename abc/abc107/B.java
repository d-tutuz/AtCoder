package abc107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class B {

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

			int h = in.nextInt(), w = in.nextInt();
			char[][] s = new char[h][w];
			for (int i = 0; i < h; i++) {
				s[i] = in.nextString().toCharArray();
			}

			List<char[]> list = new ArrayList<>();
			for (int i = 0; i < h; i++) {
				boolean ok = true;
				for (int j = 0; j < w; j++) {
					if (s[i][j] != '.') {
						ok = false;
						break;
					}
				}
				if (!ok) {
					list.add(new String(s[i]).toCharArray());
				}
			}

			char[][] t = new char[list.size()][w];
			for (int i = 0; i < list.size(); i++) {
				t[i] = list.get(i);
			}

//			for (char[] cs : t) {
//				out.println(cs);
//			}

			Set<Integer> set = new HashSet<>();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < w; i++) {
				boolean ok = true;
				StringBuilder tt = new StringBuilder();
				for (int j = 0; j < t.length; j++) {
					tt.append(t[j][i]);
					if (t[j][i] != '.') {
						ok = false;
					}
				}
				if (ok) {
					set.add(i);
//					sb.append(tt.toString());
//					sb.append("\n");
				}
			}

			for (int i = 0; i < t.length; i++) {
				for (int j = 0; j < t[0].length; j++) {
					if (set.contains(j)) continue;
					out.print(t[i][j]);
				}
				out.print("\n");
			}
//			out.println(sb.toString());


		}
	}

	// other template
	static int min(int a, int b) {
		return Math.min(a, b);
	}

	static long min(long a, long b) {
		return Math.min(a, b);
	}

	static void fill(int[][] a, int value) {
		for (int i = 0; i < a.length; i++) {
			Arrays.fill(a[i], value);
		}
	}

	static void fill(long[][] a, int value) {
		for (int i = 0; i < a.length; i++) {
			Arrays.fill(a[i], value);
		}
	}

	static void fill(char[][] a, char c) {
		for (int i = 0; i < a.length; i++) {
			Arrays.fill(a[i], c);
		}
	}

	static int max(int a, int b) {
		return Math.max(a, b);
	}

	static long max(long a, long b) {
		return Math.max(a, b);
	}

	// faster input template
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
