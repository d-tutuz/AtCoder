package test;

import java.util.Random;

public class RandomGenerator {

	public RandomGenerator() {
	}

	static Random rnd = new Random();

	// 英小文字のみ
	// n は文字数
	static String makeString(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append((char)('a' + rnd.nextInt(26)));
		}
		return sb.toString();
	}

	// 2次元配列の文字列(char[][])
	static char[][] makeCharArrays(int row, int len) {
		char[][] ret = new char[row][len];
		for (int i = 0; i < row; i++) {
			ret[i] = makeString(len).toCharArray();
		}
		return ret;
	}

	//
	static int[] makeIntArray(int n, int max) {
		int[] ret = new int[n];
		for (int i = 0; i < n; i++) {
			ret[i] = rnd.nextInt(max);
		}
		return ret;
	}
}
