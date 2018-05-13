package arc085;

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

	static int INF = 1 << 30;
	static int modP = 1000000007;

	static class TaskX {
        int n;
        int z;
        int w;
        int[] a;
        int[] dp1;
        int[] dp2;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			n = in.nextInt(); z = in.nextInt(); w = in.nextInt();
			a = in.nextIntArray(n);

			dp1 = new int[n];
			dp2 = new int[n];
			Arrays.fill(dp1, -1);
			Arrays.fill(dp2, -1);

			out.println(rec1(0));
		}

		int rec1(int idx) {
			if (dp1[idx] == -1) {
				int e = idx == 0 ? w : a[idx-1];
				int ans = abs(e - a[n-1]);
				for (int i = idx; i < n - 1; i++) {
					ans = max(ans, rec2(i+1));
				}
				dp1[idx] = ans;
			}
			return dp1[idx];
		}

		int rec2(int idx) {
			if (dp2[idx] == -1) {
				int e = a[idx-1];
				int ans = abs(e - a[n-1]);
				for (int i = idx; i < n - 1; i++) {
					ans = min(ans, rec1(i+1));
				}
				dp2[idx] = ans;
			}

			return dp2[idx];
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
