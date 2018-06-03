package abc001;

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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			P[] ps = new P[n];
			for (int i = 0; i < n; i++) {
				String[] str = in.nextString().split("-");
				String sHHMI = str[0];
				String tHHMI = str[1];
				int sMM = Integer.parseInt(sHHMI.substring(0, 2))*60 + Integer.parseInt(sHHMI.substring(2, 4));
				int tMM = Integer.parseInt(tHHMI.substring(0, 2))*60 + Integer.parseInt(tHHMI.substring(2, 4));
				ps[i] = new P(sMM, tMM);
			}

			for (int i = 0; i < n; i++) {
				ps[i].s = ps[i].s/5 * 5;
				ps[i].t = (ps[i].t + 4)/5 * 5;
			}

			Arrays.sort(ps);

			int[] imos = new int[2410];
			for (int i = 0; i < n; i++) {
				imos[ps[i].s]++;
				imos[ps[i].t+1]--;
			}

			for (int i = 1; i < imos.length; i++) {
				imos[i] += imos[i-1];
			}


			boolean isRain = false;
			for (int i = 0; i < imos.length; i++) {
				if (!isRain && imos[i] >= 1) {
					isRain = true;
					out.print(String.format("%02d%02d-", i/60, i%60));
				}
				if (isRain && imos[i] <= 0) {
					isRain = false;
					out.println(String.format("%02d%02d", (i-1)/60, (i-1)%60));
				}
			}
		}
	}
	static class P implements Comparable<P> {
		int s, t;

		public P(int s, int t) {
			super();
			this.s = s;
			this.t = t;
		}

		@Override
		public int compareTo(P o) {
			return -(o.s - this.s);
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
