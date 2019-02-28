package 第5回ドワンゴからの挑戦状本選;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Built using CHelper plug-in Actual solution is at the top
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		MyInput in = new MyInput(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		ATaroVsJiro solver = new ATaroVsJiro();
		solver.solve(1, in, out);
		out.close();
	}

	static class ATaroVsJiro {
		@SuppressWarnings("unchecked")
		public void solve(int testNumber, MyInput in, PrintWriter out) {

			int n = in.nextInt(), m = in.nextInt();
			long k = in.nextLong();
			char[] s = in.nextChars();

			List<Integer>[] g = new ArrayList[n];
			g = Stream.generate(ArrayList::new).limit(n).toArray(List[]::new);

			for (int i = 0; i < m; i++) {
				int a = in.nextInt() - 1, b = in.nextInt() - 1;
				g[a].add(b);
				g[b].add(a);
			}

			boolean[][] wins = new boolean[n][3];
			for (int i = 0; i < n; i++) {
				if (s[i] == 'B') {
					for (int t : g[i]) {
						wins[t][1] = true;
					}
				}
			}

			for (int i = 0; i < n; i++) {
				boolean win = true;
				for (int t : g[i]) {
					if (s[t] == 'R')
						win = false;
				}
				if (win) {
					for (int t : g[i]) {
						wins[t][2] = true;
					}
				}
			}

			for (int i = 0; i < n; i++) {
				if (k % 2 == 0) {
					out.println(wins[i][2] ? "First" : "Second");
				} else {
					out.println(wins[i][1] ? "First" : "Second");
				}
			}
		}

	}

	static class MyInput {
		private final BufferedReader in;
		private static int pos;
		private static int readLen;
		private static final char[] buffer = new char[1024 * 8];
		private static char[] str = new char[500 * 8 * 2];
		private static boolean[] isDigit = new boolean[256];
		private static boolean[] isSpace = new boolean[256];
		private static boolean[] isLineSep = new boolean[256];

		static {
			for (int i = 0; i < 10; i++) {
				isDigit['0' + i] = true;
			}
			isDigit['-'] = true;
			isSpace[' '] = isSpace['\r'] = isSpace['\n'] = isSpace['\t'] = true;
			isLineSep['\r'] = isLineSep['\n'] = true;
		}

		public MyInput(InputStream is) {
			in = new BufferedReader(new InputStreamReader(is));
		}

		public int read() {
			if (pos >= readLen) {
				pos = 0;
				try {
					readLen = in.read(buffer);
				} catch (IOException e) {
					throw new RuntimeException();
				}
				if (readLen <= 0) {
					throw new MyInput.EndOfFileRuntimeException();
				}
			}
			return buffer[pos++];
		}

		public int nextInt() {
			int len = 0;
			str[len++] = nextChar();
			len = reads(len, isSpace);
			int i = 0;
			int ret = 0;
			if (str[0] == '-') {
				i = 1;
			}
			for (; i < len; i++)
				ret = ret * 10 + str[i] - '0';
			if (str[0] == '-') {
				ret = -ret;
			}
			return ret;
		}

		public long nextLong() {
			int len = 0;
			str[len++] = nextChar();
			len = reads(len, isSpace);
			int i = 0;
			long ret = 0;
			if (str[0] == '-') {
				i = 1;
			}
			for (; i < len; i++)
				ret = ret * 10 + str[i] - '0';
			if (str[0] == '-') {
				ret = -ret;
			}
			return ret;
		}

		public char nextChar() {
			while (true) {
				final int c = read();
				if (!isSpace[c]) {
					return (char) c;
				}
			}
		}

		public char[] nextChars() {
			int len = 0;
			str[len++] = nextChar();
			len = reads(len, isSpace);
			return Arrays.copyOf(str, len);
		}

		int reads(int len, boolean[] accept) {
			try {
				while (true) {
					final int c = read();
					if (accept[c]) {
						break;
					}
					if (str.length == len) {
						char[] rep = new char[str.length * 3 / 2];
						System.arraycopy(str, 0, rep, 0, str.length);
						str = rep;
					}
					str[len++] = (char) c;
				}
			} catch (MyInput.EndOfFileRuntimeException e) {
			}
			return len;
		}

		static class EndOfFileRuntimeException extends RuntimeException {
		}

	}
}