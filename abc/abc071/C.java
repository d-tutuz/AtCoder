package abc071;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// TODO:冗長

public class C {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] A = new int[N];
		List<Integer> list = new ArrayList<>();
		long l1 = 0L,l2 = 0L;

		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int ai : A) {
			Integer i = map.get(ai);
			map.put(ai, i == null ? 1 : i + 1);
		}

		for (int key : map.keySet()) {
			if ( map.get(key) >= 2) list.add(key);
		}
		Integer[] answers = (Integer[]) list.toArray(new Integer[0]);
		if (answers.length >= 2) {
			Arrays.sort(answers);
			l2 = (long)answers[answers.length-1]*(long)answers[answers.length-2];
		}
		list.clear();

		for (int key : map.keySet()) {
	    	if ( map.get(key) >= 4) list.add(key);
		}
		Integer[] answers2 = (Integer[]) list.toArray(new Integer[0]);
	    if (answers2.length >= 1) {
		    Arrays.sort(answers2);
		    l1 = (long)answers2[answers2.length-1]*(long)answers2[answers2.length-1];
	    }
	    System.out.println(Math.max(l1, l2));
	}

}
