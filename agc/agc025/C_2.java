package agc025;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class C_2 {

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
			PriorityQueue<Seg> ql1 = new PriorityQueue<>((o1, o2) -> o2.l - o1.l);
			PriorityQueue<Seg> qr1 = new PriorityQueue<>((o1, o2) -> o1.r - o2.r);

			PriorityQueue<Seg> ql2 = new PriorityQueue<>((o1, o2) -> o2.l - o1.l);
			PriorityQueue<Seg> qr2 = new PriorityQueue<>((o1, o2) -> o1.r - o2.r);

			for (int i = 0; i < n; i++) {
				int li = in.nextInt();
				int ri = in.nextInt();
				ql1.add(new Seg(i, li, ri));
				qr1.add(new Seg(i, li, ri));
				ql2.add(new Seg(i, li, ri));
				qr2.add(new Seg(i, li, ri));
			}


			// 右 -> 左 -> 右 ...
			long ans1 = 0;
			long now1 = 0;
			boolean[] used = new boolean[n];
			for (int i = 0; i < INF; i++) {
				while (!ql1.isEmpty() || !qr1.isEmpty()) {
					// 右
					if (i % 2 == 0) {
						Seg s = ql1.poll();
						int idx = s.i;
						int next = s.r;
						if (next <= now1 || used[idx]) {
							continue;
						}
						ans1 += abs(next - now1);
						now1 = next;
						used[idx] = true;
					// 左
					} else {
						Seg s = qr1.poll();
						int idx = s.i;
						int next = s.l;
						if (now1 <= next || used[idx]) {
							continue;
						}
						ans1 += abs(now1 - next);
						now1 = next;
						used[idx] = true;
					}
				}
			}
			ans1 += abs(now1);


			// 左 -> 右 -> 左 ...
			long ans2 = 0;
			long now2 = 0;
			used = new boolean[n];
			for (int i = 0; i < n; i++) {
				// 左
				if (i % 2 == 1) {
					Seg s = ql2.poll();
					int idx = s.i;
					int next = s.l;
					if (now2 <= next || used[idx]) {
						continue;
					}
					ans2 += abs(next - now2);
					now2 = next;
					used[idx] = true;
				// 右
				} else {
					Seg s = qr2.poll();
					int idx = s.i;
					int next = s.r;
					if (next <= now2 || used[idx]) {
						continue;
					}
					ans2 += abs(now2 - next);
					now2 = next;
					used[idx] = true;
				}
			}
			ans2 = abs(now2);

			out.println(Math.max(ans1, ans2));


		}
	}
	static class Seg {
		int i, l, r;

		public Seg(int i, int l, int r) {
			super();
			this.i = i;
			this.l = l;
			this.r = r;
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
