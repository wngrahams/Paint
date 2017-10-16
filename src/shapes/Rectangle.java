package shapes;

import java.awt.Graphics;

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
	public double calculateArea() {
		return (Math.abs(dimensions[0] * dimensions[1]) / (PIXELS_PER_CM * PIXELS_PER_CM));
	}

	@Override
	public double calculatePerimeter() {
		return ((2 * Math.abs(dimensions[0])) + (2 * Math.abs(dimensions[1]))) / PIXELS_PER_CM;
	}
	
	@Override
	public int contains(int x, int y) {
		if ((x >= location[0]) && (x <= location[0] + dimensions[0]) 
				&& (y >= location[1]) && (y <= location[1] + dimensions[1])) {
			
			// Mouse is in the shape. Now determine what part of the shape:
			if(x < location[0] + (dimensions[0]/2)) {
				if (y < location[1] + (dimensions[1]/2)) 
					return LOCATION_NW;
				else 
					return LOCATION_SW;
			}
			else {
				if (y < location[1] + (dimensions[1]/2))
					return LOCATION_NE;
				else 
					return LOCATION_SE;
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
}
