package paint;

public class Triangle extends Shape {

	private double side1;
	private double side2;
	private double side3;

	public Triangle(double s1, double s2, double s3) throws Exception {
		side1 = s1;
		side2 = s2;
		side3 = s3;
	}

	@Override
	double calculateArea() {
		// Use Heron's formula to calculate area of a Triangle given 3 sides:
		double halfPerimeter = this.calculatePerimeter() * 0.5;
		double radicand = halfPerimeter * (halfPerimeter - side1) * (halfPerimeter - side2) * (halfPerimeter - side3);
		
		return Math.sqrt(radicand);
	}

	@Override
	double calculatePerimeter() {
		return side1 + side2 + side3;
	}

	@Override
	void drawShape() {
		// TODO Auto-generated method stub
		
	}


}
