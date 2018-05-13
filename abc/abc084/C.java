package abc084;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class C {

	public static void main2(String[] args) {

		// TODO:深い理解がまだ
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		List<String> line = new ArrayList<String>();
		for (int i = 0; i < n; i++) {
			line.add(sc.nextLine());
		}

		for (int i = 1; i < line.size(); i++) {
			System.out.println("i="+i+":"+line.get(i));
		}
	}

	public static void main(String[] args) {
		int n = 3;
		List<String> line = new ArrayList<String>();
		line.add("6 5 1");
		line.add("1 10 1");
		line.add("0 0 0");

		int ans = 0;
		for (int i = 1; i < n-1; i++) {
			ans += calcTime(line.get(i), line.get(i+1));
		}
		System.out.println(ans);
	}

	private static int calcTime(String str1, String str2) {
		int time = 0;

		String[] strlist1 = str1.split(" ");
		String[] strlist2 = str2.split(" ");

		int c_i1 = Integer.parseInt(strlist1[0]);
		int s_i1 = Integer.parseInt(strlist1[1]);
		int f_i1 = Integer.parseInt(strlist1[2]);
		int c_i2 = Integer.parseInt(strlist2[0]);
		int s_i2 = Integer.parseInt(strlist2[1]);
		int f_i2 = Integer.parseInt(strlist2[2]);

		if (f_i2 == 0){
			time = c_i1 + s_i1 + s_i2;
		} else {
			time = c_i1 + s_i1 + Math.floorMod(c_i1 + s_i1 - s_i2, f_i2);
		}
		return time;
	}
}
