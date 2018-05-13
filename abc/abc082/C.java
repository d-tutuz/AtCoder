package abc082;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class C {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int ans = 0;

		List<Integer> list = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			list.add(sc.nextInt());
		}

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Integer s : list) {
		    Integer i = map.get(s);
		    map.put(s, i == null ? 1 : i + 1);
		}

		for (Integer key : map.keySet()) {
		    if (key > map.get(key)) {
				ans += map.get(key);
			} else if (key < map.get(key)) {
				ans += (map.get(key) - key);
			}
		}
		System.out.println(ans);
		sc.close();
	}
}
