package test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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
	static int n;
	static int[] x, y;

	// input init
	static void init() {
		//////////////////////////////////////
		// ここは input に合わせて適宜変更する
		//////////////////////////////////////
		Random rnd = new Random();
		n = 10000;
		x = new int[n+1]; y = new int[n+1];
		for (int i = 1; i < n+1; i++) {

			x[i] = rnd.nextInt(100) + x[i-1];
			y[i] = rnd.nextInt(100) + y[i-1];

		}
	}

	/**
	 * 解の型を合わせる必要がある
	 * */
	static void print(long correctAnswer, long testAnswer) {
		// input print
		System.out.println("Input:");
		System.out.printf("%d\n", n);
		for (int i = 1; i < n+1; i++) {
			System.out.printf("%d %d\n", x[i], y[i]);
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
		boolean loop = true;
		int loopCount = 100;

		if (loop) {
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

			long[] min = new long[n+1], max = new long[n+1];
			for (int i = 1; i < n+1; i++) {
				min[i] = Math.min(x[i], y[i]);
				max[i] = Math.max(x[i], y[i]);
			}

			long ans = 1;
			for (int i = 1; i < n+1; i++) {
				if (x[i-1] == x[i] && y[i-1] == y[i]) continue;

				if (x[i-1] > y[i-1] && x[i] < y[i]) {
					ans += min[i] - max[i-1] + 1;
					continue;
				}

				if (x[i-1] == y[i-1] && x[i] < y[i]) {
					ans += min[i] - max[i-1];
					continue;
				}

				if (x[i-1] < y[i-1] && x[i] > y[i]) {
					ans += min[i] - max[i-1] + 1;
					continue;
				}

				if (x[i-1] == y[i-1] && x[i] > y[i]) {
					ans += min[i] - max[i-1];
					continue;
				}

				if (x[i] == y[i]) {
					int d = x[i-1] == y[i-1] ? 0 : 1;
					ans += x[i] - max[i-1] + d;
					continue;
				}

				if ((x[i-1] < y[i-1] && x[i] < y[i]) || (x[i-1] > y[i-1] && x[i] > y[i])) {
					ans += Math.max(min[i] - max[i-1] + 1, 0);
				}
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

			long ans = 1;

			Set<Long> dx = new HashSet<>(), dy = new HashSet<>();

			for (int i = 1; i < n+1; i++) {
				dx.clear();
				dy.clear();
				int d = x[i-1] == y[i-1] ? 1 : 0;
				for (long j = x[i-1] + d; j <= x[i]; j++) {
					dx.add(j);
				}
				for (long j = y[i-1] + d; j <= y[i]; j++) {
					dy.add(j);
				}
				dx.retainAll(dy);
				ans += dx.size();
			}

			return ans;
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
