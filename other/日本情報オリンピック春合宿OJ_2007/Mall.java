package 日本情報オリンピック春合宿OJ_2007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class Mall {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static int INF = 1 << 28;

	static class TaskX {

		int m, n;
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			m = in.nextInt(); n = in.nextInt();
			int a = in.nextInt(), b = in.nextInt();

			int[] sum = new int[f(n, m)];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					sum[f(i, j)] = in.nextInt();
					if (sum[f(i, j)] < 0) sum[f(i, j)] = INF;
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (i > 0) sum[f(i, j)] += sum[f(i-1, j)];
					if (j > 0) sum[f(i, j)] += sum[f(i, j-1)];
					if (i > 0 && j > 0) sum[f(i, j)] -= sum[f(i-1, j-1)];
				}
			}

			int ans = INF;
			for (int x1 = 0; x1+b-1 < n; x1++) {
				for (int y1 = 0; y1+a-1 < m; y1++) {
					int x2 = x1 + b - 1, y2 = y1 + a - 1;
					int res = sum[f(x2, y2)];
					if (x1 > 0) res -= sum[f(x1-1, y2)];
					if (y1 > 0) res -= sum[f(x2, y1-1)];
					if (x1 > 0 && y1 > 0) res += sum[f(x1-1, y1-1)];
					if (res < 0) res = INF;
					ans = Math.min(ans, res);
				}
			}

			out.println(ans);
		}

		int f(int i, int j) {
			return i * m + j;
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

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
