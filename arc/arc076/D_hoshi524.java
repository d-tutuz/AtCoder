package arc076;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class D_hoshi524 {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	void solve() {
		class Node {
			int x, y, i, xi, yi;

			Node(int x, int y, int i) {
				this.x = x;
				this.y = y;
				this.i = i;
			}

			int d(Node n) {
				return Math.min(Math.abs(x - n.x), Math.abs(y - n.y));
			}
		}
		int n = ni();
		Node l[] = new Node[n];
		Node xl[] = new Node[n];
		Node yl[] = new Node[n];
		for (int i = 0; i < n; ++i) {
			Node a = new Node(ni(), ni(), i);
			l[i] = a;
			xl[i] = a;
			yl[i] = a;
		}
		Arrays.sort(xl, (a, b) -> a.x - b.x);
		Arrays.sort(yl, (a, b) -> a.y - b.y);
		for (int i = 0; i < n; ++i) {
			xl[i].xi = i;
			yl[i].yi = i;
		}
		int mincost[] = new int[n];
		Arrays.fill(mincost, Integer.MAX_VALUE / 2);
		long v = 0;
		class State {
			int i, cost;

			State(int i, int cost) {
				this.i = i;
				this.cost = cost;
			}
		}
		PriorityQueue<State> queue = new PriorityQueue<>((a, b) -> a.cost
				- b.cost);
		queue.add(new State(0, 0));
		while (queue.size() > 0) {
			State s = queue.poll();
			if (s.cost > mincost[s.i])
				continue;
			Node x = l[s.i];
			mincost[s.i] = -1;
			v += s.cost;
			if (x.xi > 0) {
				Node t = xl[x.xi - 1];
				int c = x.d(t);
				if (mincost[t.i] > c) {
					mincost[t.i] = c;
					queue.add(new State(t.i, c));
				}
			}
			if (x.xi + 1 < n) {
				Node t = xl[x.xi + 1];
				int c = x.d(t);
				if (mincost[t.i] > c) {
					mincost[t.i] = c;
					queue.add(new State(t.i, c));
				}
			}
			if (x.yi > 0) {
				Node t = yl[x.yi - 1];
				int c = x.d(t);
				if (mincost[t.i] > c) {
					mincost[t.i] = c;
					queue.add(new State(t.i, c));
				}
			}
			if (x.yi + 1 < n) {
				Node t = yl[x.yi + 1];
				int c = x.d(t);
				if (mincost[t.i] > c) {
					mincost[t.i] = c;
					queue.add(new State(t.i, c));
				}
			}
		}
		out.println(v);
	}

	public static void main(String[] args) throws Exception {
		long S = System.currentTimeMillis();
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(
				INPUT.getBytes());
		out = new PrintWriter(System.out);

		new D_hoshi524().solve();
		out.flush();
		long G = System.currentTimeMillis();
		// tr(G - S + "ms");
	}

	private static boolean eof() {
		if (lenbuf == -1)
			return true;
		int lptr = ptrbuf;
		while (lptr < lenbuf)
			if (!isSpaceChar(inbuf[lptr++]))
				return false;

		try {
			is.mark(1000);
			while (true) {
				int b = is.read();
				if (b == -1) {
					is.reset();
					return true;
				} else if (!isSpaceChar(b)) {
					is.reset();
					return false;
				}
			}
		} catch (IOException e) {
			return true;
		}
	}

	private static byte[] inbuf = new byte[1024];
	static int lenbuf = 0, ptrbuf = 0;

	private static int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	private static boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	private static int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	private static double nd() {
		return Double.parseDouble(ns());
	}

	private static char nc() {
		return (char) skip();
	}

	private static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b !=
									// ' ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	private static char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	private static char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	private static int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	private static int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private static long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private static void tr(Object... o) {
		System.err.println(Arrays.deepToString(o));
	}
}
