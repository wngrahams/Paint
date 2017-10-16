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
		double radicand = halfPerimeter * (halfPerimeter - (dimensions[0]/PIXELS_PER_CM)) * (halfPerimeter - (dimensions[1]/PIXELS_PER_CM)) * (halfPerimeter - (dimensions[2]/PIXELS_PER_CM));
		
		return (Math.sqrt(radicand));
	}

	@Override
	public double calculatePerimeter() {
		return (dimensions[0] + dimensions[1] + dimensions[2]) / PIXELS_PER_CM;
	}
		
	@Override
	public boolean contains(int x, int y) {
		// Use barycentric technique to determine if point (x,y) is within the bounds of this triangle
		int[] v0 = new int[2];
		int[] v1 = new int[2];
		int[] v2 = new int[2];
		
		v0[0] = location[4] - location[0];
		v0[1] = location[5] - location[1];
		v1[0] = location[2] - location[0];
		v1[1] = location[3] - location[1];
		v2[0] = x - location[0];
		v2[1] = y - location[1];
		
		long dot00 = dot(v0, v0);
		long dot01 = dot(v0, v1);
		long dot02 = dot(v0, v2);
		long dot11 = dot(v1, v1);
		long dot12 = dot(v1, v2);
		
		double denominator = (1.0) / (dot00 * dot11 - dot01 * dot01);
		double lambda1 = (dot11 * dot02 - dot01 * dot12) * denominator;
		double lambda2 = (dot00 * dot12 - dot01 * dot02) * denominator;
		
		return (lambda1 >= 0) && (lambda2 >= 0) && (lambda1 + lambda2 < 1);
	}
	
	private long dot(int[] a, int[] b) {
		if (a.length != b.length)
			throw new IllegalArgumentException("Dot product is not defined for vectors of different lengths");
		
		long result = 0;
		for (int i=0; i<a.length; i++) {
			result += (a[i] * b[i]);
		}
		
		return result;
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
