package abc083;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class B {


	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = new String(in.readLine());

		String[] strings = new String[3];
		strings = s.split("\\s+");

		// N(1~10000)
		String number = strings[0];
		String[] numbers = null;
		int intNumber = Integer.parseInt(number);

		// Nの各桁を足し合わせた数のKey,Value
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 1; i <= intNumber; i++) {
			int plusNum = 0;
			numbers = String.valueOf(i).split("");
			for (String n : numbers) {
				plusNum += Integer.parseInt(n);
			}
			map.put(i, plusNum);
		}

		// A ≦ mapのvalue ≦ Bとなっている数値を取得し、総和する
		int outputSum = 0;
		Integer a = Integer.parseInt(strings[1]);
		Integer b = Integer.parseInt(strings[2]);

		for (Integer key : map.keySet()) {

			if (a <= map.get(key) && map.get(key) <= b) {
				outputSum += key;
			}
		}

		// 結果出力
		System.out.println(outputSum);



	}
}
