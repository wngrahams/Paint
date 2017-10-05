package shapes;

public class Line extends Shape {
	
	private double startPt;
	private double endPt;

	public Line() {
		startPt = 0;
		endPt = 0;
	}
	
	public Line(double a, double b) {
		startPt = a;
		endPt = b;
	}

	@Override
	public double calculateArea() {
		return 0;
	}

	@Override
	public double calculatePerimeter() {
		return 0;
	}

	@Override
	public void drawShape() {
		// TODO Auto-generated method stub

	}

}
