package shapes;

import java.awt.Graphics;
import java.util.Arrays;

/*
 * @author Graham Stubbs (wgs11) and Cooper Logerfo (cml264)
 */
public class Rectangle extends Shape {
	
	public Rectangle() {
		dimensions = new int[2];
		location = new int[2];
		this.setDim(0, 0);
	}

	public Rectangle(int height, int width) {
		dimensions = new int[2];
		location = new int[2];
		this.setDim(height, width);
	}
	
	public Rectangle(int[] newDim){
		dimensions = new int[2];
		location = new int[2];
		this.setDim(newDim);
	}
	
	@Override
	public void adjust(int newX, int newY, int direction) {
		int[] oldLoc = Arrays.copyOf(location, location.length);
		
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
			setDim(dimensions[0], (dimensions[1] + (oldLoc[1] - location[1])));
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
		return (Math.abs(dimensions[0] * dimensions[1]) / (PIXELS_PER_CM * PIXELS_PER_CM));
	}

	@Override
	public double calculatePerimeter() {
		return ((2 * Math.abs(dimensions[0])) + (2 * Math.abs(dimensions[1]))) / PIXELS_PER_CM;
	}
	
	@Override
	public int contains(int x, int y) {
		// do this to deal with negative dimensions
		resetLocAndDim();
		
		if ((x >= location[0]) && (x <= location[0] + dimensions[0]) 
				&& (y >= location[1]) && (y <= location[1] + dimensions[1])) {
			
			// Mouse is in the shape. Now determine what part of the shape:
			if(x <= location[0] + (dimensions[0]/3)) {
				if (y <= location[1] + (dimensions[1]/3)) 
					return LOCATION_NW;
				else if (y >= location[1] + (dimensions[1] * 2/3))
					return LOCATION_SW;
				else
					return LOCATION_W;
			}
			else if (x >= location[0] + (dimensions[0] * 2/3)) {
				if (y <= location[1] + (dimensions[1]/3)) 
					return LOCATION_NE;
				else if (y >= location[1] + (dimensions[1] * 2/3))
					return LOCATION_SE;
				else
					return LOCATION_E;
			}
			else {
				if (y <= location[1] + (dimensions[1]/3)) 
					return LOCATION_N;
				else if (y >= location[1] + (dimensions[1] * 2/3))
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
				drawDim[i] = -1 * dimensions[i];
			}
			else {
				drawStart[i] = location[i];
				drawDim[i] = dimensions[i];
			}
		}
		
		g.fillRect(drawStart[0], drawStart[1], drawDim[0], drawDim[1]);
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
	
	public void setDim(int height, int width) {
		dimensions[0] = height;
		dimensions[1] = width;
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
