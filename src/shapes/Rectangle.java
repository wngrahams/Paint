package shapes;

public class Rectangle extends Shape {

	private double height;
	private double width;

	public Rectangle(double h, double w) {
		height = h;
		width = w;
	}

	@Override
	public double calculateArea() {
		return height * width;
	}

	@Override
	public double calculatePerimeter() {
		return (2 * height) + (2 * width);
	}

	@Override
	public void drawShape() {
		// TODO Auto-generated method stub

	}

}
