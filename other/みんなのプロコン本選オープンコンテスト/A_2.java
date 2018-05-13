package みんなのプロコン本選オープンコンテスト;


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

public class A_2 {

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
		static String YAHOO = "yahoo";
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			String s1 = in.nextString();
			int len = s1.length();

			List<String> list = new ArrayList<>();
			int tarLen = len/5;
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < tarLen; i++) {
				sb.append(YAHOO);
			}
			list.add(sb.toString());
			list.add(sb.append(YAHOO).toString());

			int ans = INF;
			for (String s2 : list) {
				int[][] dp = new int[s2.length()+1][s1.length()+1];
				for (int i = 0; i < s2.length(); i++) {
					for (int j = 0; j < s1.length(); j++) {
						dp[i][j] = INF;
					}
				}
				for (int i = 0; i < s1.length()+1; i++) {
					dp[0][i] = i;
				}
				for (int i = 0; i < s2.length()+1; i++) {
					dp[i][0] = i;
				}

				for (int i = 0; i < s2.length(); i++) {
					for (int j = 0; j < s1.length(); j++) {
						if (s2.substring(i, i+1).equals(s1.substring(j, j+1))) {
							dp[i+1][j+1] = Math.min(dp[i][j], Math.min(dp[i+1][j]+1, dp[i][j+1]+1));
						} else {
							dp[i+1][j+1] = Math.min(dp[i][j]+1, Math.min(dp[i+1][j]+1, dp[i][j+1]+1));
						}
					}
				}
				ans = Math.min(ans, dp[s2.length()][s1.length()]);
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
