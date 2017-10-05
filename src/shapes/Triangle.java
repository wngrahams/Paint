package shapes;

import java.util.ArrayList;
import java.util.List;

public class Triangle extends Shape {
	
	public Triangle() {
		dimensions = new ArrayList<Double>(3);
		this.setDim(0.0, 0.0, 0.0);
	}

	public Triangle(double s1, double s2, double s3) {
		dimensions = new ArrayList<Double>(3);
		this.setDim(s1, s2, s3);
	}
	
	public Triangle(List<Double> newDim){
		dimensions = new ArrayList<Double>(3);
		this.setDim(newDim);
	}

	@Override
	public double calculateArea() {
		// Use Heron's formula to calculate area of a Triangle given 3 sides:
		double halfPerimeter = this.calculatePerimeter() * 0.5;
		double radicand = halfPerimeter * (halfPerimeter - dimensions.get(0)) * (halfPerimeter - dimensions.get(1)) * (halfPerimeter - dimensions.get(2));
		
		return Math.sqrt(radicand);
	}

	@Override
	public double calculatePerimeter() {
		return dimensions.get(0) + dimensions.get(1) + dimensions.get(2);
	}

	@Override
	public void drawShape() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDim(List<Double> newDim) throws IllegalArgumentException {
		if (newDim.size() != 3)
			throw new IllegalArgumentException("A triangle is created with exactly 3 sides");
		
		setDimList(newDim.get(0), newDim.get(1), newDim.get(2));
	}
	
	public void setDim(double side1, double side2, double side3) {
		setDimList(side1, side2, side3);
	}

	private void setDimList(double a, double b, double c) {
		if (dimensions.size() != 0) {
			dimensions.set(0, a);
			dimensions.set(1, b);
			dimensions.set(2, c);
		}
		else {
			dimensions.add(a);
			dimensions.add(b);
			dimensions.add(c);
		}
	}
}
