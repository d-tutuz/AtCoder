package test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;
import java.util.stream.Stream;

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
	static int n;
	static String[] AA, BB;

	// input init
	static void init() {
		//////////////////////////////////////
		// ここは input に合わせて適宜変更する
		//////////////////////////////////////
		Random rnd = new Random();
		n = 20;
		AA = new String[n];
		BB = new String[n];
		for (int i = 0; i < n; i++) {

			String str = "";
			for (int j = 0; j < rnd.nextInt(1000) % 10 + 2; j++) {
				str += String.valueOf((char)(rnd.nextInt(26) + 'a'));
			}
			AA[i] = str;

			str = "";
			for (int j = 0; j < rnd.nextInt(1000) % 10 + 2; j++) {
				str += String.valueOf((char)(rnd.nextInt(26) + 'a'));
			}
			BB[i] = str;
		}
	}

	/**
	 * 解の型を合わせる必要がある
	 * */
	static void print(String correctAnswer, String testAnswer) {
		// input print
		System.out.println("Input:");
		System.out.printf("%d\n", n);
		for (int i = 0; i < n; i++) {
			System.out.printf("%s %s\n", AA[i], BB[i]);
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
		int loopCount = 100000;

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

		String correctAnswer = correct.solve();
		String testAnswer = test.solve();


		if (!correctAnswer.equals(testAnswer)) {
			print(correctAnswer, testAnswer);
		}
	}

	/**
	 * 検証したいロジック
	 * ここに 実際の実装 を記載する
	 * */
	static class TestAnswer {
		String solve() {
			TreeSet<Character>[] g = new TreeSet[26];
			g = Stream.generate(TreeSet::new).limit(26).toArray(TreeSet[]::new);

			int[] cnt = new int[26];
			boolean[] tar = new boolean[26];
			boolean[] add = new boolean[26262];

			for (int k = 0; k < n; k++) {
				char[] a = AA[k].toCharArray();
				char[] b = BB[k].toCharArray();
				boolean change = false;
				for (int i = 0; i < Math.min(a.length, b.length); i++) {
					if (a[i] == b[i]) continue;

					g[c2i(a[i])].add(b[i]);
					if (!add[c2i(a[i]) * 100 + c2i(b[i])]) {
						cnt[c2i(b[i])]++;
					}
					add[c2i(a[i]) * 100 + c2i(b[i])] = true;

					tar[c2i(a[i])] = true;
					tar[c2i(b[i])] = true;

					change = true;
					break;
				}

				if (!change && a.length >= b.length) {
					return "-1";
				}
			}

			PriorityQueue<Character> q = new PriorityQueue<>();
			for (int i = 0; i < 26; i++) {
				if (tar[i] && cnt[i] == 0) {
					q.add(i2c(i));
				}
			}

			// tar 以外は通常の辞書順最小で構築
			StringBuilder sb = new StringBuilder();
			for (char i = 'a'; i <= 'z'; i++) {
				if (!tar[c2i(i)]) {
					sb.append(i);
					continue;
				}

				while (!q.isEmpty()) {
					char cur = q.remove();
					if (cur > i) {
						q.add(cur);
						break;
					}
					sb.append(cur);
					for (char nex : g[c2i(cur)]) {
						if (--cnt[c2i(nex)] == 0) {
							q.add(nex);
						}
					}
				}
			}

			return sb.toString().length() != 26 ? "-1" : sb.toString();
		}

		char i2c(int i) {
			return (char)(i + 'a');
		}

		int c2i(char c) {
			return c - 'a';
		}
	}

	/**
	 * 正しい解が出力されるロジック
	 * ここに ナイーブ実装 or AC実装 を記載する
	 * */
	static class CorrectAnswer {
		String solve() {
			String[] A = new String[n];
			String[] B = new String[n];
			ArrayList<Integer>[] g = new ArrayList[26];
			ArrayList<Integer>[] revg = new ArrayList[26];
			for (int i = 0; i < 26; ++i) {
				g[i] = new ArrayList<>();
				revg[i] = new ArrayList<>();
			}
			int[] outdeg = new int[26];
			boolean[] valid = new boolean[26];
			out: for (int i = 0; i < n; ++i) {
				A[i] = AA[i];
				B[i] = BB[i];
				if (A[i].equals(B[i])) {
					return "-1";
				}
				int j = 0;
				while (A[i].charAt(j) == B[i].charAt(j)) {
					++j;
					if (j == Math.min(A[i].length(), B[i].length())) {
						if (A[i].length() < B[i].length()) {
							continue out;
						} else {
							return "-1";
						}
					}
				}
				int a = (int) (A[i].charAt(j) - 'a');
				int b = (int) (B[i].charAt(j) - 'a');
				g[b].add(a);
				revg[a].add(b);
				valid[a] = true;
				valid[b] = true;
				++outdeg[b];
			}
			int[] col = new int[26];
			int cols = scc(g, col);
			if (cols != 26) {
				return "-1";
			}
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for (int i = 0; i < 26; ++i) {
				if (outdeg[i] == 0) {
					pq.add(i);
				}
			}
			String ans = "";
			while (!pq.isEmpty()) {
				int v = pq.poll();
				ans += (char) (v + 'a');
				valid[v] = false;
				for (int u : revg[v]) {
					--outdeg[u];
					if (outdeg[u] == 0) {
						pq.add(u);
					}
				}
			}
			return ans;
		}

		class State {
			int i, parent, j;

			public State(int i_, int parent_, int j_) {
				i = i_;
				parent = parent_;
				j = j_;
			}
		}

		int scc(ArrayList<Integer>[] g, int[] col) {
			int n = g.length;
			Arrays.fill(col, -1);
			int[] low = new int[n];
			int[] ord = new int[n];
			Arrays.fill(low, -1);
			Arrays.fill(ord, -1);
			boolean[] marked = new boolean[n];
			int order = 0;
			int cols = 0;
			ArrayDeque<State> stk = new ArrayDeque<>();
			ArrayDeque<Integer> pnd = new ArrayDeque<>();
			for (int ii = 0; ii < n; ++ii) {
				if (ord[ii] == -1) {
					stk.add(new State(ii, -1, 0));
				}
				while (!stk.isEmpty()) {
					State s = stk.pollFirst();
					if (ord[s.i] == -1) {
						low[s.i] = (ord[s.i] = order++);
						pnd.addFirst(s.i);
					}
					if (s.j > 0 && s.parent != g[s.i].get(s.j - 1) && !marked[g[s.i].get(s.j - 1)]) {
						low[s.i] = Math.min(low[s.i], low[g[s.i].get(s.j - 1)]);
					}
					if (s.j == g[s.i].size() && low[s.i] == ord[s.i]) {
						while (true) {
							int v = pnd.pollFirst();
							col[v] = cols;
							marked[v] = true;
							if (v == s.i) {
								break;
							}
						}
						++cols;
						continue;
					}
					if (s.j == g[s.i].size()) {
						continue;
					}
					int v = g[s.i].get(s.j);
					stk.addFirst(new State(s.i, s.parent, s.j + 1));
					if (ord[v] == -1) {
						stk.addFirst(new State(v, s.i, 0));
					} else if (!marked[v]) {
						low[s.i] = Math.min(low[s.i], low[v]);
					}
				}
			}
			return cols;
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
