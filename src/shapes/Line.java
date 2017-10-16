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
	public void adjust(int newX, int newY, int direction) {
		// TODO Auto-generated method stub
		
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
		if (x >= Math.min(location[0], location[0] + dimensions[0]) - 4 && 
				x <= Math.max(location[0], location[0] + dimensions[0]) + 4 &&
				y >= Math.min(location[1], location[1] + dimensions[1]) - 4 && 
				y <= Math.max(location[1], location[1] + dimensions[1]) + 4) {
			// in the "box" that this line is a diagonal of
			
			if (dimensions[0] != 0) {
				// if slope is defined
				double slope = (dimensions[1] + 0.0)/dimensions[0];
				double intercept = ((location[0] * dimensions[1] / dimensions[0]) - location[1]) * -1;
				
				int xHolder = x;
				int yHolder = y;
				
				if (Math.abs(slope) > 1) {
					slope = 1 / slope;
					intercept = (0.0 - intercept) * slope;
					int temp = x;
					x = y;
					y = temp;
				}
				
				if ((slope*x + intercept) <= y + 4 && (slope*x + intercept) >= y - 4) {
					x = xHolder;
					y = yHolder;
					// if cursor is on the line
					if (x <= Math.min(location[0], location[0] + dimensions[0]) + Math.abs(dimensions[0])/3) {
						if (y <= Math.min(location[1], location[1] + dimensions[1]) + Math.abs(dimensions[1])/3)
							return LOCATION_N;
						else 
							return LOCATION_E;
					}
					else if (x >= Math.min(location[0], location[0] + dimensions[0]) + Math.abs(dimensions[0])* 2/3) {
						if (y <= Math.min(location[1], location[1] + dimensions[1]) + Math.abs(dimensions[1])/3)
							return LOCATION_N;
						else 
							return LOCATION_E;
					}
					else
						return LOCATION_MIDDLE;
				}
			}
			else if (x <= location[0] + 4 && x >= location[0] - 4) {
				// if slope is not defined && cursor is on the line
				if (dimensions[1] > 0) {
					if (y <= (location[1] + dimensions[1]/3))
						return LOCATION_N;
					else if (y >= (location[1] + dimensions[1]*2/3)) { 
						return LOCATION_E;
					}
					else 
						return LOCATION_MIDDLE;
				}
				else {
					if (y >= (location[1] + dimensions[1]/3))
						return LOCATION_N;
					else if (y <= (location[1] + dimensions[1]*2/3)) { 
						return LOCATION_E;
					}
					else 
						return LOCATION_MIDDLE;
				}
			}
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
