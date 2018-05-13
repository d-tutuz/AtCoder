package codefestival2017qualC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class C {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
//		inputStream = new FileInputStream(new File("/workspace/Atcoder/other/codefestival2017qualC/1_15.txt"));
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

			String s = in.nextString();
			char[] t = new String(s).replace("x", "").toCharArray();

			if (t.length == 0) {
				out.println(0);
				return;
			}
			// 回分可能判定
			if (t.length % 2 == 0) {
				int half = t.length / 2;
				int l = half - 1, r = half;
				int count = 1;
				while (count <= half) {
					if (t[r] == t[l]) {
						r++; l--; count++;
						continue;
					} else {
						out.println(-1);
						return;
					}
				}
			} else {
				int half = t.length / 2;
				int l = half, r = half;
				int count = 0;
				while (count <= half) {
					if (t[r] == t[l]) {
						r++; l--; count++;
						continue;
					} else {
						out.println(-1);
						return;
					}
				}
			}

			// 操作回数
			char[] cs = s.toCharArray();
			int count = 0;
			if (t.length % 2 == 0) {
				int kijun = t.length / 2;
				count = 0;
				int l = -1, r = -1;
				boolean isNext = false;
				for (int i = 0; i < cs.length; i++) {
					if (cs[i] != 'x') {
						count++;
					}
					if (count == kijun) {
						l = i;
						count++;
						isNext = true;
						continue;
					}
					if (cs[i] != 'x' && isNext) {
						r = i;
						break;
					}
				}
				count = 0;
				while (l >= 0 && r < cs.length) {
					if (cs[l] == cs[r]) {
						l--; r++;
						continue;
					} else if (cs[l] == 'x') {
						count++; l--;
					} else if (cs[r] == 'x') {
						count++; r++;
					}
				}
				count += (l + 1) + (cs.length - r);
			} else {
				int kijun = t.length / 2 + 1;
				count = 0;
				int l = -1, r = -1;
				for (int i = 0; i < cs.length; i++) {
					if (cs[i] != 'x') {
						count++;
					}
					if (count == kijun) {
						l = i;
						r = i;
						break;
					}
				}
				count = 0;
				while (l >= 0 && r < cs.length) {
					if (cs[l] == cs[r]) {
						l--; r++;
						continue;
					} else if (cs[l] == 'x') {
						count++; l--;
					} else if (cs[r] == 'x') {
						count++; r++;
					}
				}
				count += (l + 1) + (cs.length - r);
			}

			out.println(count);
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
