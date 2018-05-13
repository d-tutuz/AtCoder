package codefestival2016qualc;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
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

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			long[] t = in.nextLongArray(n), a = in.nextLongArray(n);

			long[] tCalc = new long[n];
			Arrays.fill(tCalc, -1);

			long[] aCalc = new long[n];
			Arrays.fill(aCalc, -1);

			long[] tDef = new long[n];
			long[] aDef = new long[n];

			long tMax = -1, aMax = -1;
			for (int i = 0; i < n; i++) {
				if (tMax < t[i]) {
					tMax = t[i];
					tDef[i] = t[i];
				} else {
					tCalc[i] = tMax;
				}
			}
			for (int i = n-1; i >= 0; i--) {
				if (aMax < a[i]) {
					aMax = a[i];
					aDef[i] = a[i];
				} else {
					aCalc[i] = aMax;
				}
			}

			for (int i = 0; i < n; i++) {
				if (aCalc[i] == -1 && tCalc[i] == -1) {
					if (aDef[i] != tDef[i]) {
						out.println(0);
						return;
					}
				} else if (aCalc[i] == -1) {
					if (aDef[i] > tCalc[i]) {
						out.println(0);
						return;
					}
				} else if (tCalc[i] == -1) {
					if (tDef[i] > aCalc[i]) {
						out.println(0);
						return;
					}
				}
			}

			long ans = 1;
			for (int i = 0; i < n; i++) {
				if (tCalc[i] == -1 || aCalc[i] == -1) {
					continue;
				}
				ans = ans * min(aCalc[i],tCalc[i]) % modP;
			}
			out.println(ans);
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
