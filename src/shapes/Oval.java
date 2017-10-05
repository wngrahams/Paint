package shapes;

import java.util.ArrayList;
import java.util.List;

public class Oval extends Shape {
	
	public Oval() {
		dimensions = new ArrayList<Double>(2);
		this.setDim(0.0, 0.0);
	}
	
	public Oval(double a, double b) {
		dimensions = new ArrayList<Double>(2);
		this.setDim(a, b);
	}
	
	public Oval(List<Double> newDim) {
		dimensions = new ArrayList<Double>(2);
		this.setDim(newDim);
	}

	@Override
	public double calculateArea() {
		return dimensions.get(0) * dimensions.get(1) * Math.PI;
	}

	@Override
	public double calculatePerimeter() {
		if (dimensions.get(0) == dimensions.get(1))
			return 2* Math.PI * dimensions.get(0);
		else {
			// Use Ramanujan's approximation of the perimeter of an ellipse:
			double h = Math.pow((dimensions.get(0) - dimensions.get(1)), 2) / Math.pow((dimensions.get(0) + dimensions.get(1)),2);
			double perimApprox = (Math.PI) * (dimensions.get(0) + dimensions.get(1));
			perimApprox *= (1 + (3 * h)/(10 + Math.sqrt(4 - 3 * h)));
			
			return perimApprox;
		} 
	}

	@Override
	public void drawShape() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDim(List<Double> newDim) throws IllegalArgumentException {
		if (newDim.size() != 2)
			throw new IllegalArgumentException("An oval is created with exactly 2 dimensions");
		
		if (newDim.get(0) > newDim.get(1)) {
			dimensions.set(0, newDim.get(0));
			dimensions.set(1, newDim.get(1));
		}
		else {
			dimensions.set(0, newDim.get(1));
			dimensions.set(1, newDim.get(0));
		}
	}
	
	public void setDim(double a, double b) {
		if (a > b) {
			dimensions.set(0, a);
			dimensions.set(1, b);
		}
		else {
			dimensions.set(0, b);
			dimensions.set(1, a);
		}
	}
}
