package paint;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import shapes.*;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel implements DrawListener, MouseListener, MouseMotionListener {

	private Color drawColor;
	private Shape drawShape;
	private Shape shapeType;

	private int[] startPos;
	private int[] dim;
	
	private ShapesList shapesList;
	
	private int triCounter = 0;
	private int[] triPoints;
	
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
	
	private void initializePanel() {
		setBackground(Color.WHITE);
		
		addMouseListener(this);
	    addMouseMotionListener(this);
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
		
		drawShape.setColor(drawColor);
	}
	
	@Override
	public void paint(Graphics g){
		g.setColor(drawColor);
		
		// draw shapes
		for (int i=0; i<shapesList.size(); i++)
			shapesList.get(i).drawShape(g);;
		
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
		if (Shape.TRIANGLE != shapeType) {
			createShape();
			startPos[0] = e.getX();
			startPos[1] = e.getY();
			drawShape.setLoc(startPos);
			repaint();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (Shape.TRIANGLE != shapeType)
			shapesList.add(drawShape);
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
		if (Shape.TRIANGLE != shapeType) {
			
//			if (e.getX() < startPos[0]) {
//				int oldStartX = startPos[0];
//				startPos[0] = e.getX();
//				dim[0] = oldStartX - startPos[0];
//			}
//			else
//				dim[0] = e.getX() - startPos[0];
//			
//			if (e.getX() < startPos[1]) {
//				int oldStartY = startPos[1];
//				startPos[1] = e.getY();
//				dim[1] = oldStartY - startPos[1];
//			}
//			else
//				dim[1] = e.getY() - startPos[1];
			
			dim[0] = e.getX() - startPos[0];
			dim[1] = e.getY() - startPos[1];
			
//			System.out.println(dim[0] + ", " + dim[1]);
			
			drawShape.setDim(dim);
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void colorChanged(Color newColor) {
		drawColor = newColor;
	}

	@Override
	public void shapeChanged(Shape newShape) {
		shapeType = newShape;
	}

}
