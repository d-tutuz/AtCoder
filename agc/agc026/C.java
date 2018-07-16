package agc026;

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
import java.util.TreeMap;

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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int k = in.nextInt();
			char[] s = in.nextString().toCharArray();


			TreeMap<String, Integer> mapRightRed = new TreeMap<>();
			TreeMap<String, Integer> mapRightBlue = new TreeMap<>();

			// 右側の半分全列挙を逆順で個数を保存しておく
			for (int i = 0; i < (1 << k); i++) {
				StringBuffer red = new StringBuffer();
				StringBuffer blue = new StringBuffer();

				for (int j = 0; j < k; j++) {
					if (((i >> j) & 1) == 1) {
						red.append(s[k+j]);
					} else {
						blue.append(s[k+j]);
					}
				}
				mapRightRed.merge(red.reverse().toString(), 1, Integer::sum);
				mapRightBlue.merge(blue.reverse().toString(), 1, Integer::sum);

			}

			long ans = 0;

			List<String> list = new ArrayList<>();

			// 左側を半分全列挙
			for (int i = 0; i < (1 << k); i++) {
				StringBuffer red = new StringBuffer();
				StringBuffer blue = new StringBuffer();

				for (int j = 0; j < k; j++) {
					if (((i >> j) & 1) == 1) {
						red.append(s[j]);
					} else {
						blue.append(s[j]);
					}
				}

				long cr = 0, cb = 0;
				if (mapRightBlue.containsKey(red.toString())) {
					cr += mapRightBlue.get(red.toString());
				}
				if (mapRightRed.containsKey(blue.toString())) {
					cb += mapRightRed.get(blue.toString());
				}

				if (cr * cb > 0) {
					list.add(red.toString());
				}
				ans += cr * cb;
			}

			for (String string : list) {
				out.println(string);
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
