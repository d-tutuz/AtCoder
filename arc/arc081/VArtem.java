package arc081;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class VArtem {

	int MOD = 1_000_000_007;
	int INF = Integer.MAX_VALUE / 3;
	int ALPH = 26;

	void solve() {
		char[] s = in.nextToken().toCharArray();
		int n =s.length;
		int[] dp = new int[n];
		int[] last = new int[ALPH];
		int[] lastPos = new int[ALPH];
		Arrays.fill(lastPos, n);
		int[] next = new int[n];
		int[] nextChar = new int[n];

		// 文字列を後ろから順番に参照する
		for (int i = n - 1; i >= 0; i--) {

			// 今対象にしている i 番目の文字列 ⇒ アルファベットの順番に変換
			int code = s[i] - 'a';
			dp[i] = INF;

			// アルファベットを順番に参照し、min{ dp[next(c,i)+1] +1 }を実装
			for (int j = 0; j < ALPH; j++) {
				if (last[j] + 1 < dp[i]) {
					dp[i] = last[j] + 1;

					// これは何？？
					next[i] = lastPos[j];
					nextChar[i] = j;
				}
			}
			last[code] = dp[i];
			lastPos[code] = i;
		}

		int best = -1, bestPos = -1;
		for (int i = 0; i < ALPH; i++) {
			if (best == -1 || last[i] < last[best]) {
				best = i;
				bestPos = lastPos[best];
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append((char) (best + 'a'));
		while (bestPos < n) {
			sb.append((char) (nextChar[bestPos] + 'a'));
			bestPos = next[bestPos];
		}
		out.println(sb.toString());
	}

	FastScanner in;
	PrintWriter out;

	void run() {
		in = new FastScanner();
		out = new PrintWriter(System.out);
		solve();
		out.close();
	}

	class FastScanner {
		BufferedReader br;
		StringTokenizer st;

		public FastScanner() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastScanner(String s) {
			try {
				br = new BufferedReader(new FileReader(s));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public String nextToken() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
				}
			}
			return st.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(nextToken());
		}

		public long nextLong() {
			return Long.parseLong(nextToken());
		}

		public double nextDouble() {
			return Double.parseDouble(nextToken());
		}
	}

	public static void main(String[] args) {
		new VArtem().run();
	}
}

