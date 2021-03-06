package paint;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import shapes.*;

/*
 * @author Graham Stubbs (wgs11) and Cooper Logerfo (cml264)
 */
@SuppressWarnings("serial")
public class DrawPanel extends JPanel implements DrawListener, MouseListener, MouseMotionListener {

	private Color drawColor;
	private Shape drawShape;
	private Shape shapeType;
	private int adjustDirection = Shape.LOCATION_NOT_CONTAINED;
	private ShapesList shapesList;
	private int removalIndex = -1;

	private int[] startPos;
	private int[] dim;
	private int[] mouseDiff;
	
	private int triCounter = 0;
	private int[] triPoints;
	
	private ArrayList<ShapeDrawnListener> shapeDrawnListeners = new ArrayList<ShapeDrawnListener>();
	
	public DrawPanel() {
		setDoubleBuffered(true);
		
		drawColor = Color.BLACK;
		shapeType = null;
		drawShape = null;

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
			// if no shape button is selected, set drawShape = null so we can adjust already drawn shapes
			drawShape = null;
			return;
		}
		
		drawShape.setColor(drawColor);
	}
	
	private void determineCursor(int x, int y) {
		// if we're adjusting 
		if (null == shapeType) {
			for (int i=shapesList.size()-1; i >= 0; i--) {
				adjustDirection = shapesList.get(i).contains(x, y);
				if (adjustDirection != Shape.LOCATION_NOT_CONTAINED) {
					// if the mouse is over a drawn shape
					setCursor(Cursor.getPredefinedCursor(adjustDirection));
					break;
				}
				else
					setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
			}
		}
		// otherwise set cursor to crosshair for drawing 
		else if (x < getWidth() && x > 0 && y < getHeight() && y > 0) {
			this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		}
		else
			setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
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
		
		// regular drawing, not adjusting
		if (null != drawShape && null != shapeType) {
			for (int i=0; i<shapesList.size(); i++)
				shapesList.get(i).drawShape(g);
			
			drawShape.drawShape(g);
		}
		else if (null == shapeType) {
			// we are adjusting 
			if (null == drawShape) {
				// nothing has been clicked on yet, just draw all shapes in the list
				for (int i=0; i<shapesList.size(); i++)
					shapesList.get(i).drawShape(g);
			}
			else {
				// a shape has been chosen for adjusting and was removed from the list
				// draw all shapes including this one in their original order
				for (int i=0; i<shapesList.size(); i++) {
					if (i == removalIndex)
						drawShape.drawShape(g);
					shapesList.get(i).drawShape(g);
				}
				if (shapesList.size() == removalIndex)
					drawShape.drawShape(g);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// get the three points of a triangle to draw
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
				drawShape = null;
				shapeType = null;
				repaint();
				triCounter = 0;
				
				for (ShapeDrawnListener sd : shapeDrawnListeners)
					sd.shapeDrawn();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// if shapeType == null, we're adjusting shapes
		if (null == shapeType){
			createShape();
			
			// iterate through the shapesList from back to front to find
			// the most recently drawn shape that the mouse is within
			for (int i=shapesList.size()-1; i >= 0; i--) {
				adjustDirection = shapesList.get(i).contains(e.getX(), e.getY());
				if (adjustDirection != Shape.LOCATION_NOT_CONTAINED) {
					drawShape = shapesList.get(i);
					mouseDiff = Arrays.copyOf(drawShape.getLoc(), drawShape.getLoc().length);

					// this loop makes it so that dragging the shape doesn't jump to the top left
					for (int j=0; j<mouseDiff.length; j++){
						if (j%2 == 0)
							mouseDiff[j] = e.getX() - mouseDiff[j];
						else
							mouseDiff[j] = e.getY() - mouseDiff[j];
					}
					
					// remove shape, re-add it later
					shapesList.remove(i);
					removalIndex = i;

					break;
				}
				else 
					drawShape = null;
			}
		}
		// otherwise, we're drawing a new shape
		else if (Shape.TRIANGLE != shapeType) {
			createShape();
			startPos = new int[2];
			startPos[0] = e.getX();
			startPos[1] = e.getY();
			drawShape.setLoc(startPos);
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// if we're adjusting shapes
		if (null == shapeType) {
			if (null != drawShape) {	
				// add the adjusted shape back into the shapesList
				shapesList.add(removalIndex, drawShape);
				drawShape = null;
				
				repaint();
			}
		}
		else if (Shape.TRIANGLE != shapeType) {
			// add the newly drawn shape into the shapesList
			shapesList.add(drawShape);
			
			shapeType = null;
			drawShape = null;
			
			for (ShapeDrawnListener sd : shapeDrawnListeners)
				sd.shapeDrawn();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		determineCursor(0, 0);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		determineCursor(0, 0);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// if we're adjusting
		if (null == shapeType) {
			if (null != drawShape) {
				// once it's determined that we've successfully clicked on a drawn shape
				if (Shape.LOCATION_MIDDLE != adjustDirection)
					drawShape.adjust(e.getX(), e.getY(), adjustDirection);
				else {
					startPos = new int[mouseDiff.length];
					for (int i=0; i<startPos.length; i++) {
						if (i%2 == 0)
							startPos[i] = e.getX() - mouseDiff[i];
						else
							startPos[i] = e.getY() - mouseDiff[i];
					}
					
					// reset shape location according to mouse being dragged
					drawShape.setLoc(startPos);
				}
				repaint();
			}
		}
		// if we're making a new shape, constantly update dimensions as the new shape is dragged
		else if (Shape.TRIANGLE != shapeType) {
			dim[0] = e.getX() - startPos[0];
			dim[1] = e.getY() - startPos[1];
			
			drawShape.setDim(dim);
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		determineCursor(e.getX(), e.getY());
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
		String area = "Total Area: " + shapesList.getTotalArea() + " cm\u00B2";
		JOptionPane.showMessageDialog(this, area, "Total Area", JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void calculatePerimeter() {
		String perimeter = "Total perimeter: " + shapesList.getTotalPerimeter() + " cm";
		JOptionPane.showMessageDialog(this, perimeter, "Total Perimeter", JOptionPane.PLAIN_MESSAGE);
	}

	
	// small interface to let ButtonPanel know when a shape is drawn
	public interface ShapeDrawnListener {
		void shapeDrawn();
	}
	
	public void addShapeDrawnListener(ShapeDrawnListener sd) {
		shapeDrawnListeners.add(sd);
	}
}
