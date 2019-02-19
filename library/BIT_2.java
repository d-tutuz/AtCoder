import java.util.Arrays;

/**
 * 0-indexed BinaryIndexTree
 * 区間加算あり
 * */
class BIT_2 {
	private int n;
	private long[] bit;

	public BIT_2(int n) {
		this.n = n;
		bit = new long[n + 2];
	}

	public void accumulate(int begin, int end, long num) {
		accumulate(begin, num);
		accumulate(end, -num);
	}

	private void accumulate(int index, long num) {
		index++;
		while (index <= n + 1) {
			bit[index] += num;
			index += index & -index;
		}
	}

	private long sum(int i) {
		long s = 0;
		while (i > 0) {
			s += bit[i];
			i -= i & -i;
		}
		return s;
	}

	public long get(int index) {
		return sum(index + 1);
	}

	public void set(int index, long num) {
		accumulate(index, index + 1, num - get(index));
	}

	public String toString() {
		long[] value = new long[n];
		for (int i = 0; i < n; i++) {
			value[i] = get(i);
		}
		return Arrays.toString(value);
	}
}