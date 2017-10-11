package shapes;

import java.awt.Graphics;

public class Triangle extends Shape {
	
	public Triangle() {
		dimensions = new int[3];
		location = new int[6];
		this.setDim(0, 0, 0);
	}

	public Triangle(int s1, int s2, int s3) {
		dimensions = new int[3];
		location = new int[6];
		this.setDim(s1, s2, s3);
	}
	
	public Triangle(int[] newDim){
		dimensions = new int[3];
		location = new int[6];
		this.setDim(newDim);
	}
	
	private void calculateDimFromLoc() {
		for (int i=0; i<dimensions.length; i++)
			dimensions[i] = (int) Math.sqrt(Math.pow((location[(2*i+2)%6] - location[(2*i)%6]), 2) + Math.pow((location[(2*i+3)%6] - location[(2*i+1)%6]), 2));
	}

	@Override
	public double calculateArea() {
		// Use Heron's formula to calculate area of a Triangle given 3 sides:
		double halfPerimeter = this.calculatePerimeter() * 0.5;
		double radicand = halfPerimeter * (halfPerimeter - dimensions[0]) * (halfPerimeter - dimensions[1]) * (halfPerimeter - dimensions[2]);
		
		return Math.sqrt(radicand);
	}

	@Override
	public double calculatePerimeter() {
		return dimensions[0] + dimensions[1] + dimensions[2];
	}
	
	@Override
	public void drawShape(Graphics g) {
		g.setColor(shapeColor);
		int[] xLocation = new int[3];
		int[] yLocation = new int[3];
		for (int i=0; i<6; i++){
			if (i%2 == 0)
				xLocation[i/2] = location[i];
			else
				yLocation[i/2] = location[i];
		}
		g.fillPolygon(xLocation, yLocation, 3);
	}
	
	@Override
	public void setDim(int[] newDim) {
		dimensions[0] = newDim[0];
		dimensions[1] = newDim[1];
		dimensions[2] = newDim[2];
	}
	
	public void setDim(int side1, int side2, int side3) {
		dimensions[0] = side1;
		dimensions[1] = side2;
		dimensions[2] = side3;
	}

	@Override
	public void setLoc(int[] newLoc) {
		for (int i=0; i<6; i++) {
			location[i] = newLoc[i];
		}
		
		calculateDimFromLoc();
	}
}
