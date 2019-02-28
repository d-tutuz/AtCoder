package みんなのプロコン;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
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

			int n = in.nextInt(), k = in.nextInt();
			int[] idxs = new int[k+1];
			for (int i = 0; i < k; i++) {
				idxs[i] = in.nextInt()-1;
			}
			idxs[k] = INF;
			String[] strs = new String[n];
			for (int i = 0; i < n; i++) {
				strs[i] = in.nextString();
			}

			int l  = -1, maxLen = -1;
			String com = "";
			for (int i = 0; i < k; i++) {
				int len = strs[idxs[i]].length();
				if (maxLen < len) {
					maxLen = len;
					com = strs[idxs[i]].substring(0, len);
				}
			}
			int m = -1, r = maxLen;

			while (r - l > 1) {
				m = (l+r)/2;

				String c = com.substring(0, m);
				if (checkMax(strs, idxs, c)) {
					l = m;
				} else {
					r = m;
				}
			}
			int sup = m;

			l = -1; r = maxLen;
			while (r - l > 1) {
				m = (l+r)/2;

				String c = com.substring(0, m);
				if (checkMin(strs, idxs, c)) {
					r = m;
				} else {
					l = m;
				}
			}

			out.println(l+1 > sup ? "-1" : com.substring(0, l+1));
		}

		boolean checkMax(String[] strs, int[] idxs, String com) {

			for (int i = 0, j = 0; i < strs.length; i++) {
				if (i == idxs[j]) {
					if (!strs[i].startsWith(com)) return false;
					j++;
				}
			}

			return true;
		}

		boolean checkMin(String[] strs, int[] idxs, String com) {

			for (int i = 0, j = 0; i < strs.length; i++) {

				if (i == idxs[j]) {
					j++;
					continue;
				} else {
					if (strs[i].startsWith(com)) return false;
				}
			}

			return true;
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
