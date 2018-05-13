package agc005;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.TreeSet;

/**
 * Built using CHelper plug-in Actual solution is at the top
 *
 * @author ogiekako
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		MyScanner in = new MyScanner(inputStream);
		MyPrintWriter out = new MyPrintWriter(outputStream);
		TaskB solver = new TaskB();
		solver.solve(1, in, out);
		out.close();
	}
}

class TaskB {
	public void solve(int testNumber, MyScanner in, MyPrintWriter out) {
		int n = in.nextInt();
		int[] p = new int[n];
		for (int i = 0; i < n; i++) {
			p[in.nextInt() - 1] = i;
		}
		TreeSet<Integer> seen = new TreeSet<>();
		seen.add(-1);
		seen.add(n);
		long res = 0;
		for (int i = 0; i < n; i++) {
			res += (long) (p[i] - seen.lower(p[i])) * (seen.higher(p[i]) - p[i]) * (i + 1);
			seen.add(p[i]);
		}
		out.println(res);
	}
}

class MyScanner {
	private final InputStream in;

	public MyScanner(InputStream in) {
		this.in = in;
	}

	private static final int BUFSIZE = 65536;
	int bufLen;
	int bufPtr;
	byte[] buf = new byte[BUFSIZE];

	public int read() {
		if (bufLen == -1)
			throw new InputMismatchException();
		if (bufPtr >= bufLen) {
			bufPtr = 0;
			try {
				bufLen = in.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (bufLen <= 0)
				return -1;
		}
		return buf[bufPtr++];
	}

	public int nextInt() {
		int c = read();
		if (c == -1)
			throw new NoSuchElementException();
		while (c != '-' && (c < '0' || '9' < c)) {
			c = read();
			if (c == -1)
				throw new NoSuchElementException();
		}
		if (c == '-')
			return -nextInt();
		int res = 0;
		do {
			res *= 10;
			res += c - '0';
			c = read();
		} while ('0' <= c && c <= '9');
		return res;
	}

}

class MyPrintWriter {
	PrintWriter out;

	public MyPrintWriter(OutputStream outputStream) {
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				outputStream)));
	}

	public void println(Object... os) {
		if (os.length == 0) {
			out.println();
			return;
		}
		for (int i = 0; i < os.length - 1; i++) {
			out.print(os[i]);
			out.print(' ');
		}
		out.println(os[os.length - 1]);
	}

	public void close() {
		out.close();
	}

}
