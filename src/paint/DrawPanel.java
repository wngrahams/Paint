package paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import shapes.Shape;

public class DrawPanel extends JPanel implements DrawListener, MouseListener, MouseMotionListener {

	private Color drawColor;
	private Shape drawShape;
	
	public DrawPanel() {
		setDoubleBuffered(true);
		
		drawColor = Color.BLACK;
		initializePanel();
	}
	
	private void initializePanel() {
		setBackground(Color.WHITE);
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);  // TODO: get rid of this, implement your own
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		drawShape = newShape;
	}

}
