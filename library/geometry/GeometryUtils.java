package geometry;


public class GeometryUtils {

	// 点座標
	static class Vertex {
		double x, y;

		public Vertex(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}

	// ベクトル
	static class Vector {
		double x, y;

		public Vector(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Vector [x=" + x + ", y=" + y + "]";
		}
	}

	// 2点間の距離
	static double distanceVertex(Vertex v1, Vertex v2) {
		return Math.sqrt(Math.pow(v1.x - v2.x, 2) + Math.pow(v1.y - v2.y, 2));
	}

	// ベクトルの外積
	static double crossVector(Vector v1, Vector v2) {
		return v1.x * v2.y - v1.y * v2.x;
	}

	// 点と直線の距離
	static double distanceDotAndLine(Vertex p, Vertex a, Vertex b) {
		Vector ab = new Vector(b.x - a.x, b.y - a.y);
		Vector ap = new Vector(p.x - a.x, p.y - a.y);

		double d = Math.abs(crossVector(ab, ap));
		double l = distanceVertex(a, b);
		double h = d / l;
		return h;
	}


}
