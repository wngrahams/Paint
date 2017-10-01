package paint;

public class Rectangle extends Shape {

	private double height;
	private double width;

	public Rectangle(double h, double w) throws Exception {
		height = h;
		width = w;
	}

	@Override
	double calculateArea() {
		return height * width;
	}

	@Override
	double calculatePerimeter() {
		return (2 * height) + (2 * width);
	}

	@Override
	void drawShape() {
		// TODO Auto-generated method stub

	}

}
