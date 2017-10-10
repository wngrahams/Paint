package shapes;

import java.awt.Graphics;

public class Oval extends Shape {
	
	public Oval() {
		dimensions = new int[2];
		location = new int[2];
		this.setDim(0, 0);
	}
	
	public Oval(int a, int b) {
		dimensions = new int[2];
		location = new int[2];
		this.setDim(a, b);
	}
	
	public Oval(int[] newDim) {
		dimensions = new int[2];
		location = new int[2];
		this.setDim(newDim);
	}

	@Override
	public double calculateArea() {
		return dimensions[0] * dimensions[1] * Math.PI;
	}

	@Override
	public double calculatePerimeter() {
		if (dimensions[0] == dimensions[1])
			return 2 * Math.PI * dimensions[0];
		else {
			// Use Ramanujan's approximation of the perimeter of an ellipse:
			double h = Math.pow(Math.abs(dimensions[0] - dimensions[1]), 2) / Math.pow((dimensions[0] + dimensions[1]),2);
			double perimApprox = (Math.PI) * (dimensions[0] + dimensions[1]);
			perimApprox *= (1 + (3 * h)/(10 + Math.sqrt(4 - 3 * h)));
			
			return perimApprox;
		} 
	}
	
	@Override
	public void drawShape(Graphics g) {
		g.setColor(shapeColor);
		g.fillOval(location[0], location[1], dimensions[0]*2, dimensions[1]*2);
	}
	
	@Override
	public void setDim(int[] newDim) {
//		if (newDim[0] > newDim[1]) {
//			dimensions[0] = newDim[0]/2;
//			dimensions[1] = newDim[1]/2;
//		}
//		else {
//			dimensions[0] = newDim[1];
//			dimensions[1] = newDim[0]/2;		
//		}
		
		dimensions[0] = newDim[0]/2;
		dimensions[1] = newDim[1]/2;
	}
	
	public void setDim(int a, int b) {
//		if (a > b) {
//			dimensions[0] = a/2;
//			dimensions[1] = b/2;
//		}
//		else {
//			dimensions[0] = b/2;
//			dimensions[1] = a/2;		
//		}
		
		dimensions[0] = a/2;
		dimensions[1] = b/2;
	}

	@Override
	public void setLoc(int[] newLoc) {
		location[0] = newLoc[0];
		location[1] = newLoc[1];
	}
}
