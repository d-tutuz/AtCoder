package arc092;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class D {

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
	static int modP = 1000000007;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int N = in.nextInt();
			int[] an = in.nextIntArray(N);
			int[] bn = in.nextIntArray(N);

			int ans = 0;
			for (int k = 0; k < 32; k++) {
				int count = 0;
				int[] sa = new int[N];
				int[] sb = new int[N];
				int mask = (1 << (k + 1)) - 1;
				for (int i = 0; i < N; i++) {
					sa[i] = an[i] & mask;
					sb[i] = bn[i] & mask;
				}
				Arrays.sort(sb);

				int T = 1 << k;
				for (int i = 0; i < N; i++) {
					int f1 = lowerBound(sb, 1 * T - sa[i]);
					int t1 = lowerBound(sb, 2 * T - sa[i]);
					int f2 = lowerBound(sb, 3 * T - sa[i]);
					int t2 = lowerBound(sb, 4 * T - sa[i]);
					count += t1 - f1;
					count %= 2;
					count += t2 - f2;
					count %= 2;
				}

				if (count == 1) {
					ans |= 1 << k;
				}

			}

			out.println(ans);

		}
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
