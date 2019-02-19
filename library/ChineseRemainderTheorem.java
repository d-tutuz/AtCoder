/**
 * 中国剰余定理(CRM)
 * */
class ChineseRemainderTheorem {

	// 負の数にも対応した mod
	long mod(long a, long m) {
		return (a % m + m) % m;
	}

	// 拡張 Euclid の互除法
	// します
	//
	// long[] pq = new long[3];
	// pq[0] = d, pq[1] = p, pq[2] = q;
	long extGcd(long a, long b, long[] pq) {
		if (b == 0) {
			pq[1] = 1;
			pq[2] = 0;
			return a;
		}
		long d = extGcd(b, a % b, pq);
		long tmp = pq[2];
		pq[2] = pq[1] - a/b * tmp;
		pq[1] = tmp;
		return d;
	}


	// 中国剰余定理
	// x ≡ b1 (mod m1), x ≡ b2 (mod m2) なる x を求める
	//
	// リターン値を (r, m) とすると解は x ≡ r (mod. m)
	// 解なしの場合は (0, -1) をリターン
	long[] crt(long b1, long m1, long b2, long m2) {
		long[] pq = new long[3];
		long d = extGcd(m1, m2, pq);
		if ((b2 - b1) % d != 0) {
			return new long[]{0, -1};
		}
		long m = m1 * (m2/d);
		long tmp = (b2 - b1) / d * pq[1] % (m2/d);
		long r = mod(b1 + m1 * tmp, m);
		pq[1] = r;
		pq[2] = m;
		return new long[]{r, m};
	}
}
