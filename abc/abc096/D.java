package abc096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

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
	static int modP = 1000000007;

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			List<Integer> list = new ArrayList<>();
			Integer[] ps = getPrimes(55555);
			int psLen = ps.length;
			Integer[] checkPrimes = getPrimes(1000000);

			Set<Integer> set = new HashSet<>();
			for (int i = 0; i < checkPrimes.length; i++) {
				set.add(checkPrimes[i]);
			}

			list.add(ps[psLen-1]);
			list.add(ps[psLen-2]);
			list.add(ps[psLen-3]);
			list.add(ps[psLen-4]);
			list.add(ps[psLen-5]);

			int sum = 0;
			for (Integer integer : list) {
				sum += integer;
			}

			for (int i = psLen-6, count = 0; count < n-5; i--) {
				int p = ps[i];
				boolean isAdd = check(list, set, p);
				if (isAdd) {
					list.add(ps[i]);
					count++;
				}
			}

			for (int i = 0; i < list.size(); i++) {
				if (i > 0) {
					out.print(" "+list.get(i));
				} else {
					out.print(list.get(i));
				}
			}
			out.print("\n");

		}
	}
	public static boolean check(List<Integer> list, Set<Integer> set, int p) {

		int len = list.size();
		boolean isAdd = true;
		for (int a = 0; a < len; a++) {
			for (int b = a+1; b < len; b++) {
				for (int c = b+1; c < len; c++) {
					for (int d = c+1; d < len; d++) {
						int tmp = 0;
						tmp = tmp + list.get(a)+list.get(b)+list.get(c)+list.get(d)+p;
						if (!set.contains(tmp)) {
							continue;
						} else {
							return false;
						}
					}
				}
			}
		}

		return isAdd;

	}

	public static Integer[] getPrimes(int n) {
		boolean[] isPrime = new boolean[n + 1];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;
		for (int i = 2; i < isPrime.length; i++) {
			if (!isPrime[i])
				continue;
			for (int j = i + i; j < isPrime.length; j += i) {
				isPrime[j] = false;
			}
		}

		List<Integer> list = new ArrayList<>();
		for (int i = 1; i < isPrime.length; i++) {
			if (isPrime[i]) {
				list.add(i);
			}
		}
	return list.toArray(new Integer[list.size()]);
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
