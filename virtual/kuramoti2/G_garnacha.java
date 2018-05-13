package kuramoti2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class G_garnacha {

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

			int n = in.nextInt(), m = in.nextInt();
			int[] a = in.nextIntArray(n);

			int[] cumsum = new int[m + 1];
			long[] xx = new long[m + 1];
			for (int i = 0; i < n; i++) {
				if (i > 0) {
					if (a[i - 1] < a[i]) {
						cumsum[a[i]]--;
					}
					xx[a[i]] += (a[i] - a[i - 1] + m) % m - 1;
				}
				if (i < n - 1) {
					if (a[i + 1] > a[i]) {
						cumsum[a[i] + 1]++;
					} else {
						cumsum[1]++;
						cumsum[a[i + 1]]--;
						if (a[i] < m) {
							cumsum[a[i] + 1]++;
						}
					}
				}
			}
			for (int i = 1; i <= m; i++) {
				cumsum[i] += cumsum[i - 1];
			}

			long tmp = 0;
			for (int i = 1; i < n; i++) {
				tmp += Math.min((a[i] - a[i - 1] + m) % m, (a[i] - 1 + m) % m + 1);
			}

			long min = tmp;
			for (int x = 2; x <= m; x++) {
				tmp -= cumsum[x - 1];
				tmp += xx[x - 1];

				min = Math.min(min, tmp);
			}

			out.println(min);

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
