package paint;

import java.awt.Color;

import shapes.Shape;

public interface DrawListener {
	void colorChanged(Color newColor);
	void shapeChanged(Shape newShape);
}
