package shapes;

import java.awt.Graphics;

public class Line extends Shape {

	public Line() {
		dimensions = new int[2];
		location = new int[2];
		this.setDim(0, 0);
	}
	
	public Line(int a, int b) {
		dimensions = new int[2];
		location = new int[2];
		this.setDim(a, b);
	}
	
	public Line(int[] newDim) {
		dimensions = new int[2];
		location = new int[2];
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
	public void drawShape(Graphics g) {
		g.setColor(shapeColor);
		g.drawLine(location[0], location[1], dimensions[0], dimensions[1]);
	}
	
	@Override
	public void setDim(int[] newDim) {
		dimensions[0] = newDim[0];
		dimensions[1] = newDim[1];
	}
	
	public void setDim(int startPt, int endPt) {
		dimensions[0] = startPt;
		dimensions[1] = endPt;
	}
	
	@Override
	public void setLoc(int[] newLoc) {
		location[0] = newLoc[0];
		location[1] = newLoc[1];
	}
}
