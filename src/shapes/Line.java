package shapes;

import java.awt.Graphics;

public class Line extends Shape {

	public Line() {
		dimensions = new int[2];
		this.setDim(0, 0);
	}
	
	public Line(int a, int b) {
		dimensions = new int[2];
		this.setDim(a, b);
	}
	
	public Line(int[] newDim) {
		dimensions = new int[2];
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
	public void drawShape(Graphics g, int[] pos) {
		g.drawLine(pos[0], pos[1], dimensions[0] - pos[0], dimensions[1] - pos[1]);
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
}
