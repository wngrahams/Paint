package shapes;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Shape {
	
	public Rectangle() {
		dimensions = new ArrayList<Double>(2);
		this.setDim(0.0, 0.0);
	}

	public Rectangle(double height, double width) {
		dimensions = new ArrayList<Double>(2);
		this.setDim(height, width);
	}
	
	public Rectangle(List<Double> newDim){
		dimensions = new ArrayList<Double>(2);
		this.setDim(newDim);
	}

	@Override
	public double calculateArea() {
		return dimensions.get(0) * dimensions.get(1);
	}

	@Override
	public double calculatePerimeter() {
		return (2 * dimensions.get(0)) + (2 * dimensions.get(1));
	}

	@Override
	public void drawShape(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDim(List<Double> newDim) throws IllegalArgumentException {
		if (newDim.size() != 2)
			throw new IllegalArgumentException("A rectangle is created with exactly 2 sides");
		
		setDimList(newDim.get(0), newDim.get(1));
	}
	
	public void setDim(double height, double width) {
		setDimList(height, width);
	}

	private void setDimList(double a, double b) {
		if (dimensions.size() != 0) {
			dimensions.set(0, a);
			dimensions.set(1, b);
		}
		else {
			dimensions.add(a);
			dimensions.add(b);
		}
	}
}
