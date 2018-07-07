package abc060;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Stream;

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
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int W = in.nextInt();
			int[] w = new int[n];
			List<Integer>[] v = new ArrayList[n];
			v = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			int base = 0;
			for (int i = 0; i < n; i++) {
				w[i] = in.nextInt();

				int vv = in.nextInt();
				if (i == 0) {
					base = w[i];
				}

				v[w[i]-base].add(vv);
			}

			for (int i = 0; i < 4; i++) {
				v[i].add(0);
				Collections.sort(v[i], Comparator.reverseOrder());
			}

			long ans = 0;
			for (int i = 0; i < v[0].size(); i++) {
				for (int j = 0; j < v[1].size(); j++) {
					for (int k = 0; k < v[2].size(); k++) {
						for (int m = 0; m < v[3].size(); m++) {
							long total = (long)base*(long)i + (long)(base+1)*(long)j + (long)(base+2)*(long)k + (long)(base+3)*(long)m;
							if (total <= W) {
								long val = 0;
								for (int l1 = 0; l1 < i; l1++) {
									val += v[0].get(l1);
								}
								for (int l2 = 0; l2 < j; l2++) {
									val += v[1].get(l2);
								}
								for (int l3 = 0; l3 < k; l3++) {
									val += v[2].get(l3);
								}
								for (int l4 = 0; l4 < m; l4++) {
									val += v[3].get(l4);
								}
								ans = Math.max(ans, val);
							}
						}
					}
				}
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
