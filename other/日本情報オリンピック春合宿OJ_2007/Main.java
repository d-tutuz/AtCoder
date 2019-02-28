package 日本情報オリンピック春合宿OJ_2007;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

	public static void main(String[] $) throws IOException {
		new Main().solve();
	}

	static FastScanner in = new FastScanner(System.in);
	int m, n;
	int INF = 1 << 28;
	public void solve() throws IOException {

		m = in.nextInt(); n = in.nextInt();
		int a = in.nextInt(), b = in.nextInt();

		int[] sum = new int[f(n, m)];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sum[f(i, j)] = in.nextInt();
				if (sum[f(i, j)] < 0) sum[f(i, j)] = INF;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i > 0) sum[f(i, j)] += sum[f(i-1, j)];
				if (j > 0) sum[f(i, j)] += sum[f(i, j-1)];
				if (i > 0 && j > 0) sum[f(i, j)] -= sum[f(i-1, j-1)];
			}
		}

		int ans = INF;
		for (int x1 = 0; x1+b-1 < n; x1++) {
			for (int y1 = 0; y1+a-1 < m; y1++) {
				int x2 = x1 + b - 1, y2 = y1 + a - 1;
				int res = sum[f(x2, y2)];
				if (x1 > 0) res -= sum[f(x1-1, y2)];
				if (y1 > 0) res -= sum[f(x2, y1-1)];
				if (x1 > 0 && y1 > 0) res += sum[f(x1-1, y1-1)];
				if (res < 0) res = INF;
				ans = Math.min(ans, res);
			}
		}

		System.out.println(ans);
	}

	int f(int i, int j) {
		return i * m + j;
	}
}

class FastScanner {
	private final BufferedInputStream in;
	private static final int bufSize = 1 << 16;
	private final byte buf[] = new byte[bufSize];
	private int i = bufSize;
	private int k = bufSize;
	private final StringBuilder str = new StringBuilder();

	FastScanner(InputStream in) {
		this.in = new BufferedInputStream(in, bufSize);
	}

	int nextInt() throws IOException {
		return (int) nextLong();
	}

	long nextLong() throws IOException {
		int c;
		long x = 0;
		boolean sign = true;
		while ((c = nextChar()) <= 32)
			;
		if (c == '-') {
			sign = false;
			c = nextChar();
		}
		if (c == '+') {
			c = nextChar();
		}
		while (c >= '0') {
			x = x * 10 + (c - '0');
			c = nextChar();
		}
		return sign ? x : -x;
	}

	int nextChar() throws IOException {
		if (i == k) {
			k = in.read(buf, 0, bufSize);
			i = 0;
		}
		return i >= k ? -1 : buf[i++];
	}

	String next() throws IOException {
		int c;
		str.setLength(0);
		while ((c = nextChar()) <= 32 && c != -1)
			;
		if (c == -1) {
			return null;
		}
		while (c > 32) {
			str.append((char) c);
			c = nextChar();
		}
		return str.toString();
	}

	String nextLine() throws IOException {
		int c;
		str.setLength(0);
		while ((c = nextChar()) <= 32 && c != -1)
			;
		if (c == -1) {
			return null;
		}
		while (c != '\n') {
			str.append((char) c);
			c = nextChar();
		}
		return str.toString();
	}
}
