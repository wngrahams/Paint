package paint;

public class Line extends Shape {
	
	private double startPt;
	private double endPt;

	public Line(double a, double b) {
		startPt = a;
		endPt = b;
	}

	@Override
	double calculateArea() {
		return 0;
	}

	@Override
	double calculatePerimeter() {
		return 0;
	}

	@Override
	void drawShape() {
		// TODO Auto-generated method stub

	}

}
