package abc083;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class A {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = new String(in.readLine());

		String[] strings = new String[4];
		strings = s.split("\\s+");

		BigDecimal d1 = new BigDecimal(strings[0]);
		BigDecimal d2 = new BigDecimal(strings[1]);
		BigDecimal d3 = new BigDecimal(strings[2]);
		BigDecimal d4 = new BigDecimal(strings[3]);

		BigDecimal left = d1.add(d2);
		BigDecimal right = d3.add(d4);

		if (left.compareTo(right) > 0) {
			System.out.println("Left");
		} else if (left.equals(right)) {
			System.out.println("Balanced");
		} else {
			System.out.println("Right");
		}

	}
}