package shapes;

import java.util.ArrayList;
import java.util.List;

public class Line extends Shape {

	public Line() {
		dimensions = new ArrayList<Double>(2);
		this.setDim(0.0, 0.0);
	}
	
	public Line(double a, double b) {
		dimensions = new ArrayList<Double>(2);
		this.setDim(a, b);
	}
	
	public Line(List<Double> newDim) {
		dimensions = new ArrayList<Double>(2);
		this.setDim(newDim);
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

	@Override
	public void setDim(List<Double> newDim) throws IllegalArgumentException {
		if (newDim.size() != 2)
			throw new IllegalArgumentException("A line is created with exactly 2 points");
		
		setDimList(newDim.get(0), newDim.get(1));
	}
	
	public void setDim(double startPt, double endPt) {
		setDimList(startPt, endPt);
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
