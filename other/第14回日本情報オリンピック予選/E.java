package 第14回日本情報オリンピック予選;

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
import java.util.stream.Stream;

public class E {

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

			int h = in.nextInt(), w = in.nextInt();
			char[][] s = new char[h][w];
			for (int i = 0; i < h; i++) {
				s[i] = in.nextString().toCharArray();
			}

			List<P>[] list = new ArrayList[10];
			list = Stream.generate(ArrayList::new).limit(10).toArray(List[]::new);
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					for (int k = 1; k < 10; k++) {
						if ((s[i][j]-'0') == k) {
							list[k].add(new P(i, j));
						}
					}
				}
			}

			boolean[][] alive = new boolean[h][w];
			for (int i = 9; i >= 1; i--) {
				for (int j = 0; j < list[i].size(); j++) {
					int nh = list[i].get(j).first;
					int nw = list[i].get(j).second;
					if (i == 9) {
						alive[nh][nw] = true;
						continue;
					}

					int c = 0;
					for (int k = 0; k < 8; k++) {
						int mh = nh + mh8[k];
						int mw = nw + mw8[k];
						if (alive[mh][mw]) c++;
					}

					if (i + c >= 10) alive[nh][nw] = true;
				}
			}

			for (int i = 0; i < list[9].size(); i++) {
				int nh = list[9].get(i).first;
				int nw = list[9].get(i).second;
				int c = 0;
				for (int k = 0; k < 8; k++) {
					int mh = nh + mh8[k];
					int mw = nw + mw8[k];
					if (alive[mh][mw]) c++;
				}
				if (c == 0) {
					alive[nh][nw] = false;
				}
			}

			long ans = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (alive[i][j]) ans++;
				}
			}
			out.println(ans);
		}
	}

	static class P {
		int first, second;

		public P(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}

		@Override
		public String toString() {
			return "P [first=" + first + ", second=" + second + "]";
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

		public int[] nextIntArray1Index(int n) {
			int[] res = new int[n + 1];
			for (int i = 0; i < n; i++) {
				res[i + 1] = nextInt();
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

		public long[] nextLongArray1Index(int n) {
			long[] res = new long[n + 1];
			for (int i = 0; i < n; i++) {
				res[i + 1] = nextLong();
			}
			return res;
		}

		public double[] nextDoubleArray(int n) {
			double[] res = new double[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextDouble();
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
