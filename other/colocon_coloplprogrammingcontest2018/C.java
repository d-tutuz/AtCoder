package colocon_coloplprogrammingcontest2018;

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
	static int modP = 1000000007;

	static long[] prev;
	static long a, b;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			a = in.nextLong();
			b = in.nextLong();
			prev = new long[(int)(b-a+1)];
			out.println(dfs(a, 0));

		}

		static long dfs(long n, int p) {
			if (n > b) {
				return 1;
			}

			// n を購入しない場合
			long ret = dfs(n + 1, p);

			// n を購入する場合
			boolean isAdd = true;
			for (int i = 0; i < p; i++) {
				if (gcd(n, prev[i]) != 1) {
					isAdd = false;
				}
			}
			if (isAdd) {
				prev[p] = n;
				ret += dfs(n + 1, p + 1);
			}

			return ret;
		}

	}



	public static long gcd(long a, long b) {
		while (b > 0) {
			long c = a;
			a = b;
			b = c % b;
		}
		return a;
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
