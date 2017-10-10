package shapes;

import java.awt.Graphics;

public class Triangle extends Shape {
	
	public Triangle() {
		dimensions = new int[3];
		this.setDim(0, 0, 0);
	}

	public Triangle(int s1, int s2, int s3) {
		dimensions = new int[3];
		this.setDim(s1, s2, s3);
	}
	
	public Triangle(int[] newDim){
		dimensions = new int[3];
		this.setDim(newDim);
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
	
	public Triangle getShape(){
		return new Triangle(dimensions);
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
	public void drawShape(Graphics g) {
		// TODO Auto-generated method stub
		// g.fillPolygon()
	}

	@Override
	public void setLoc(int[] newLoc) {
		location[0] = newLoc[0];
		location[1] = newLoc[1];
		location[2] = newLoc[2];
	}
}
