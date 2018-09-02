package abc108;

import static java.lang.Math.*;

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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int l = in.nextInt();

			// l 以下の最大の2べきの整数 r を求める
			int r = f(l);

			// 追加するパスの経路数
			int x = l - (int)pow(2, r);

			// 基準のグラフを構築
			List<P> list = new ArrayList<>();
			for (int i = 1; i <= r; i++) {
				list.add(new P(i, i+1, 0));
				list.add(new P(i, i+1, (int)pow(2, i-1)));
			}

			// 値の最大値と構築するノードの情報をもとに辺を構築
			int max = l;
			char[] info = g(r, x).toCharArray();
			for (int i = r; i >= 0; i--) {
				if (info[i] == '0') continue;
				max--;
				int cost = max - ((int)pow(2, i) - 1);
				list.add(new P(i+1, r+1, cost));
				max = cost;
			}

			out.printf("%d %d\n", r+1, list.size());
			for (P p : list) {
				out.println(p);
			}

		}
	}

	static class P {
		int l, r, c;

		public P(int l, int r, int c) {
			super();
			this.l = l;
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return l + " " + r + " " + c;
		}
	}

	static int f(int l) {
		int ret = 0;
		int num = 1;
		while (num * 2 <= l) {
			ret++;
			num *= 2;
		}
		return ret;
	}

	static String g(int r, int x) {
		String str = Integer.toBinaryString(x);
		str = String.format("%"+(r+1)+"s", str).replace(" ", "0");
		return reverse(str);
	}

	static String reverse(String str) {
		StringBuilder sb = new StringBuilder();
		sb.append(str);
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
