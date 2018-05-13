package abc083;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = new String(in.readLine());

		String[] strings = new String[2];
		strings = s.split("\\s+");

		double x = Double.parseDouble(strings[0]);
		double y = Double.parseDouble(strings[1]);

//		double x = 25;
//		double y = 100;

		double baseNum = 2;
		double threshold = x;

		int count = -1;

		for (double i = 0; threshold <= y; i++) {

			double n = Math.pow(baseNum, i);
			threshold = x * n;
			count++;
		}
		System.out.println(count);
	}

}
