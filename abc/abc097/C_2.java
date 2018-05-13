package abc097;

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
import java.util.TreeSet;

public class C_2 {

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
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			String s = in.nextString();
			int k = in.nextInt();
			int len = s.length();

			TreeSet<String> set = new TreeSet<>();

			for (int i = 0; i < len; i++) {
				if (i + k <= len) {
					set.add(s.substring(i, i+k));
				} else {
					set.add(s.substring(i, len));
				}
			}

			List<String> list = new ArrayList<>();

			int count = 1;
			for (String string : set) {
				if (count <= 5) {
					list.add(string);
					count++;
				} else {
					break;
				}
			}

			TreeSet<String> nset = new TreeSet<>();
			for (String str : list) {
				int nlen = str.length();
				for (int i = 0; i < nlen; i++) {
					for (int j = i+1; j <= nlen; j++) {
						nset.add(str.substring(i, j));
					}
				}
			}

			count = 1;
			for (String string : nset) {
				if (count == k) {
					out.println(string);
					return;
				}
				count++;
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
