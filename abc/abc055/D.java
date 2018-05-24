package abc055;

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

			int n = in.nextInt();
			char[] s = in.nextString().toCharArray();

			// 1
			char[] ans = new char[n];
			if (s[0] == 'o') {
				ans = new char[n];
				ans[(n-1)%n] = 'S';
				ans[(n+0)%n] = 'S';
				ans[(n+1)%n] = 'S';

				for (int i = 1; i < n; i++) {
					if (ans[i] == 'S' && s[i] == 'o') {
						ans[(i + 1)%n] = ans[i-1];
					} else if (ans[i] == 'S' && s[i] == 'x') {
						ans[(i + 1)%n] = other(ans[i-1]);
					} else if (ans[i] == 'W' && s[i] == 'o') {
						ans[(i + 1)%n] = other(ans[i-1]);
					} else if (ans[i] == 'W' && s[i] == 'x') {
						ans[(i + 1)%n] = ans[i-1];
					}
				}
				if (ans[(n-1)%n] == 'S' && ans[(n+0)%n] == 'S' && ans[(n+1)%n] == 'S') {
					out.println(new String(ans));
					return;
				}

				// 2
				ans = new char[n];
				ans[(n - 1) % n] = 'W';
				ans[(n + 0) % n] = 'S';
				ans[(n + 1) % n] = 'W';

				for (int i = 1; i < n; i++) {
					if (ans[i] == 'S' && s[i] == 'o') {
						ans[(i + 1)%n] = ans[i - 1];
					} else if (ans[i] == 'S' && s[i] == 'x') {
						ans[(i + 1)%n] = other(ans[i - 1]);
					} else if (ans[i] == 'W' && s[i] == 'o') {
						ans[(i + 1)%n] = other(ans[i - 1]);
					} else if (ans[i] == 'W' && s[i] == 'x') {
						ans[(i + 1)%n] = ans[i - 1];
					}
				}
				if (ans[(n-1)%n] == 'W' && ans[(n+0)%n] == 'S' && ans[(n+1)%n] == 'W') {
					out.println(new String(ans));
					return;
				}
				// 3
				ans = new char[n];
				ans[(n - 1) % n] = 'W';
				ans[(n + 0) % n] = 'W';
				ans[(n + 1) % n] = 'S';

				for (int i = 1; i < n; i++) {
					if (ans[i] == 'S' && s[i] == 'o') {
						ans[(i + 1)%n] = ans[i - 1];
					} else if (ans[i] == 'S' && s[i] == 'x') {
						ans[(i + 1)%n] = other(ans[i - 1]);
					} else if (ans[i] == 'W' && s[i] == 'o') {
						ans[(i + 1)%n] = other(ans[i - 1]);
					} else if (ans[i] == 'W' && s[i] == 'x') {
						ans[(i + 1)%n] = ans[i - 1];
					}
				}
				if (ans[(n-1)%n] == 'W' && ans[(n+0)%n] == 'W' && ans[(n+1)%n] == 'S') {
					out.println(new String(ans));
					return;
				}

				// 4
				ans = new char[n];
				ans[(n - 1) % n] = 'S';
				ans[(n + 0) % n] = 'W';
				ans[(n + 1) % n] = 'W';

				for (int i = 1; i < n; i++) {
					if (ans[i] == 'S' && s[i] == 'o') {
						ans[(i + 1)%n] = ans[i - 1];
					} else if (ans[i] == 'S' && s[i] == 'x') {
						ans[(i + 1)%n] = other(ans[i - 1]);
					} else if (ans[i] == 'W' && s[i] == 'o') {
						ans[(i + 1)%n] = other(ans[i - 1]);
					} else if (ans[i] == 'W' && s[i] == 'x') {
						ans[(i + 1)%n] = ans[i - 1];
					}
				}
				if (ans[(n-1)%n] == 'S' && ans[(n+0)%n] == 'W' && ans[(n+1)%n] == 'W') {
					out.println(new String(ans));
					return;
				}
			} else {
				ans = new char[n];
				ans[(n-1)%n] = 'W';
				ans[(n+0)%n] = 'S';
				ans[(n+1)%n] = 'S';

				for (int i = 1; i < n; i++) {
					if (ans[i] == 'S' && s[i] == 'o') {
						ans[(i + 1)%n] = ans[i-1];
					} else if (ans[i] == 'S' && s[i] == 'x') {
						ans[(i + 1)%n] = other(ans[i-1]);
					} else if (ans[i] == 'W' && s[i] == 'o') {
						ans[(i + 1)%n] = other(ans[i-1]);
					} else if (ans[i] == 'W' && s[i] == 'x') {
						ans[(i + 1)%n] = ans[i-1];
					}
				}
				if (ans[(n-1)%n] == 'W' && ans[(n+0)%n] == 'S' && ans[(n+1)%n] == 'S') {
					out.println(new String(ans));
					return;
				}

				// 2
				ans = new char[n];
				ans[(n - 1) % n] = 'S';
				ans[(n + 0) % n] = 'S';
				ans[(n + 1) % n] = 'W';

				for (int i = 1; i < n; i++) {
					if (ans[i] == 'S' && s[i] == 'o') {
						ans[(i + 1)%n] = ans[i - 1];
					} else if (ans[i] == 'S' && s[i] == 'x') {
						ans[(i + 1)%n] = other(ans[i - 1]);
					} else if (ans[i] == 'W' && s[i] == 'o') {
						ans[(i + 1)%n] = other(ans[i - 1]);
					} else if (ans[i] == 'W' && s[i] == 'x') {
						ans[(i + 1)%n] = ans[i - 1];
					}
				}
				if (ans[(n-1)%n] == 'S' && ans[(n+0)%n] == 'S' && ans[(n+1)%n] == 'W') {
					out.println(new String(ans));
					return;
				}
				// 3
				ans = new char[n];
				ans[(n - 1) % n] = 'W';
				ans[(n + 0) % n] = 'W';
				ans[(n + 1) % n] = 'W';

				for (int i = 1; i < n; i++) {
					if (ans[i] == 'S' && s[i] == 'o') {
						ans[(i + 1)%n] = ans[i - 1];
					} else if (ans[i] == 'S' && s[i] == 'x') {
						ans[(i + 1)%n] = other(ans[i - 1]);
					} else if (ans[i] == 'W' && s[i] == 'o') {
						ans[(i + 1)%n] = other(ans[i - 1]);
					} else if (ans[i] == 'W' && s[i] == 'x') {
						ans[(i + 1)%n] = ans[i - 1];
					}
				}
				if (ans[(n-1)%n] == 'W' && ans[(n+0)%n] == 'W' && ans[(n+1)%n] == 'W') {
					out.println(new String(ans));
					return;
				}

				// 4
				ans = new char[n];
				ans[(n - 1) % n] = 'S';
				ans[(n + 0) % n] = 'W';
				ans[(n + 1) % n] = 'S';

				for (int i = 1; i < n; i++) {
					if (ans[i] == 'S' && s[i] == 'o') {
						ans[(i + 1)%n] = ans[i - 1];
					} else if (ans[i] == 'S' && s[i] == 'x') {
						ans[(i + 1)%n] = other(ans[i - 1]);
					} else if (ans[i] == 'W' && s[i] == 'o') {
						ans[(i + 1)%n] = other(ans[i - 1]);
					} else if (ans[i] == 'W' && s[i] == 'x') {
						ans[(i + 1)%n] = ans[i - 1];
					}
				}
				if (ans[(n-1)%n] == 'S' && ans[(n+0)%n] == 'W' && ans[(n+1)%n] == 'S') {
					out.println(new String(ans));
					return;
				}
			}


			out.println(-1);
		}

		static char other(char a) {
			return a == 'S' ? 'W' : 'S';
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
