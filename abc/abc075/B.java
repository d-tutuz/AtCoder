package abc075;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class B {

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

	static int[] mx = {-1,-1,-1,0,0,1,1,1};
	static int[] my = {-1,0,1,-1,1,-1,0,1};


	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int h = in.nextInt(), w = in.nextInt();
			char[][] map = new char[h][w];
			for (int i = 0; i < h; i++) {
				map[i] = in.nextString().toCharArray();
			}

			for (int ny = 0; ny < h; ny++) {
				for (int nx = 0; nx < w; nx++) {
					if (map[ny][nx] == '#') {
						continue;
					}
					int count = 0;
					for (int i = 0; i < 8; i++) {
						int ty = ny + my[i];
						int tx = nx + mx[i];
						if (ty < 0 || tx < 0 || ty >= h || tx >= w) {
							continue;
						}
						if (map[ty][tx] == '#') {
							count++;
						}
					}
					map[ny][nx] = (char) count;
				}
			}

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] != '#') {
						sb.append((int)map[i][j]);
					} else {
						sb.append(map[i][j]);
					}
				}
				sb.append("\n");
			}

			out.print(sb.toString());

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
