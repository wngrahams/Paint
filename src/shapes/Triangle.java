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
	public boolean contains(int x, int y) {
		// Use barycentric technique to determine if point (x,y) is within the bounds of this triangle
		int[] v0 = new int[2];
		int[] v1 = new int[2];
		int[] v2 = new int[2];
		
		System.out.print("A: (" + location[0] + ", " + location[1] + ") ");
		System.out.print("B: (" + location[2] + ", " + location[3] + ") ");
		System.out.print("C: (" + location[4] + ", " + location[5] + ") ");
		System.out.println("p: (" + x + ", " + y + ")");
		
		v0[0] = location[4] - location[0];
		v0[1] = location[5] - location[1];
		v1[0] = location[2] - location[0];
		v1[1] = location[3] - location[1];
		v2[0] = x - location[0];
		v2[1] = y - location[1];
		
		int dot00 = dot2d(v0, v0);
		int dot01 = dot2d(v0, v1);
		int dot02 = dot2d(v0, v2);
		int dot11 = dot2d(v1, v1);
		int dot12 = dot2d(v1, v2);
		
		double denominator = (1.0) / (dot00 * dot11 - dot01 * dot01);
		System.out.println("denominator: " + denominator);
		double lambda1 = (dot11 * dot02 - dot01 * dot12) * denominator;
		double lambda2 = (dot00 * dot12 - dot01 * dot02) * denominator;
		System.out.println("lambda1: " + lambda1);
		System.out.println("lambda2: " + lambda2);
		
		return (lambda1 >= 0) && (lambda2 >= 0) && (lambda1 + lambda2 < 1);
	}
	
	private int dot2d(int[] a, int[] b) {
		if (a.length > 2 || b.length > 2)
			throw new IllegalArgumentException("Vector has more than 2 dimensions");
		
		return(a[0]*b[0] + a[1]*b[1]);
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
