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
		return Math.abs(dimensions[0] * dimensions[1] * Math.PI);
	}

	@Override
	public double calculatePerimeter() {
		if (dimensions[0] == dimensions[1])
			return Math.abs(2 * Math.PI * dimensions[0]);
		else {
			// Use Ramanujan's approximation of the perimeter of an ellipse:
			double hNumerator = Math.pow(Math.abs(Math.abs(dimensions[0]) - Math.abs(dimensions[1])), 2);
			double hDenominator = Math.pow((Math.abs(dimensions[0]) + Math.abs(dimensions[1])),2);
			double h = hNumerator / hDenominator;
			double perimApprox = (Math.PI) * (Math.abs(dimensions[0]) + Math.abs(dimensions[1]));
			perimApprox *= (1 + (3 * h)/(10 + Math.sqrt(4 - 3 * h)));
			
			return perimApprox;
		} 
	}
	
	@Override
	public boolean contains(int x, int y) {
		// find the origin of the ellipse at (h, k):
		int h = location[0] + dimensions[0];
		int k = location[1] + dimensions[1];
		
		// plug in x and y into the equation of an ellipse to see if they are contained within the bounds
		double lhs = Math.pow(dimensions[1], 2) * Math.pow((x - h), 2) + 
				Math.pow(dimensions[0], 2) * Math.pow((y - k), 2);
		double rhs = Math.pow(dimensions[0], 2) * Math.pow(dimensions[1], 2);
		
		return (lhs <= rhs);
	}
	
	@Override
	public void drawShape(Graphics g) {
		g.setColor(shapeColor);
		
		int[] drawStart = new int [location.length];
		int[] drawDim = new int [dimensions.length];
		
		for (int i=0; i<dimensions.length; i++) {
			if (dimensions[i] < 0) {
				drawStart[i] = location[i] + (dimensions[i]*2);
				drawDim[i] = -1 * dimensions[i] ;
			}
			else {
				drawStart[i] = location[i];
				drawDim[i] = dimensions[i];
			}
		}
		
		g.fillOval(drawStart[0], drawStart[1], drawDim[0]*2, drawDim[1]*2);
	}
	
	@Override
	public void setDim(int[] newDim) {
		dimensions[0] = newDim[0]/2;
		dimensions[1] = newDim[1]/2;
	}
	
	public void setDim(int a, int b) {
		dimensions[0] = a/2;
		dimensions[1] = b/2;
	}

	@Override
	public void setLoc(int[] newLoc) {
		location[0] = newLoc[0];
		location[1] = newLoc[1];
	}
}
