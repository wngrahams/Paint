package shapes;

import java.awt.Graphics;

public class Rectangle extends Shape {
	
	public Rectangle() {
		dimensions = new int[2];
		location = new int[2];
		this.setDim(0, 0);
	}

	public Rectangle(int height, int width) {
		dimensions = new int[2];
		location = new int[2];
		this.setDim(height, width);
	}
	
	public Rectangle(int[] newDim){
		dimensions = new int[2];
		location = new int[2];
		this.setDim(newDim);
	}

	@Override
	public double calculateArea() {
		return dimensions[0] * dimensions[1];
	}

	@Override
	public double calculatePerimeter() {
		return (2 * dimensions[0]) + (2 * dimensions[1]);
	}

	@Override
	public void drawShape(Graphics g) {
		g.fillRect(location[0], location[1], dimensions[0], dimensions[1]);
	}
	
	@Override
	public void setDim(int[] newDim) {
		dimensions[0] = newDim[0];
		dimensions[1] = newDim[1];
	}
	
	public void setDim(int height, int width) {
		dimensions[0] = height;
		dimensions[1] = width;
	}

	@Override
	public void setLoc(int[] newLoc) {
		location[0] = newLoc[0];
		location[1] = newLoc[1];
	}
}
