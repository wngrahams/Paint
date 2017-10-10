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
	
	public DrawPanel() {
		setDoubleBuffered(true);
		
		drawColor = Color.BLACK;
		shapeType = Shape.RECTANGLE;
		drawShape = new Rectangle();

		startPos = new int [2];
		dim = new int [2];
		
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
		else if (Shape.TRIANGLE == shapeType)
			drawShape = new Triangle();
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		createShape();
		startPos[0] = e.getX();
		startPos[1] = e.getY();
		drawShape.setLoc(startPos);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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
		dim[0] = e.getX() - startPos[0];
		dim[1] = e.getY() - startPos[1];
		
		drawShape.setDim(dim);
		repaint();
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
