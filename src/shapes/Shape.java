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
	
	public abstract int contains(int x, int y);
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
	
	public final static int LOCATION_NOT_CONTAINED = 0b0000;
	public final static int LOCATION_SW = 0b0100;
	public final static int LOCATION_SE = 0b0101;
	public final static int LOCATION_NW = 0b0110;
	public final static int LOCATION_NE = 0b0111;
	public final static int LOCATION_N = 0b1000;
	public final static int LOCATION_S = 0b1001;
	public final static int LOCATION_W = 0b1010;
	public final static int LOCATION_E = 0b1011;
	public final static int LOCATION_MIDDLE = 0b1100;
	
	protected final static double PIXELS_PER_CM = (Toolkit.getDefaultToolkit().getScreenResolution()) / 2.54;
}

