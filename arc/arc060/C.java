package arc060;

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

			int N = in.nextInt();
			int A = in.nextInt();
			int[] xn = in.nextIntArray(N);

			List<P> s1 = new ArrayList<>();
			List<P> s2 = new ArrayList<>();
			List<P> s3 = new ArrayList<>();
			List<P> s4 = new ArrayList<>();
			List<P> s5 = new ArrayList<>();


			int[] x1 = new int[N/5];
			int[] x2 = new int[N/5];
			int[] x3 = new int[N/5];
			int[] x4 = new int[N/5];
			int[] x5 = new int[N-(N/5)*4];

			for (int i = 0; i < N/5; i++) {
				x1[i] = xn[i];
			}

			for (int i = 0; i < N/5; i++) {
				 x2[i] = xn[i+(N/5)];
			}

			for (int i = 0; i < N/5; i++) {
				 x3[i] = xn[i+(N/5)*2];
			}

			for (int i = 0; i < N/5; i++) {
				 x4[i] = xn[i+(N/5)*3];
			}

			for (int i = 0; i < N-(N/5)*4; i++) {
				 x5[i] = xn[i+(N/5)*4];
			}


			int x1Len = x1.length;
			for (int i = 0; i < 1 << x1Len; i++) {
				int tmpNum = 0;
				int tmpCount = 0;
				for (int j = 0; j < x1Len; j++) {
					if ((i >> j & 1) == 1) {
						tmpNum += x1[j];
						tmpCount++;
					}
				}
				boolean isFound = false;
				for (P p : s1) {
					if (p.c == tmpCount && p.n == tmpNum) {
						p.d++;
						isFound = true;
					}
				}
				if (!isFound) s1.add(new P(tmpNum, tmpCount));
			}

			int x2Len = x2.length;
			for (int i = 0; i < 1 << x2Len; i++) {
				int tmpNum = 0;
				int tmpCount = 0;
				for (int j = 0; j < x2Len; j++) {
					if ((i >> j & 1) == 1) {
						tmpNum += x2[j];
						tmpCount++;
					}
				}
				boolean isFound = false;
				for (P p : s2) {
					if (p.c == tmpCount && p.n == tmpNum) {
						p.d++;
						isFound = true;
					}
				}
				if (!isFound) s2.add(new P(tmpNum, tmpCount));
			}

			int x3Len = x3.length;
			for (int i = 0; i < 1 << x3Len; i++) {
				int tmpNum = 0;
				int tmpCount = 0;
				for (int j = 0; j < x3Len; j++) {
					if ((i >> j & 1) == 1) {
						tmpNum += x3[j];
						tmpCount++;
					}
				}
				boolean isFound = false;
				for (P p : s3) {
					if (p.c == tmpCount && p.n == tmpNum) {
						p.d++;
						isFound = true;
					}
				}
				if (!isFound) s3.add(new P(tmpNum, tmpCount));
			}

			int x4Len = x4.length;
			for (int i = 0; i < 1 << x4Len; i++) {
				int tmpNum = 0;
				int tmpCount = 0;
				for (int j = 0; j < x4Len; j++) {
					if ((i >> j & 1) == 1) {
						tmpNum += x4[j];
						tmpCount++;
					}
				}
				boolean isFound = false;
				for (P p : s4) {
					if (p.c == tmpCount && p.n == tmpNum) {
						p.d++;
						isFound = true;
					}
				}
				if (!isFound) s4.add(new P(tmpNum, tmpCount));
			}

			int x5Len = x5.length;
			for (int i = 0; i < 1 << x5Len; i++) {
				int tmpNum = 0;
				int tmpCount = 0;
				for (int j = 0; j < x5Len; j++) {
					if ((i >> j & 1) == 1) {
						tmpNum += x5[j];
						tmpCount++;
					}
				}
				boolean isFound = false;
				for (P p : s5) {
					if (p.c == tmpCount && p.n == tmpNum) {
						p.d++;
						isFound = true;
					}
				}
				if (!isFound) s5.add(new P(tmpNum, tmpCount));
			}

			long ans = 0;
			for (P p1 : s1) {
				for (P p2 : s2) {
					for (P p3 : s3) {
						for (P p4 : s4) {
							for (P p5 : s5) {
								int count = p1.c+p2.c+p3.c+p4.c+p5.c;
								int sum = p1.n+p2.n+p3.n+p4.n+p5.n;
								int multi = p1.d*p2.d*p3.d*p4.d*p5.d;
								if (count == 0) {
									continue;
								}

								if (sum % count == 0 && sum / count == A) {
									ans += multi;
								}
							}
						}
					}
				}
			}

			out.println(ans);


		}
	}
	static class P {
		int n, c, d;
		P (int num, int count) {
			this.n = num;
			this.c = count;
			this.d = 1;
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
