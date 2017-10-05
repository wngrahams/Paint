package shapes;

import java.util.ArrayList;
import java.util.List;

public abstract class Shape {
	
	protected ArrayList<Double> dimensions;
	
	public abstract double calculateArea();
	public abstract double calculatePerimeter();
	
	public abstract void drawShape();
	public abstract void setDim(List<Double> newDim);
}
