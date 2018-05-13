package abc073;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.StringTokenizer;

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

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			int m = in.nextInt();
			int r = in.nextInt();
			char[] rn = new char[r];
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < r; i++) {
				sb.append(in.nextString());
			}
			rn = sb.toString().toCharArray();

			int[][] d = new int[n][n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(d[i], INF);
			}
			for (int i = 0; i < n; i++) {
				d[i][i] = 0;
			}
			for (int i = 0; i < m; i++) {
				int a = in.nextInt()-1;
				int b = in.nextInt()-1;
				int c = in.nextInt();
				d[a][b] = c;
				d[b][a] = c;
			}

			warshallFloyd(d);

			List<String> strList = new ArrayList<String>();
			strList = permute(rn, 0, rn.length-1, strList);
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < strList.size(); i++) {
				list.add(Integer.parseInt(strList.get(i)));
			}

			long cost = INF;
			for (int i = 0; i < list.size(); i++) {
				long tmp = 0;
				for (int j = 0; j < list.size()-1; j++) {
					int num = list.get(i);
					tmp += d[num-1][num/10-1];
				}
				cost = Math.min(cost, tmp);
			}
			out.println(cost);

		}
	}

	static void warshallFloyd(int[][] d) {
		int INF = 1<<30;
		int len = d.length;
		for (int k = 0; k < len; k++) {
			for (int i = 0; i < len; i++) {
				if (d[i][k] == INF) continue;
				for (int j = 0; j < len; j++) {
					if (d[k][j] == INF) continue;
					d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
				}
			}
		}
	}

	public static List<String> permute(char[] ary, int startIndex, int endIndex, List<String> list) {
		if (startIndex == endIndex) {
			list.add(String.valueOf(ary));
		} else {
			for (int i = startIndex; i <= endIndex; i++) {
				swap(ary, startIndex, i);
				permute(ary, startIndex + 1, endIndex, list);
				swap(ary, startIndex, i);
			}
		}
		return list;
	}

	public static void swap(char[] ary, int x, int y) {
		char temp = ary[x];
		ary[x] = ary[y];
		ary[y] = temp;
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
