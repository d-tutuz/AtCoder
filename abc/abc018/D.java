package abc018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

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
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int N = in.nextInt();
			int M = in.nextInt();
			int P = in.nextInt();
			int Q = in.nextInt();
			int R = in.nextInt();

			Map<Integer, Integer> f = new HashMap<>();

			T[] tt = new T[R];
			int sum = 0;
			for (int i = 0; i < R; i++) {
				int x = in.nextInt()-1;
				int y = in.nextInt()-1;
				int z = in.nextInt();
				f.merge(x, z, Integer::sum);
				tt[i] = new T(x, y, z);
			}

			List<Map.Entry<Integer, Integer>> flist = new ArrayList<Map.Entry<Integer, Integer>>(f.entrySet());
			Collections.sort(flist, new Comparator<Map.Entry<Integer, Integer>>() {

						@Override
						public int compare(Entry<Integer, Integer> entry1, Entry<Integer, Integer> entry2) {
							return ((Integer) entry2.getValue()).compareTo((Integer) entry1.getValue());
						}
					});

			Set<Integer> set = new TreeSet<>();
			for (int i = 0; i < P; i++) {
//				System.out.println(flist.get(i).getKey() +":"+ flist.get(i).getValue());
				set.add(flist.get(i).getKey());
			}

			Map<Integer, Integer> t = new HashMap<>();

			for (T tmp : tt) {
				if (set.contains(tmp.x)) {
					t.merge(tmp.y, tmp.z, Integer::sum);
				}
			}

			List<Map.Entry<Integer, Integer>> tlist = new ArrayList<Map.Entry<Integer, Integer>>(t.entrySet());
			Collections.sort(tlist, new Comparator<Map.Entry<Integer, Integer>>() {

						@Override
						public int compare(Entry<Integer, Integer> entry1, Entry<Integer, Integer> entry2) {
							return ((Integer) entry2.getValue()).compareTo((Integer) entry1.getValue());
						}
					});

			long ans = 0;
			for (int i = 0; i < Q; i++) {
//				System.out.println(tlist.get(i).getKey() +":"+ tlist.get(i).getValue());
				ans += tlist.get(i).getValue();
			}

			out.println(ans);


		}

		class P implements Comparable<P> {
			int n, w;

			public P(int n, int w) {
				super();
				this.n = n;
				this.w = w;
			}

			@Override
			public int compareTo(P o) {
				return -(this.w - o.w);
			}

			@Override
			public String toString(){
				return n + ":" + w;
			}

		}

		class T {
			int x, y, z;

			public T(int x, int y, int z) {
				super();
				this.x = x;
				this.y = y;
				this.z = z;
			}

			@Override
			public String toString(){
				return x + ":" + y + ":" + z;
			}
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

		public int[] nextIntArrayDec(int n) {
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt() - 1;
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

		public long[] nextLongArrayDec(int n) {
			long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong() - 1;
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
