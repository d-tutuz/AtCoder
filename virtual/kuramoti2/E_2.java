package kuramoti2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Queue;

/**
 * Built using CHelper plug-in Actual solution is at the top
 *
 * @author ilyakor
 */
public class E_2 {

	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		OutputWriter out = new OutputWriter(outputStream);
		TaskB solver = new TaskB();
		solver.solve(1, in, out);
		out.close();
	}

	static class TaskB {

		ArrayList<Integer>[] g;
		boolean[] u;

		public void solve(int testNumber, InputReader in, OutputWriter out) {
			int n = in.nextInt();
			int m = in.nextInt();
			g = new ArrayList[n];
			for (int i = 0; i < n; ++i) {
				g[i] = new ArrayList<>();
			}
			for (int i = 0; i < m; ++i) {
				int x = in.nextInt() - 1;
				int y = in.nextInt() - 1;
				g[x].add(y);
				g[y].add(x);
			}
			u = new boolean[n];
			ArrayList<Integer> head = new ArrayList<>();
			ArrayList<Integer> tail = new ArrayList<>();
			int s = 0;
			int t = g[s].get(0);
			u[s] = true;
			u[t] = true;
			head.add(s);
			tail.add(t);

			Queue<Integer> q = new ArrayDeque<>();
			q.add(s);
			while (!q.isEmpty()) {
				int x = q.poll();
				for (int y : g[x]) {
					if (u[y]) {
						continue;
					}
					u[y] = true;
					head.add(y);
					q.add(y);
					break;
				}
			}

			q = new ArrayDeque<>();
			q.add(t);
			while (!q.isEmpty()) {
				int x = q.poll();
				for (int y : g[x]) {
					if (u[y]) {
						continue;
					}
					u[y] = true;
					tail.add(y);
					q.add(y);
					break;
				}
			}

			Collections.reverse(head);
			head.addAll(tail);
			out.printLine(head.size());
			for (int x : head) {
				out.print((x + 1) + " ");
			}
			out.printLine();
		}

	}

	static class InputReader {

		private InputStream stream;
		private byte[] buffer = new byte[10000];
		private int cur;
		private int count;

		public InputReader(InputStream stream) {
			this.stream = stream;
		}

		public static boolean isSpace(int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public int read() {
			if (count == -1) {
				throw new InputMismatchException();
			}
			try {
				if (cur >= count) {
					cur = 0;
					count = stream.read(buffer);
					if (count <= 0) {
						return -1;
					}
				}
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			return buffer[cur++];
		}

		public int readSkipSpace() {
			int c;
			do {
				c = read();
			} while (isSpace(c));
			return c;
		}

		public int nextInt() {
			int sgn = 1;
			int c = readSkipSpace();
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') {
					throw new InputMismatchException();
				}
				res = res * 10 + c - '0';
				c = read();
			} while (!isSpace(c));
			res *= sgn;
			return res;
		}

	}

	static class OutputWriter {

		private final PrintWriter writer;

		public OutputWriter(OutputStream outputStream) {
			writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					outputStream)));
		}

		public OutputWriter(Writer writer) {
			this.writer = new PrintWriter(writer);
		}

		public void print(Object... objects) {
			for (int i = 0; i < objects.length; i++) {
				if (i != 0) {
					writer.print(' ');
				}
				writer.print(objects[i]);
			}
		}

		public void printLine(Object... objects) {
			print(objects);
			writer.println();
		}

		public void close() {
			writer.close();
		}

	}
}
