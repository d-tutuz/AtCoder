package track;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.StringTokenizer;

public class App {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = new FileInputStream(new File(args[0]));
		OutputStream outputStream = new FileOutputStream(new File("answer.txt"));
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static int INF = 1 << 30;

	static class TaskX {

		public void solve(int testNumber, InputReader in, PrintWriter out) {

			char[] s = in.nextString().toCharArray();
			int n = s.length;

			int ans = INF;
			Map<Character, Integer> map = new HashMap<>();
			for (int l = 0, r = 0; l < n; l++) {
				while (r < n && !check(map)) {
					map.merge(s[r], 1, Integer::sum);
					r++;
				}
				if (check(map)) {
					ans = min(ans, map.values().stream().mapToInt(Number::intValue).sum());
				}

				map.merge(s[l], -1, Integer::sum);
			}
			out.println(ans);
		}

		boolean check(Map<Character, Integer> map) {
			int count = 0;
			for (char c : map.keySet()) {
				if (Character.isAlphabetic(c) && map.get(c) > 0) count++;
			}
			return count >= 5 &&
					((map.containsKey('$') && map.get('$') > 0)
							|| (map.containsKey('%') && map.get('%') > 0)
							|| (map.containsKey('@') && map.get('@') > 0));
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

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}
	}

}
