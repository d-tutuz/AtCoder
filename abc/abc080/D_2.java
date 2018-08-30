package abc080;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class D_2 {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int N = in.nextInt(), C = in.nextInt();
			P[] p = new P[N];
			for (int i = 0; i < N; i++) {
				int s = in.nextInt();
				int t = in.nextInt();
				int c = in.nextInt();

				p[i] = new P(s, t, c);
			}

			Arrays.sort(p);

			int[] imos = new int[100010];
			imos[p[0].s-1]++;
			imos[p[0].t]--;

			for (int i = 1; i < N; i++) {
				if (p[i-1].c == p[i].c && p[i-1].t == p[i].s) {
					imos[p[i].s]++;
					imos[p[i].t]--;
				} else {
					imos[p[i].s-1]++;
					imos[p[i].t]--;
				}
			}

			Arrays.parallelPrefix(imos, Math::addExact);

			int ans = 0;
			for (int i = 0; i < imos.length; i++) {
				ans = Math.max(ans, imos[i]);
			}

			out.println(ans);

		}

		class P implements Comparable<P> {
			int s, t, c;

			public P(int s, int t, int c) {
				super();
				this.s = s;
				this.t = t;
				this.c = c;
			}

			@Override
			public int compareTo(P o) {
				if (this.c == o.c) {
					return this.t - o.t;
				} else {
					return this.c - o.c;
				}
			}

			@Override
			public String toString(){
				return s+":"+t+":"+c;
			}

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
