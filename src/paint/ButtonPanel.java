package paint;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import paint.DrawPanel.ShapeDrawnListener;
import shapes.*;

/*
 * @author Graham Stubbs (wgs11) and Cooper Logerfo (cml264)
 */
@SuppressWarnings("serial")
public class ButtonPanel extends JPanel implements ActionListener, ShapeDrawnListener {
	
	private Color selectedColor;
	
	private JButton areaButton;
	private JButton colorButton;
	private JToggleButton lineButton;
	private JToggleButton ovalButton;
	private JButton perimeterButton;
	private JToggleButton rectangleButton;
	private JToggleButton triangleButton;

	private ArrayList<DrawListener> drawListeners = new ArrayList<DrawListener>(); 
	private ButtonGroup shapeButtons = new ButtonGroup();
	
	public ButtonPanel() {
		setDoubleBuffered(true);
		
		new ShapesList();
		selectedColor = Color.BLACK;
		
		initializePanel();
		initializeButtons();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == areaButton) {
			for (DrawListener dl : drawListeners)
				dl.calculateArea();
			
			resetCursor();
		}
		else if (e.getSource() == colorButton) {
			Color oldColor = selectedColor;
			selectedColor = JColorChooser.showDialog(null, "Choose a color", selectedColor);
			if (null == selectedColor || oldColor == selectedColor)
				selectedColor = oldColor;
			else {
				changeColorIcon(selectedColor);
				for (DrawListener dl : drawListeners)
					dl.colorChanged(selectedColor);
			}
			resetCursor();
		}
		else if (e.getSource() == lineButton) {
			for (DrawListener dl : drawListeners)
				dl.shapeChanged(Shape.LINE);
		}
		else if (e.getSource() == ovalButton) {
			for (DrawListener dl : drawListeners)
				dl.shapeChanged(Shape.OVAL);
		}
		else if (e.getSource() == perimeterButton) {
			for (DrawListener dl : drawListeners)
				dl.calculatePerimeter();
			
			resetCursor();
		}
		else if (e.getSource() == rectangleButton) {
			for (DrawListener dl : drawListeners)
				dl.shapeChanged(Shape.RECTANGLE);
		}
		else if (e.getSource() == triangleButton) {
			for (DrawListener dl : drawListeners)
				dl.shapeChanged(Shape.TRIANGLE);
		}
		
	}
	
	public void addDrawListener(DrawListener dl) {
		drawListeners.add(dl);
	}
	
	private void changeColorIcon(Color newColor) {
		BufferedImage img = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
		Graphics2D gr = img.createGraphics();
		
		gr.setColor(newColor);
		gr.fillRect(0, 0, 20, 20);
		img.flush();
		
		colorButton.setIcon(new ImageIcon(img));
	}
	
	private void initializeButtons() {
		areaButton = new JButton("Get Area");
		perimeterButton = new JButton("Get Perimeter");
		
		// get absolute path of images
		lineButton = new JToggleButton(new ImageIcon(this.getClass().getResource("/line.png")));
		ovalButton = new JToggleButton(new ImageIcon(this.getClass().getResource("/oval.png")));
		rectangleButton = new JToggleButton(new ImageIcon(this.getClass().getResource("/rectangle.png")));
		triangleButton = new JToggleButton(new ImageIcon(this.getClass().getResource("/triangle.png")));
		
		shapeButtons.add(lineButton);
		shapeButtons.add(ovalButton);
		shapeButtons.add(rectangleButton);
		shapeButtons.add(triangleButton);
				
		colorButton = new JButton("Choose Color");
		changeColorIcon(selectedColor);
		colorButton.setHorizontalTextPosition(SwingConstants.LEFT);
		
		areaButton.addActionListener(this);
		colorButton.addActionListener(this);
		lineButton.addActionListener(this);
		ovalButton.addActionListener(this);
		perimeterButton.addActionListener(this);
		rectangleButton.addActionListener(this);
		triangleButton.addActionListener(this);

		lineButton.setToolTipText("Press and hold on panel, then drag.");
		ovalButton.setToolTipText("Press and hold on panel, then drag.");
		rectangleButton.setToolTipText("Press and hold on panel, then drag.");
		triangleButton.setToolTipText("Click on panel 3 times, once for each corner.");
		
		areaButton.setFocusable(false);
		colorButton.setFocusable(false);
		lineButton.setFocusable(false);
		ovalButton.setFocusable(false);
		perimeterButton.setFocusable(false);
		rectangleButton.setFocusable(false);
		triangleButton.setFocusable(false);

		add(rectangleButton);
		add(ovalButton);
		add(triangleButton);
		add(lineButton);
		add(colorButton);
		add(areaButton);
		add(perimeterButton);
	}
	
	private void initializePanel() {
		setBackground(Color.LIGHT_GRAY);
	    setLayout(new GridLayout(1, 0));
	    setBorder(new LineBorder(Color.DARK_GRAY));
	}
	
	private void resetCursor() {
		// reset cursor so cursor changes still work:
		try {
			Robot bot = new Robot();
			int oldX = MouseInfo.getPointerInfo().getLocation().x;
			int oldY = MouseInfo.getPointerInfo().getLocation().y;
			bot.mouseMove(0, 0);
			bot.mouseMove(oldX, oldY);
		} catch (AWTException ex) {
			// do nothing
		}
	}

	// deselect all toggleButtons when a shape is drawn
	@Override
	public void shapeDrawn() {
		shapeButtons.clearSelection();
	}
}
