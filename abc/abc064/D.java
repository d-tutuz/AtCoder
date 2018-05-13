package abc064;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class D {

	public static void main(String[] args) {

		StringBuffer sb = new StringBuffer();
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		char[] s = in.next().toCharArray();

		Deque<Integer> st = new ArrayDeque<>();
		Deque<Integer> st2 = new ArrayDeque<>();

		for (int i = 0; i < s.length; i++) {
			if (s[i] == '(') {
				st.push((int)s[i]);
				sb.append(s[i]);
			} else if (s[i] == ')') {
				if (!st.isEmpty()) {
					st.pop();
					sb.append(s[i]);
				} else {
					sb.append(s[i]);
					st2.push((int)s[i]);
				}

			}
		}

		while (!st.isEmpty()) {
			st.pop();
			sb.append(')');
		}

		while (!st2.isEmpty()) {
			st2.pop();
			sb.insert(0, '(');
		}

		System.out.println(sb.toString());

	}
}
