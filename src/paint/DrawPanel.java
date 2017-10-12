package paint;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import shapes.*;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel implements DrawListener, MouseListener, MouseMotionListener {

	private Color drawColor;
	private Shape drawShape;
	private Shape shapeType;

	private int[] startPos;
	private int[] dim;
	private int[] mouseDiff;
	
	private ShapesList shapesList;
	
	private int triCounter = 0;
	private int[] triPoints;
	
	private int removalIndex = -1;
	
	public DrawPanel() {
		setDoubleBuffered(true);
		
		drawColor = Color.BLACK;
		shapeType = Shape.RECTANGLE;
		drawShape = new Rectangle();

		startPos = new int[2];
		dim = new int[2];
		
		shapesList = new ShapesList();
		
		initializePanel();
	}
	
	private void createShape() {
		if (Shape.LINE == shapeType)
			drawShape = new Line();
		else if (Shape.OVAL == shapeType)
			drawShape = new Oval();
		else if (Shape.RECTANGLE == shapeType)
			drawShape = new Rectangle();
		else if (Shape.TRIANGLE == shapeType) {
			drawShape = new Triangle();
			triPoints = new int[6];
			triCounter = 0;
		}
		else {
			drawShape = null;
			return;
		}
		
		drawShape.setColor(drawColor);
	}
	
	private void initializePanel() {
		setBackground(Color.WHITE);
		
		addMouseListener(this);
	    addMouseMotionListener(this);
	}
	
	@Override
	public void paint(Graphics g){
		// draw background
		g.setColor( Color.WHITE ); 
		int w = getWidth();  
		int h = getHeight();
		g.fillRect( 0, 0, w, h ); 
		
		g.setColor(drawColor);
		
		// draw shapes
		for (int i=0; i<shapesList.size(); i++)
			shapesList.get(i).drawShape(g);;
		
		if (null != drawShape) 
			drawShape.drawShape(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (Shape.TRIANGLE == shapeType) {
			if (triCounter == 0)
				createShape();
			
			if (triCounter < 6) {
				triPoints[triCounter] = e.getX();
				triPoints[triCounter + 1] = e.getY();
				triCounter += 2;
			}
			
			if (triCounter >= 6){
				drawShape.setLoc(triPoints);
				shapesList.add(drawShape);
				repaint();
				triCounter = 0;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (null == shapeType){
			createShape();
			for (int i=shapesList.size()-1; i >= 0; i--) {
				if (shapesList.get(i).contains(e.getX(), e.getY())) {
					drawShape = shapesList.get(i);
					mouseDiff = Arrays.copyOf(drawShape.getLoc(), drawShape.getLoc().length);
					mouseDiff[0] = e.getX() - mouseDiff[0];
					mouseDiff[1] = e.getY() - mouseDiff[1];
					
					shapesList.remove(i);
					removalIndex = i;
					
					break;
				}
				else 
					drawShape = null;
			}
		}
		else if (Shape.TRIANGLE != shapeType) {
			createShape();
			startPos[0] = e.getX();
			startPos[1] = e.getY();
			drawShape.setLoc(startPos);
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (null == shapeType) {
			if (null != drawShape) {
				shapesList.add(removalIndex, drawShape);
				drawShape = null;  // comment this out to show other way
				repaint();
			}
		}
		else if (Shape.TRIANGLE != shapeType) {
			shapesList.add(drawShape);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		// TODO: make it so that cursor goes back to crosshair after color is changed
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (null == shapeType) {
			if (null != drawShape) {
				startPos[0] = e.getX() - mouseDiff[0];
				startPos[1] = e.getY() - mouseDiff[1];
				drawShape.setLoc(startPos);
				repaint();
			}
		}
		else if (Shape.TRIANGLE != shapeType) {
			dim[0] = e.getX() - startPos[0];
			dim[1] = e.getY() - startPos[1];
			
			drawShape.setDim(dim);
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (null == shapeType) {
			for (int i=shapesList.size()-1; i >= 0; i--) {
				System.out.println("i: " + i);
				if (shapesList.get(i).contains(e.getX(), e.getY())) {
					setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					break;
				}
				else
					setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			}
		}
		else if (e.getX() < getWidth() && e.getX() > 0 && e.getY() < getHeight() && e.getY() > 0) {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			System.out.println("in frame");
		}
		else
			setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
	}

	@Override
	public void colorChanged(Color newColor) {
		drawColor = newColor;
	}

	@Override
	public void shapeChanged(Shape newShape) {
		shapeType = newShape;
	}

	@Override
	public void calculateArea() {
		String area = "Total Area: " + shapesList.getTotalArea();
		JOptionPane.showMessageDialog(this, area, "Total Area", JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void calculatePerimeter() {
		String perimeter = "Total perimeter: " + shapesList.getTotalPerimeter();
		JOptionPane.showMessageDialog(this, perimeter, "Total Perimeter", JOptionPane.PLAIN_MESSAGE);
	}

}
