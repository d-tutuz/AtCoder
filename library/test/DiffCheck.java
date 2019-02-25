package test;

import static java.lang.Math.*;

import java.util.Arrays;
import java.util.Random;

// TODO: inputのクラスを定義して、それに対応するように結果の出力などを実装できるとbetter
public class DiffCheck {

	// tmplate
	static int INF = 1 << 30;
	static long LINF = 1L << 55;
	static int MOD = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };
	static int[] mh8 = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] mw8 = { -1, 0, 1, -1, 1, -1, 0, 1 };

	// input
	//////////////////////////////////////
	// ここは適宜変更する
	//////////////////////////////////////
	static int n, a, b, c;
	static int[] l;

	// input init
	static void init() {
		//////////////////////////////////////
		// ここは input に合わせて適宜変更する
		//////////////////////////////////////
		Random rnd = new Random();
		n = 8;
		a = rnd.nextInt(1001);
		b = rnd.nextInt(1001);
		c = rnd.nextInt(1001);
		l = new int[n];
		for (int i = 0; i < n; i++) {
			l[i] = rnd.nextInt(1001);
		}
	}

	/**
	 * 解の型を合わせる必要がある
	 * */
	static void print(long correctAnswer, long testAnswer) {
		// input print
		System.out.println("Input:");
		System.out.printf("%d %d %d %d\n", n, a, b, c);
		for (int i = 0; i < n; i++) {
			System.out.printf("%d\n", l[i]);
		}

		System.out.println("----------------------------------------");
		// diff
		System.out.println("Expected output:");
		System.out.printf("%s\n", correctAnswer);

		System.out.println("Execution result:");
		System.out.printf("%s\n", testAnswer);
		System.out.println("========================================");
	}

	// main
	public static void main(String[] args) {

		// n - loop
		int loopCount = 100;

		if (loopCount > 0) {
			while (loopCount-- > 0) {
				execute();
			}
		}

		execute();
	}

	static void execute() {

		init();

		//////////////////////////////////////
		// ここは Input の形に合わせて整形する
		//////////////////////////////////////

		CorrectAnswer correct = new CorrectAnswer();
		TestAnswer test = new TestAnswer();

		long correctAnswer = correct.solve();
		long testAnswer = test.solve();


		if (correctAnswer != testAnswer) {
			print(correctAnswer, testAnswer);
		}
	}

	/**
	 * 検証したいロジック
	 * ここに 実際の実装 を記載する
	 * */
	static class TestAnswer {
		long solve() {

			int ans = INF;
			for (int i = 0; i < (int)Math.pow(4, n); i++) {
				int digit = i;
				int ca = 0, cb = 0, cc = 0;
				int tmp = 0;

				int j = 0;
				int check = 0;
				for (int k = 0; k < n; k++) {
					int p = digit % 4;

					if (p == 0) {
						if (ca > 0) tmp += 10;
						ca += l[n-1-j];
						check |= 1 << 0;
					} else if (p == 1) {
						if (cb > 0) tmp += 10;
						cb += l[n-1-j];
						check |= 1 << 1;
					} else if (p == 2) {
						if (cc > 0) tmp += 10;
						cc += l[n-1-j];
						check |= 1 << 2;
					} else if (p == 3) {
						// nothing
					}

					digit /= 4;
					j++;
				}

				if (check != 7) continue;
				tmp += abs(a - ca) + abs(b - cb) + abs(c - cc);

				ans = min(tmp, ans);
			}

			return ans;


		}
	}

	/**
	 * 正しい解が出力されるロジック
	 * ここに ナイーブ実装 or AC実装 を記載する
	 * */
	static class CorrectAnswer {
		long solve() {

			Arrays.sort(l);

			long ans = INF;
			do {
				for (int i = 1; i < n; i++) {
					for (int j = i+1; j < n; j++) {
						for (int k = j+1; k <= n; k++) {

							long cost = 0;
							cost += calc(0, i, l, a);
							cost += calc(i, j, l, b);
							cost += calc(j, k, l, c);

							ans = Math.min(ans, cost);
						}
					}
				}
			} while (Permutation.next(l));

			return ans;
		}

		long calc(int l, int r, int[] a, int tar) {
			int ret = 0;
			int tmp = 0, cnt = 0;
			for (int i = l; i < r; i++) {
				tmp += a[i];
				cnt++;
			}
			ret += (cnt - 1) * 10;
			ret += Math.abs(tar - tmp);
			return ret;
		}
	}


	static class Permutation {

		public static boolean next(int[] a) {
			int n = a.length;

			int i = n - 1;
			while (i > 0 && a[i - 1] >= a[i])
				i--;
			if (i <= 0)
				return false;

			int j = n - 1;
			while (a[j] <= a[i - 1])
				j--;
			swap(a, i - 1, j);

			int k = n - 1;
			while (i < k)
				swap(a, i++, k--);

			return true;
		}

		private static void swap(int[] a, int i, int j) {
			int tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
		}
	}

	static void printArrayLine(long[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				System.out.print(a[i]);
			} else {
				System.out.print(" " + a[i]);
			}
		}
		System.out.print("\n");
	}

	static void printArrayLine(int[] a) {
		int n = a.length;
		for (int i = 0; i < n; i++) {
			if (i == 0) {
				System.out.print(a[i]);
			} else {
				System.out.print(" " + a[i]);
			}
		}
		System.out.print("\n");
	}

}
