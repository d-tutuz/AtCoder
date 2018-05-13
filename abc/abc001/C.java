package abc001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class C {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static int INF = 1 << 30;
	static int modP = 1000000007;
	static int[] mh4 = { 0, -1, 1, 0 };
	static int[] mw4 = { -1, 0, 0, 1 };

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			BigDecimal deg = new BigDecimal(in.nextDouble());
			deg = deg.divide(BigDecimal.TEN);
			BigDecimal dis = new BigDecimal(in.nextDouble());
			dis = dis.divide(new BigDecimal(60), 1, BigDecimal.ROUND_HALF_UP);

			System.out.println(deg +" " +dis);

			String a = calcDeg(deg.doubleValue());
			String b = calcDis(dis.doubleValue());

			if ("0".equals(b)) {
				out.println("C "+b);
			} else {
				out.println(a +" "+ b);
			}


		}
		static String calcDis(double dis) {
			String ret = "";
			if (0.0 <= dis && dis <= 0.2) {
				ret = "0";
			} else if (0.3 <= dis && dis <= 1.5) {
				ret = "1";
			} else if (1.6 <= dis && dis <= 3.3) {
				ret = "2";
			} else if (3.4 <= dis && dis <= 5.4) {
				ret = "3";
			} else if (5.5 <= dis && dis <= 7.9) {
				ret = "4";
			} else if (8.0 <= dis && dis <= 10.7) {
				ret = "5";
			} else if (10.8 <= dis && dis <= 13.8) {
				ret = "6";
			} else if (13.9 <= dis && dis <= 17.1) {
				ret = "7";
			} else if (17.2 <= dis && dis <= 20.7) {
				ret = "8";
			} else if (20.8 <= dis && dis <= 24.4) {
				ret = "9";
			} else if (24.5 <= dis && dis <= 28.4) {
				ret = "10";
			} else if (28.5 <= dis && dis <= 32.6) {
				ret = "11";
			} else if (32.7 <= dis) {
				ret = "12";
			}
			return ret;
		}


		static String calcDeg(double deg) {
			String ret = "";
			if (11.25 <= deg && deg < 33.75) {
				ret = "NNE";
			} else if (33.75 <= deg && deg < 56.25) {
				ret = "NE";
			} else if (56.25 <= deg && deg < 78.75) {
				ret = "ENE";
			} else if (78.75 <= deg && deg < 101.25) {
				ret = "E";
			} else if (101.25 <= deg && deg < 123.75) {
				ret = "ESE";
			} else if (123.75 <= deg && deg < 146.25) {
				ret = "SE";
			} else if (146.25 <= deg && deg < 168.75) {
				ret = "SSE";
			} else if (168.75 <= deg && deg < 191.25) {
				ret = "S";
			} else if (191.25 <= deg && deg < 213.75) {
				ret = "SSW";
			} else if (213.75 <= deg && deg < 236.25) {
				ret = "SW";
			} else if (236.25 <= deg && deg < 258.75) {
				ret = "WSW";
			} else if (258.75 <= deg && deg < 281.25) {
				ret = "W";
			} else if (281.25 <= deg && deg < 303.75) {
				ret = "WNW";
			} else if (303.75 <= deg && deg < 326.25) {
				ret = "NW";
			} else if (326.25 <= deg && deg < 348.75) {
				ret = "NNW";
			} else {
				ret = "N";
			}
			return ret;
		}
	}

	static class InputReader {
		BufferedReader in;
		StringTokenizer tok;

		public String nextString() {
			while (!tok.hasMoreTokens()) {
				try {
					tok = new StringTokenizer(in.readLine(), " ");
				} catch (IOException e) {
					throw new InputMismatchException();
				}
			}
			return tok.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(nextString());
		}

		public long nextLong() {
			return Long.parseLong(nextString());
		}

		public double nextDouble() {
			return Double.parseDouble(nextString());
		}

		public int[] nextIntArray(int n) {
			int[] res = new int[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextInt();
			}
			return res;
		}

		public long[] nextLongArray(int n) {
			long[] res = new long[n];
			for (int i = 0; i < n; i++) {
				res[i] = nextLong();
			}
			return res;
		}

		public InputReader(InputStream inputStream) {
			in = new BufferedReader(new InputStreamReader(inputStream));
			tok = new StringTokenizer("");
		}

	}

}
