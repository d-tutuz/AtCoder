package abc102;


public class Main {

	public static void main(String[] args) {
		double a = Math.pow(10, 12) * 2;

		for (int i = 0; i < 1000; i++) {
			a *= 0.667;
		}

		System.out.println(a);
		System.out.println(Long.MAX_VALUE);

	}
}
