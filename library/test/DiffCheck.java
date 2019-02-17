package test;

import java.util.Arrays;
import java.util.Random;

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
	static int n, k;
	static long[] b;

	// input init
	static void init() {
		Random rnd = new Random();
		//////////////////////////////////////
		// ここは input に合わせて適宜変更する
		//////////////////////////////////////
		n = 100; k = 25;
		b = new long[n];
		for (int i = 0; i < n; i++) {
			b[i] = rnd.nextLong() % 10000;
		}
	}

	// main
	public static void main(String[] args) {

		// n - loop
		boolean loop = true;
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

		if (correct.solve() != test.solve()) {
			// input
			System.out.println("Input:");
			System.out.printf("%d %d\n", n, k);
			printArrayLine(b);


			//////////////////////////////////////
			// ここは基本変える必要はない(解は1つの出力のみであるから)
			//////////////////////////////////////
			System.out.println("----------------------------------------");
			// diff
			System.out.println("Expected output:");
			System.out.printf("%s\n", correct.solve());

			System.out.println("Execution result:");
			System.out.printf("%s\n", test.solve());
			System.out.println("========================================");
		}
	}

	/**
	 * 検証したいロジック
	 * ここに 実際の実装 を記載する
	 * */
	static class TestAnswer {
		long solve() {

			if (k == 1) {
				long ret = 0;
				for (long l : b) if (l >= 0) ret += l;
				return ret;
			}

			SegmentTree seg = new SegmentTree(n, -LINF);

			// [0, i] の最大値
			long[] dp = new long[n];
			Arrays.fill(dp, -LINF);
			dp[0] = b[0];
			seg.update(0, dp[0]);

			for (int i = 1; i < n; i++) {

				// [0, i] 全体を 0 にする
				if (i - k + 1 >= 0) dp[i] = 0;

				// [j, i] を 0 にする
				if (i - k >= 0) dp[i] = Math.max(dp[i], seg.query(0, i - k + 1));

				// b[i] を足す
				dp[i] = Math.max(dp[i], dp[i-1] + b[i]);

				seg.update(i, dp[i]);
			}

			return Math.max(0, dp[n-1]);
		}

		class SegmentTree extends AbstractSegmentTree<Long> {

			public SegmentTree(int n, Long initial_value) {
				super(n, initial_value);
			}

			@Override
			Long merge(Long x, Long y) {
				return Math.max(x, y);
			}

		}

		@SuppressWarnings("unchecked")
		abstract class AbstractSegmentTree<T> {
			int size;
			T[] dat;
			T INITIAL_VALUE;

			abstract T merge(T x, T y);

			public AbstractSegmentTree(int n, T initial_value) {
				size = 1;
				this.INITIAL_VALUE = initial_value;
				while (size < n) {
					size *= 2;
				}
				dat = (T[])new Object[size * 2];
				for (int i = 0; i < size * 2; i++) {
					dat[i] = INITIAL_VALUE;
				}
			}

			void update(int k, T a) {
				k += size;
				dat[k] = a;
				while (k > 0) {
					k /= 2;
					dat[k] = merge(dat[2 * k], dat[2 * k + 1]);
				}
			}

			private T query(int a, int b, int k, int l, int r) {
				if (r <= a || b <= l) return INITIAL_VALUE;

				if (a <= l && r <= b) {
					return dat[k];
				} else {
					T vl = query(a, b, 2 * k, l, (l + r) / 2);
					T vr = query(a, b, 2 * k + 1, (l + r) / 2, r);
					return merge(vl, vr);
				}
			}

			T query(int a, int b) {
				return query(a, b, 1, 0, size);
			}
		}
	}

	/**
	 * 正しい解が出力されるロジック
	 * ここに ナイーブ実装 or AC実装 を記載する
	 * */
	static class CorrectAnswer {
		long solve() {

			long[] bb = new long[n+1];
			for (int i = 1; i < n+1; i++) {
				bb[i] = b[i-1];
			}
			long[] dp = new long[n+1];
			long[] max = new long[n+1];

			Arrays.fill(dp, Long.MIN_VALUE/3);
			dp[0] = 0;

			for (int i = 1; i < n+1; i++) {
				if (i-k >= 0) {
					dp[i] = Math.max(dp[i-1] + bb[i], max[i-k]);
				} else {
					dp[i] = dp[i-1] + bb[i];
				}
				max[i] = Math.max(max[i-1], dp[i]);
			}

			return dp[n];
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
