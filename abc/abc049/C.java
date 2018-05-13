package abc049;

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

			char[] s = in.nextString().toCharArray();
			String[] t = new String[4];
			t[0] = ("dream");
			t[1] = ("dreamer");
			t[2] = ("erase");
			t[3] = ("eraser");

			int slen = s.length;
			while (slen > 0) {
				boolean isMatch = false;
				for (int k = 0; k < 4; k++) {
					int tlen = t[k].length();
					char[] tar = t[k].toCharArray();
					boolean isSame = true;
					for (int i = slen - tlen; i < slen; i++) {
						if (i < 0) {
							isSame = false;
							break;
						}
						if (s[i] == tar[i - slen + tlen]) {
							continue;
						} else {
							isSame = false;
							break;
						}
					}
					if (isSame) {
						slen -= tlen;
						isMatch = true;
						break;
					}
				}
				if (!isMatch) {
					out.println("NO");
					return;
				}
			}
			out.println("YES");

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
