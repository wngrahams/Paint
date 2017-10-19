package shapes;

import java.awt.Graphics;
import java.util.Arrays;

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
	public void adjust(int newX, int newY, int direction) {
		int[] oldLoc = Arrays.copyOf(location, location.length);
		
		// TODO: bug where dragging any direction except SE, E, and S slowly changes position 
		
		if (LOCATION_SE == direction)
			setDim(newX - location[0], newY - location[1]);
		else if (LOCATION_NW == direction) {
			setLoc(newX, newY);
			setDim((dimensions[0] + (oldLoc[0] - location[0])), (dimensions[1] + (oldLoc[1] - location[1])));
		}
		else if (LOCATION_SW == direction) {
			setLoc(newX, location[1]);
			setDim((dimensions[0] + (oldLoc[0] - location[0])), (newY - location[1]));
		}
		else if (LOCATION_NE == direction) {
			setLoc(location[0], newY);
			setDim((newX - location[0]), (dimensions[1] + (oldLoc[1] - location[1])));
		}
		else if (LOCATION_N == direction) {
			setLoc(location[0], newY);
			setDim(dimensions[0], (dimensions[1]*2 + (oldLoc[1] - location[1])));
		}
		else if (LOCATION_W == direction) {
			setLoc(newX, location[1]);
			setDim((dimensions[0] + (oldLoc[0] - location[0])), dimensions[1]);
		}
		else if (LOCATION_S == direction)
			setDim(dimensions[0], (newY - location[1]));
		else if (LOCATION_E == direction)
			setDim((newX - location[0]), dimensions[1]);
		else if (LOCATION_MIDDLE == direction)
			setLoc(newX, newY);
	}

	@Override
	public double calculateArea() {
		return Math.abs((dimensions[0]/2.0) * (dimensions[1]/2.0) * Math.PI) / (PIXELS_PER_CM * PIXELS_PER_CM);
	}

	@Override
	public double calculatePerimeter() {
		if (dimensions[0] == dimensions[1])
			return Math.abs(2 * Math.PI * (dimensions[0]/2.0)) / PIXELS_PER_CM;
		else {
			// Use Ramanujan's approximation of the perimeter of an ellipse:
			double hNumerator = Math.pow(Math.abs(Math.abs((dimensions[0]/2.0)) - Math.abs((dimensions[1]/2.0))), 2);
			double hDenominator = Math.pow((Math.abs((dimensions[0]/2.0)) + Math.abs((dimensions[1]/2.0))),2);
			double h = hNumerator / hDenominator;
			double perimApprox = (Math.PI) * (Math.abs((dimensions[0]/2.0)) + Math.abs((dimensions[1]/2.0)));
			perimApprox *= (1 + (3 * h)/(10 + Math.sqrt(4 - 3 * h)));
			
			return perimApprox / PIXELS_PER_CM;
		} 
	}
	
	@Override
	public int contains(int x, int y) {
		// do this to deal with negative dimensions
		resetLocAndDim();
		
		// find the origin of the ellipse at (h, k):
		double h = location[0] + dimensions[0]/2.0;
		double k = location[1] + dimensions[1]/2.0;
		
		// plug in x and y into the equation of an ellipse to see if they are contained within the bounds
		double lhs = Math.pow((dimensions[1]/2.0), 2) * Math.pow((x - h), 2) + 
				Math.pow((dimensions[0]/2.0), 2) * Math.pow((y - k), 2);
		double rhs = Math.pow((dimensions[0]/2.0), 2) * Math.pow((dimensions[1]/2.0), 2);
				
		if (lhs <= rhs) {
			// mouse is in the shape, now determine what part
			if (x <= location[0] +  dimensions[0] * 2/5) {
				if (y <= location[1] + dimensions[1] * 2/5)
					return LOCATION_NW;
				else if (y >= location[1] + dimensions[1] * 3/5) 
					return LOCATION_SW;
				else
					return LOCATION_W;
			}
			else if (x >= location[0] + dimensions[0] * 3/5) {
				if (y <= location[1] + dimensions[1] * 2/5)
					return LOCATION_NE;
				else if (y >= location[1] + dimensions[1] * 3/5) 
					return LOCATION_SE;
				else
					return LOCATION_E;
			}
			else {
				if (y <= location[1] + dimensions[1] * 2/5)
					return LOCATION_N;
				else if (y >= location[1] + dimensions[1] * 3/5) 
					return LOCATION_S;
				else
					return LOCATION_MIDDLE;
			}
		}
			
		return LOCATION_NOT_CONTAINED;
	}
	
	@Override
	public void drawShape(Graphics g) {
		g.setColor(shapeColor);
		
		int[] drawStart = new int [location.length];
		int[] drawDim = new int [dimensions.length];
		
		for (int i=0; i<dimensions.length; i++) {
			if (dimensions[i] < 0) {
				drawStart[i] = location[i] + dimensions[i];
				drawDim[i] = -1 * dimensions[i] ;
			}
			else {
				drawStart[i] = location[i];
				drawDim[i] = dimensions[i];
			}
		}
		
		g.fillOval(drawStart[0], drawStart[1], drawDim[0], drawDim[1]);
	}
	
	private void resetLocAndDim() {
		for (int i=0; i<dimensions.length; i++) {
			if (dimensions[i] < 0) {
				location[i] = location[i] + dimensions[i];
				dimensions[i] = -1 * dimensions[i];
			}
		}
	}
	
	@Override
	public void setDim(int[] newDim) {
		dimensions[0] = newDim[0];
		dimensions[1] = newDim[1];
	}
	
	public void setDim(int a, int b) {
		dimensions[0] = a;
		dimensions[1] = b;
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
