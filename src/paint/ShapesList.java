package paint;

import java.util.ArrayList;

import shapes.Shape;

public class ShapesList {

	private ArrayList<Shape> listOfShapes;
	
	private double totalPerimeter;
	private double totalArea;
	
	public ShapesList() {
		listOfShapes = new ArrayList<Shape>();
	}
	
	public void add(Shape shapeToAdd) {
		listOfShapes.add(shapeToAdd);
	}
	
	public void add(int index, Shape shapeToAdd) {
		listOfShapes.add(index, shapeToAdd);
	}
	
	public Shape get(int index) {
		return listOfShapes.get(index);
	}

	public double getTotalPerimeter() {
		if (null != listOfShapes) { 
			totalPerimeter = 0;
			
			for (int i=0; i<listOfShapes.size(); i++)
				totalPerimeter += listOfShapes.get(i).calculatePerimeter();
			
			return totalPerimeter;
		}
		
		return 0;
	}
	
	public double getTotalArea() {
		if (null != listOfShapes) {
			totalArea = 0;
			
			for (int i=0; i<listOfShapes.size(); i++)
				totalArea += listOfShapes.get(i).calculateArea();
			
			return totalArea;
		}
		
		return 0;
	}
	
	public void remove(int index) {
		listOfShapes.remove(index);
	}
	
	public int size() {
		return listOfShapes.size();
	}
}
