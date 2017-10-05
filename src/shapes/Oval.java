package shapes;

public class Oval extends Shape {

	private double majorRadius;
	private double minorRadius;
	
	public Oval() {
		minorRadius = 0;
		majorRadius = 0;
	}
	
	public Oval(double a, double b) {
		if (a > b) {
			majorRadius = a; 
			minorRadius = b;
		}
		else {
			majorRadius = b;
			minorRadius = a;
		}
	}

	@Override
	public double calculateArea() {
		return majorRadius * minorRadius * Math.PI;
	}

	@Override
	public double calculatePerimeter() {
		if (majorRadius == minorRadius)
			return 2* Math.PI * majorRadius;
		else {
			// Use Ramanujan's approximation of the perimeter of an ellipse:
			double h = Math.pow((majorRadius - minorRadius),2) / Math.pow((majorRadius + minorRadius),2);
			double perimApprox = (Math.PI) * (majorRadius + minorRadius);
			perimApprox *= (1 + (3 * h)/(10 + Math.sqrt(4 - 3 * h)));
			
			return perimApprox;
		} 
	}

	@Override
	public void drawShape() {
		// TODO Auto-generated method stub

	}

}
