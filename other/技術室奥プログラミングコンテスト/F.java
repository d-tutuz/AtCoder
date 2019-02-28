package 技術室奥プログラミングコンテスト;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.StringTokenizer;

public class F {

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
			long[] a = new long[n];
			long[] b = new long[n];

			for (int i = 0; i < n; i++) {
				a[i] = in.nextLong();
				b[i] = in.nextLong();
			}

			P[] p = new P[n];
			for (int i = 0; i < n; i++) {
				p[i] = new P((double)a[i]/(double)b[i], i);
			}

			Arrays.sort(p);

			long ans = 0;
			long dist = Arrays.stream(a).sum();
			for (int i = 0; i < n; i++) {
				ans += (dist - a[p[i].y]) * b[p[i].y];
				dist -= a[p[i].y];
			}

			out.println(ans);

		}

		// template class
		class P implements Comparable<P> {
			double x;
			int y;

			P(double a, int b) {
				x = a;
				y = b;
			}

			@Override
			public boolean equals(Object o) {
				if (this == o) return true;
				if (!(o instanceof P)) return false;
				P p = (P) o;
				return x == p.x && y == p.y;
			}

			@Override
			public int hashCode() {
				return Objects.hash(x, y);
			}

			@Override
			public int compareTo(P p) {
				return -Double.compare(this.x, p.x);
			}

			@Override
			public String toString(){
				return x +":" + y;
			}
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
