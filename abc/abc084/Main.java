package abc084;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    PrintWriter out = new PrintWriter(System.out);

    public void run() {
    	Scanner in = new Scanner(System.in);
        int Q = in.nextInt();

        boolean[] isPrime = new boolean[100100];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i < isPrime.length; i++) {
            if (!isPrime[i]) continue;
            for (int j = i + i; j < isPrime.length; j += i) {
                isPrime[j] = false;
            }
        }

        int[] imos = new int[100100];
        for (int i = 2; i < imos.length; i++) {
            imos[i] = (isPrime[i] && isPrime[(i+1)/2]) ? 1 : 0;
        }
        for (int i = 2; i < imos.length - 1; i++) {
            imos[i+1] += imos[i];
        }

        for (int q = 0; q < Q; q++) {
            int l = in.nextInt(), r = in.nextInt();
            out.println(imos[r] - imos[l-1]);
        }
        out.flush();
    }


    public static void main(String[] args) {
        new Main().run();
    }

}