package abc091;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class C_2 {

	public static void main(String[] args) throws IOException {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		TaskX solver = new TaskX();
		solver.solve(1, in, out);
		out.close();
	}

	static class TaskX {
		public void solve(int testNumber, InputReader in, PrintWriter out) {

			int n = in.nextInt();
			List<P> p1 = new ArrayList<>();
			List<P> p2 = new ArrayList<>();

			List<P> q1 = new ArrayList<>();
			List<P> q2 = new ArrayList<>();

			List<P> s1 = new ArrayList<>();
			List<P> s2 = new ArrayList<>();

			List<P> t1 = new ArrayList<>();
			List<P> t2 = new ArrayList<>();


			for (int i = 0; i < n; i++) {
				p1.add(new P(in.nextInt(), in.nextInt()));
			}
			for (int i = 0; i < n; i++) {
				p2.add(new P(in.nextInt(), in.nextInt()));
			}

			q1 = copy(p1);
			q2 = copy(p2);
			s1 = copy(p1);
			s2 = copy(p2);
			t1 = copy(p1);
			t2 = copy(p2);

			Collections.sort(p1, new MyComparator());
			Collections.sort(p2, new MyComparator());

			Collections.sort(t1, new MyComparator());
			Collections.sort(t2, new MyComparator());

			int count1 = 0;
			int count2 = 0;
			int count3 = 0;
			int count4 = 0;

			for (int i = p1.size() - 1; i >= 0; i--) {
				for (P p : p2) {
					if (p1.get(i).x < p.x && p1.get(i).y < p.y) {
						count1++;
						p2.remove(p);
						break;
					}
				}
			}

			for (int i = 0; i < t1.size(); i++) {
				for (P p : t2) {
					if (t1.get(i).x < p.x && t1.get(i).y < p.y) {
						count3++;
						t2.remove(p);
						break;
					}
				}
			}

			Collections.sort(q1, new MyComparator2());
			Collections.sort(q2, new MyComparator2());

			Collections.sort(s1, new MyComparator2());
			Collections.sort(s2, new MyComparator2());

			for (int i = q1.size() - 1; i >= 0; i--) {
				for (P p : q2) {
					if (q1.get(i).x < p.x && q1.get(i).y < p.y) {
						count2++;
						q2.remove(p);
						break;
					}
				}
			}

			for (int i = 0; i < s1.size(); i++) {
				for (P p : s2) {
					if (s1.get(i).x < p.x && s1.get(i).y < p.y) {
						count4++;
						s2.remove(p);
						break;
					}
				}
			}

			out.println(Math.max(Math.max(count1, count2),Math.max(count3, count4)));
		}

	}

	public static List<P> copy(List<P> list){
		return new ArrayList<P>(list);
	}

	static class P {
		int x, y;

		P(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class MyComparator implements Comparator<P>  {
		public int compare(P a, P b) {
			int no1 = a.x;
			int no2 = b.x;

			if (no1 > no2) {
				return 1;

			} else if (no1 == no2) {
				return 0;

			} else {
				return -1;

			}
		}
	}

	static class MyComparator2 implements Comparator<P>  {
		public int compare(P a, P b) {
			int no1 = a.y;
			int no2 = b.y;

			if (no1 > no2) {
				return 1;

			} else if (no1 == no2) {
				return 0;

			} else {
				return -1;

			}
		}
	}

	static class ArrayUtils {
		public static Map<Integer, Integer> getCountMap(int[] array) {
			Map<Integer, Integer> map = new TreeMap<>();
			for (int x : array)
				map.merge(x, 1, Integer::sum);
			return map;
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
