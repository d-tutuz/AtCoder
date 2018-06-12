package abc011;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

public class Main {
	private static class Task {
		void solve(FastScanner in, PrintWriter out) {
			int N = in.nextInt();
			int D = in.nextInt();
			int X = Math.abs(in.nextInt());
			int Y = Math.abs(in.nextInt());

			if (X % D != 0 || Y % D != 0) {
				out.println(0.0);
				return;
			}
			int V = X / D;
			int H = Y / D;
			double ans = 0.0;
			for (int v = V; v <= N; v++) {
				int antiV = v - V;
				int rest = N - v - antiV - H;
				if (rest < 0)
					continue;
				if (rest % 2 != 0)
					continue;
				int h = rest / 2 + H;
				int antiH = rest / 2;
				assert h + v + antiV + antiH == N;

				out.println(h);
				out.println(antiH);
				out.println(v);
				out.println(antiV);
				out.println();

				double tmp = 1.0;
				long n = 1;
				for (int i = 1; i <= h; i++, n++) {
					tmp *= n;
					tmp /= i;
					tmp /= 4.0;
				}

				for (int i = 1; i <= antiH; i++, n++) {
					tmp *= n;
					tmp /= i;
					tmp /= 4.0;
				}

				for (int i = 1; i <= v; i++, n++) {
					tmp *= n;
					tmp /= i;
					tmp /= 4.0;
				}

				for (int i = 1; i <= antiV; i++, n++) {
					tmp *= n;
					tmp /= i;
					tmp /= 4.0;
				}
				assert n == N;
				ans += tmp;
			}
			out.println(ans);
		}
	}

	/**
	 * ここから下はテンプレートです。
	 */
	public static void main(String[] args) {
		OutputStream outputStream = System.out;
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(outputStream);
		Task solver = new Task();
		solver.solve(in, out);
		out.close();
	}

	private static class FastScanner {
		private final InputStream in = System.in;
		private final byte[] buffer = new byte[1024];
		private int ptr = 0;
		private int bufferLength = 0;

		private boolean hasNextByte() {
			if (ptr < bufferLength) {
				return true;
			} else {
				ptr = 0;
				try {
					bufferLength = in.read(buffer);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (bufferLength <= 0) {
					return false;
				}
			}
			return true;
		}

		private int readByte() {
			if (hasNextByte())
				return buffer[ptr++];
			else
				return -1;
		}

		private static boolean isPrintableChar(int c) {
			return 33 <= c && c <= 126;
		}

		private void skipUnprintable() {
			while (hasNextByte() && !isPrintableChar(buffer[ptr]))
				ptr++;
		}

		boolean hasNext() {
			skipUnprintable();
			return hasNextByte();
		}

		public String next() {
			if (!hasNext())
				throw new NoSuchElementException();
			StringBuilder sb = new StringBuilder();
			int b = readByte();
			while (isPrintableChar(b)) {
				sb.appendCodePoint(b);
				b = readByte();
			}
			return sb.toString();
		}

		long nextLong() {
			if (!hasNext())
				throw new NoSuchElementException();
			long n = 0;
			boolean minus = false;
			int b = readByte();
			if (b == '-') {
				minus = true;
				b = readByte();
			}
			if (b < '0' || '9' < b) {
				throw new NumberFormatException();
			}
			while (true) {
				if ('0' <= b && b <= '9') {
					n *= 10;
					n += b - '0';
				} else if (b == -1 || !isPrintableChar(b)) {
					return minus ? -n : n;
				} else {
					throw new NumberFormatException();
				}
				b = readByte();
			}
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		double[] nextDoubleArray(int n) {
			double[] array = new double[n];
			for (int i = 0; i < n; i++) {
				array[i] = nextDouble();
			}
			return array;
		}

		double[][] nextDoubleMap(int n, int m) {
			double[][] map = new double[n][];
			for (int i = 0; i < n; i++) {
				map[i] = nextDoubleArray(m);
			}
			return map;
		}

		public int nextInt() {
			return (int) nextLong();
		}

		public int[] nextIntArray(int n) {
			int[] array = new int[n];
			for (int i = 0; i < n; i++)
				array[i] = nextInt();
			return array;
		}

		public long[] nextLongArray(int n) {
			long[] array = new long[n];
			for (int i = 0; i < n; i++)
				array[i] = nextLong();
			return array;
		}

		public String[] nextStringArray(int n) {
			String[] array = new String[n];
			for (int i = 0; i < n; i++)
				array[i] = next();
			return array;
		}

		public char[][] nextCharMap(int n) {
			char[][] array = new char[n][];
			for (int i = 0; i < n; i++)
				array[i] = next().toCharArray();
			return array;
		}
	}
}
