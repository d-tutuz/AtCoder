package geometry;

import java.util.List;

public class GeometryUtils {

	// 点
	class Point {
		double x, y;
	}

	// 線分
	class Segment {
		Point p1, p2;
	}

	// 円
	class Circle {
		Point c;
		double r;
	}

	// 多角形
	class Polygon {
		List<Point> points;
	}

}
