package abc074;

import java.io.IOException;
import java.util.Scanner;

public class C {

	public static void main(String[] args) throws IOException {

		// input
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();
		int c = in.nextInt();
		int d = in.nextInt();
		int e = in.nextInt();
		int f = in.nextInt();

		int seiyaku1 = f / (100 + e);
		int maxSugarSeiyaku = 0;
		int maxWater = 0;
		for (int i = 0; i <= seiyaku1; i++) {
			for (int j = 0; j <= seiyaku1; j++) {
				if ((a*i+b*j)*(100+e) <= f) {
					maxSugarSeiyaku = Math.max(maxSugarSeiyaku, (a*i+b*j)*e);
					maxWater = Math.max(maxWater, (a*i+b*j)*100);
				}
			}
		}

		int maxSugar = 0;
		for (int i = 0; i <= maxSugarSeiyaku; i++) {
			for (int j = 0; j <= maxSugarSeiyaku; j++) {
				if (i*c+j*d <= maxSugarSeiyaku) {
					maxSugar = Math.max(maxSugar, i*c+j*d);
				}
			}
		}

		System.out.println((maxWater+maxSugar) +" "+ maxSugar);


	}

}
