package abc082;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

public class A {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("[Start]");
		String s = new String(in.readLine());
//		System.out.println("input-value:" + s);

		String[] strings = new String[2];
		strings = s.split("\\s+");

		BigDecimal d1 = new BigDecimal(strings[0]);
		BigDecimal d2 = new BigDecimal(strings[1]);

		BigDecimal average = (d1.add(d2).divide(new BigDecimal(2)));
		BigDecimal ans = average.setScale(0, BigDecimal.ROUND_UP);

//		System.out.println(average);
		System.out.println(ans);

	}
}
