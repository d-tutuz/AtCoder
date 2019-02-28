package みんなのプロコン;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.StringTokenizer;

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

	static int INF = 1 << 30;
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt(), k = in.nextInt();
			boolean[] a = new boolean[n];
			for (int i = 0; i < k; i++) {
				a[in.nextInt()-1] = true;
			}
			List<String> need = new ArrayList<>();
			List<String> notNeed = new ArrayList<>();

			for (int i = 0; i < n; i++) {
				if (a[i]) {
					need.add(in.nextString());
				} else {
					notNeed.add(in.nextString());
				}
			}

			if (notNeed.isEmpty()) {
				out.println();
				return;
			}

			// 検索にヒットする中で最長prefixを求める
			int needMax = need.get(0).length();
			for (int i = 0; i < need.size()-1; i++) {
				needMax = comStr(need.get(i), need.get(i+1), needMax);
			}

			String com = need.get(0).substring(0, needMax);

			// 検索にヒットしない中で、検索にヒットする共通文字列との最長prefixを求める
			int notNeedMax = -1;
			for (int i = 0; i < notNeed.size(); i++) {
				notNeedMax = Math.max(notNeedMax, comStr(notNeed.get(i), com, INF));
			}

			if (needMax <= notNeedMax) {
				out.println(-1);
				return;
			} else {
				out.println(com.substring(0, notNeedMax+1));
			}

		}

		int comStr(String s1, String s2, int limit) {
			int ret = 0;
			int len1 = s1.length();
			int len2 = s2.length();

			for (int i = 0; i < Math.min(Math.min(len1, len2), limit); i++) {
				if (s1.charAt(i) == s2.charAt(i)) {
					ret++;
				} else {
					break;
				}
			}

			return ret;
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
