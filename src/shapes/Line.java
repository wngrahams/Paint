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
	public int contains(int x, int y) {
		if (x >= Math.min(location[0], location[0] + dimensions[0]) && 
				x <= Math.max(location[0], location[0] + dimensions[0]) &&
				y >= Math.min(location[1], location[1] + dimensions[1]) && 
				y <= Math.max(location[1], location[1] + dimensions[1])) {
			
			if (dimensions[0] != 0) {
				double slope = (dimensions[1] + 0.0)/dimensions[0];
				double intercept = ((location[0] * dimensions[1] / dimensions[0]) - location[1]) * -1;
				
				if ((slope*x + intercept) <= y + 3 && (slope*x + intercept) >= y - 3)
					return LOCATION_MIDDLE;
			}
			else if (x <= location[0] + 3 && x >= location[0] - 3)
				return LOCATION_MIDDLE;
		}
		return LOCATION_NOT_CONTAINED;
	}
	
	@Override
	public void drawShape(Graphics g) {
		g.setColor(shapeColor);
		g.drawLine(location[0], location[1], dimensions[0]+location[0], dimensions[1]+location[1]);
	}
	
	@Override
	public void setDim(int[] newDim) {
		dimensions[0] = newDim[0];
		dimensions[1] = newDim[1];
	}
	
	public void setDim(int xDist, int yDist) {
		dimensions[0] = xDist;
		dimensions[1] = yDist;
	}
	
	@Override
	public void setLoc(int[] newLoc) {
		location[0] = newLoc[0];
		location[1] = newLoc[1];
	}
}
