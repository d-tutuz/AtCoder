package test;

import java.util.Random;

// TODO: inputのクラスを定義して、それに対応するように結果の出力などを実装できるとbetter
public class DiffCheckTemplate {

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
	static int n, m, k;
	static long[] a, b;

	// input init
	static void init() {
		//////////////////////////////////////
		// ここは input に合わせて適宜変更する
		//////////////////////////////////////
		Random rnd = new Random();
		n = 100; m = 1000; k = 50;
		a = new long[n];
		b = new long[m];
		for (int i = 0; i < n; i++) {
			a[i] = rnd.nextInt(100000);
		}
		for (int i = 0; i < m; i++) {
			b[i] = rnd.nextInt(1000000);
		}
	}

	/**
	 * 解の型を合わせる必要がある
	 * */
	static void print(long correctAnswer, long testAnswer) {
		// input print
		System.out.println("Input:");
		System.out.printf("%d %d %d\n", n, m, k);
		printArrayLine(a);
		printArrayLine(b);


		System.out.println("----------------------------------------");
		// diff
		System.out.println("Expected output:");
		System.out.printf("%d\n", correctAnswer);

		System.out.println("Execution result:");
		System.out.printf("%d\n", testAnswer);
		System.out.println("========================================");
	}

	// main
	public static void main(String[] args) {

		// n - loop
		boolean loop = false;
		int loopCount = 1000;

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
			return 0;
		}
	}

	/**
	 * 正しい解が出力されるロジック
	 * ここに ナイーブ実装 or AC実装 を記載する
	 * */
	static class CorrectAnswer {
		long solve() {
			return 0;
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
