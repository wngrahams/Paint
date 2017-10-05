package shapes;

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
	public void drawShape() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDim(List<Double> newDim) throws IllegalArgumentException {
		if (newDim.size() != 2)
			throw new IllegalArgumentException("A rectangle is created with exactly 2 sides");
		
		dimensions.set(0, newDim.get(0));
		dimensions.set(1, newDim.get(1));
	}
	
	public void setDim(double height, double width) {
		dimensions.set(0, height);
		dimensions.set(1, width);
	}

}
