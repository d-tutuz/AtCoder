package review.arc004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class C_3 {

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

	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			String[] s = in.nextString().split("/");
			double x = Double.parseDouble(s[0]);
			double y = Double.parseDouble(s[1]);

			boolean isFound = false;

			for (int i = 0; i < 2; i++) {
				double n = (int)(2*x/y)+i;
				double m = n*(n+1)/2 - n*x/y;
				if (m == (int)m && 1 <= m && m <= n) {
					out.println((int)n +" "+ (int)m);
					isFound = true;
				}
			}

			if (!isFound) {
				out.println("Impossible");
			}

		}
	}

	public static long gcd(long a, long b) {
		return (b == 0) ? a : gcd(b, a % b);
	}

	public class Fraction {
		/**
		 *  分子
		 */
		private int numerator;

		/**
		 *  分母
		 */
		private int denominator;

		/**分数を作る
		 * @param numerator 分子
		 * @param denominator 分母
		 */
		public Fraction(int numerator, int denominator) {
			if (denominator == 0) {
				throw new RuntimeException("denominator is not permission 'zero'");
			}
			this.numerator = numerator;
			this.denominator = denominator;

			reduction();
		}

		/**
		 * 約分を行う
		 */
		private void reduction() {
			int gcdi = gcdi(numerator, denominator);
			numerator = numerator / gcdi;
			denominator = denominator / gcdi;
		}

		/**
		 * 引数の分数を加える
		 * @param fraction
		 * @return
		 */
		public void addition(Fraction fraction) {
			numerator = fraction.denominator * numerator + fraction.numerator * denominator;
			denominator *= fraction.denominator;

			reduction();
		}

		/**
		 * 引数の分数を引く
		 * @param fraction 引かれる数
		 * @return
		 */
		public void subtraction(Fraction fraction) {
			numerator = fraction.denominator * numerator - fraction.numerator * denominator;
			denominator *= fraction.denominator;

			reduction();
		}

		/**
		 * 引数の分数を掛ける
		 * @param fraction
		 * @return
		 */
		public void multiplication(Fraction fraction) {
			denominator *= fraction.denominator;
			numerator *= fraction.numerator;

			reduction();
		}

		/**
		 * 引数の分数を割る
		 * @param fraction 割られる数
		 * @return
		 */
		public void division(Fraction fraction) {
			denominator *= fraction.numerator;
			numerator *= fraction.denominator;

			reduction();
		}

		/**
		 * 分子を返す
		 * @return
		 */
		public int getNumerator() {
			return numerator;
		}

		/**
		 * 分母を返す
		 * @return
		 */
		public int getDenominator() {
			return denominator;
		}

		@Override
		public String toString() {
			//分母が1のときは分子だけ返す
			if (denominator == 1) {
				return Integer.toString(numerator);
			}
			return numerator + "/" + denominator;
		}

		/**
		 * 最大公約数を求める
		 * @param a
		 * @param b
		 * @return
		 */
		private int gcdi(int a, int b) {
			while (b > 0) {
				int c = a;
				a = b;
				b = c % b;
			}
			return a;
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
