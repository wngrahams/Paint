package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

public abstract class Shape {
	
	protected int[] dimensions;
	protected int[] location;
	
	protected Color shapeColor = Color.BLACK;
	
	public abstract double calculateArea();
	public abstract double calculatePerimeter();
	
	public abstract boolean contains(int x, int y);
	public abstract void drawShape(Graphics g);
	
	public int[] getLoc() {
		return location;
	}
	public abstract void setDim(int[] newDim);
	public abstract void setLoc(int[] newLoc);
	public void setColor(Color newColor) { shapeColor = newColor; }

	public final static Shape LINE = new Line();
	public final static Shape OVAL = new Oval();
	public final static Shape RECTANGLE = new Rectangle();
	public final static Shape TRIANGLE = new Triangle();
	
	protected final static double PIXELS_PER_CM = (Toolkit.getDefaultToolkit().getScreenResolution()) / 2.54;
}

