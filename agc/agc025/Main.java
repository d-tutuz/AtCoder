package agc025;

//package agc.agc025;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static class Segment {
		int idx;
		int L;
		int R;

		Segment(int i, int l, int r) {
			idx = i;
			L = l;
			R = r;
		}
	}

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int n = in.nextInt();
		List<Segment> segments = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			segments.add(new Segment(i, in.nextInt(), in.nextInt()));
		}

		Queue<Segment> lmaxQ = new PriorityQueue<>((o1, o2) -> o2.L - o1.L);
		Queue<Segment> rmaxQ = new PriorityQueue<>((o1, o2) -> o1.R - o2.R);

		long best = 0;
		for (int w = 0; w <= 1; w++) {
			boolean[] used = new boolean[n];
			int tt = w ^ 1;
			long total = 0;
			int now = 0;

			lmaxQ.clear();
			rmaxQ.clear();
			lmaxQ.addAll(segments);
			rmaxQ.addAll(segments);

			for (int c = 0; c < n; c++) {
				tt ^= 1;
				if (tt == 0) {
					while (true) {
						Segment seg = lmaxQ.poll();
						if (used[seg.idx]) {
							continue;
						}
						int to = move(now, seg);
						total += Math.abs(to - now);
						now = to;
						used[seg.idx] = true;
						break;
					}
				} else {
					while (true) {
						Segment seg = rmaxQ.poll();
						if (used[seg.idx]) {
							continue;
						}
						int to = move(now, seg);
						total += Math.abs(to - now);
						now = to;
						used[seg.idx] = true;
						break;
					}
				}
			}
			total += Math.abs(now);
			best = Math.max(best, total);
		}

		out.println(best);
		out.flush();
	}

	private static int move(int now, Segment seg) {
		if (now < seg.L) {
			return seg.L;
		} else if (seg.R < now) {
			return seg.R;
		}
		return now;
	}

	public static void debug(Object... o) {
		System.err.println(Arrays.deepToString(o));
	}

	public static class InputReader {
		private static final int BUFFER_LENGTH = 1 << 12;
		private InputStream stream;
		private byte[] buf = new byte[BUFFER_LENGTH];
		private int curChar;
		private int numChars;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		private int next() {
			if (numChars == -1) {
				throw new InputMismatchException();
			}
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public char nextChar() {
			return (char) skipWhileSpace();
		}

		public String nextToken() {
			int c = skipWhileSpace();
			StringBuilder res = new StringBuilder();
			do {
				res.append((char) c);
				c = next();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public int nextInt() {
			return (int) nextLong();
		}

		public long nextLong() {
			int c = skipWhileSpace();
			long sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = next();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res *= 10;
				res += c - '0';
				c = next();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public double nextDouble() {
			return Double.valueOf(nextToken());
		}

		int skipWhileSpace() {
			int c = next();
			while (isSpaceChar(c)) {
				c = next();
			}
			return c;
		}

		boolean isSpaceChar(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
	}
}
