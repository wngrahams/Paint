package shapes;

import java.awt.Graphics;

public abstract class Shape {
	
	protected int[] dimensions;
	protected int[] location;
	
	public abstract double calculateArea();
	public abstract double calculatePerimeter();
	
	public abstract void drawShape(Graphics g);
	public abstract void setDim(int[] newDim);
	public abstract void setLoc(int[] newLoc);

	public final static Shape LINE = new Line();
	public final static Shape OVAL = new Oval();
	public final static Shape RECTANGLE = new Rectangle();
	public final static Shape TRIANGLE = new Triangle();
}

