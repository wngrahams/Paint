package shapes;

import java.awt.Graphics;
import java.util.Arrays;

/*
 * @author Graham Stubbs (wgs11) and Cooper Logerfo (cml264)
 */
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
		int[] oldLoc = Arrays.copyOf(location, location.length);
		
		if (LOCATION_E == direction) {
			setLoc(newX, newY);
			setDim(dimensions[0] + (oldLoc[0] - location[0]), dimensions[1] + (oldLoc[1] - location[1]));
		}
		else if (LOCATION_W == direction)
			setDim(newX - location[0], newY - location[1]);
		else
			setLoc(newX, newY);
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
				
				// if slope is large, take the inverse of the equation to make comparison more accurate
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
					double distFromPt1 = calculateDist(x, y, location[0], location[1]);
					double distFromPt2 = calculateDist(x, y, location[0] + dimensions[0], location[1] + dimensions[1]);
					double epsilon = calculateDist(location[0], location[1], location[0] + dimensions[0], location[1] + dimensions[1]) * 3.0/10.0;
					
					if ((distFromPt2 - epsilon) < distFromPt1 && distFromPt1 < (distFromPt2 + epsilon))
						return LOCATION_MIDDLE;
					else if (distFromPt1 > distFromPt2)
						return LOCATION_W;
					else 
						return LOCATION_E;
				}
			}
			else if (x <= location[0] + 4 && x >= location[0] - 4) {
				// if slope is not defined && cursor is on the line
				double distFromPt1 = calculateDist(x, y, location[0], location[1]);
				double distFromPt2 = calculateDist(x, y, location[0] + dimensions[0], location[1] + dimensions[1]);
				double epsilon = calculateDist(location[0], location[1], location[0] + dimensions[0], location[1] + dimensions[1]) * 3.0/10.0;
				
				if ((distFromPt2 - epsilon) < distFromPt1 && distFromPt1 < (distFromPt2 + epsilon))
					return LOCATION_MIDDLE;
				else if (distFromPt1 > distFromPt2)
					return LOCATION_W;
				else 
					return LOCATION_E;
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
	
	public void setLoc(int x, int y) {
		location[0] = x;
		location[1] = y;
	}
}
