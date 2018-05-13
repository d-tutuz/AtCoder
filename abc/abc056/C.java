package abc056;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class C {

	static int x;
	static int anstime;
	static int count;
	static int INF = 1 << 29;

	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		x = in.nextInt();

		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 1));

		while (!q.isEmpty()) {
			Node n = q.remove();
			int time = n.time;
			int sum = n.sum;

			if (sum == x) {
				System.out.println(time - 1);
				return;
			}
			q.add(new Node(sum + time, time + 1));
			q.add(new Node(sum, time + 1));

		}
	}

	static class Node {
		int sum;
		int time;
		public Node(int sum, int time) {
			this.sum = sum;
			this.time = time;
		}
	}

}
