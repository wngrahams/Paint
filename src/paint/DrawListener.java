package paint;

import java.awt.Color;

import shapes.Shape;

/*
 * @author Graham Stubbs (wgs11) and Cooper Logerfo (cml264)
 */
public interface DrawListener {
	void colorChanged(Color newColor);
	void shapeChanged(Shape newShape);
	
	void calculateArea();
	void calculatePerimeter();
}
