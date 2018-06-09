package codeflyer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class D {

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

            int h = in.nextInt();
            int w = in.nextInt();

            int n = in.nextInt();
            int m = in.nextInt();

            String[] a = in.nextStringArray(n);

            int[][] count = new int[Math.min(h + 1, 2 * n + 2)][Math.min(w + 1, 2 * m + 2)];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (a[i].charAt(j) == '#') {
                        count[i][j] += 1;
                        count[i + (count.length - 1 - n) + 1][j] += -1;
                        count[i][j + (count[0].length - 1 - m) + 1] += -1;
                        count[i + (count.length - 1 - n) + 1][j + (count[0].length - 1 - m) + 1] += 1;
                    }
                }
            }
            for (int i = 0; i < count.length; i++) {
                for (int j = 0; j < count[0].length - 1; j++) {
                    count[i][j + 1] += count[i][j];
                }
            }
            for (int i = 0; i < count[0].length; i++) {
                for (int j = 0; j < count.length - 1; j++) {
                    count[j + 1][i] += count[j][i];
                }
            }

            long ans = 0;
            for (int i = 0; i < count.length - 1; i++) {
                for (int j = 0; j < count[0].length - 1; j++) {
                    if (count[i][j] > 0) {
                        long p = 1;
                        long q = 1;
                        if (h > 2 * n && i == n) {
                            p = h - 2 * n;
                        }
                        if (w > 2 * m && j == m) {
                            q = w - 2 * m;
                        }
                        ans += p * q;
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

		public String[] nextStringArray(int n) {
			String[] res = new String[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextString();
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
