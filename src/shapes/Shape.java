package shapes;

import java.awt.Graphics;

public abstract class Shape {
	
	protected int[] dimensions;
	
	public abstract double calculateArea();
	public abstract double calculatePerimeter();
	
	public abstract void drawShape(Graphics g, int[] pos);
	public abstract void setDim(int[] newDim);

}
