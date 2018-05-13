package tdpy;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class B {

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
//		in = new Scanner(new File("/workspace"));

		int A = in.nextInt();
		int B = in.nextInt();
		Deque<Integer> a = new ArrayDeque<>();
		Deque<Integer> b = new ArrayDeque<>();
		for (int i = 0; i < A; i++) {
			a.push(in.nextInt());
		}
		for (int i = 0; i < B; i++) {
			b.push(in.nextInt());
		}

		int snu = 0;

		int turn = 0;
		while (!a.isEmpty() || !b.isEmpty()) {
			int value = 0;
			if (a.isEmpty()) {
				value = b.pop();
				if (turn % 2 == 0) snu += value;
			} else if (b.isEmpty()) {
				value = a.pop();
				if (turn % 2 == 0) snu += value;
			} else {
				if (a.peek() > b.peek()) {
					value = a.pop();
				} else {
					value = b.pop();
				}
				if (turn % 2 == 0) snu += value;
			}
			turn++;
		}
		System.out.println(snu);
	}

}
