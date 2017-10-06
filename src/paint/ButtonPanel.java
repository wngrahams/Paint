package paint;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import shapes.*;

@SuppressWarnings("serial")
public class ButtonPanel extends JPanel implements ActionListener {
	
	private Color selectedColor;
	private Shape selectedShape;
		
	private JButton areaButton;
	private JButton colorButton;
	private JButton lineButton;
	private JButton ovalButton;
	private JButton perimeterButton;
	private JButton rectangleButton;
	private JButton triangleButton;
	
	private ArrayList<DrawListener> drawListeners = new ArrayList<DrawListener>(); 
	
	private ShapesList shapes;

	public ButtonPanel() {
		setDoubleBuffered(true);
		
		shapes = new ShapesList();
		selectedColor = Color.BLACK;
		
		initializePanel();
		initializeButtons();
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Use an interface for this?
		if (e.getSource() == areaButton) {
			String area = "Total Area: " + shapes.getTotalArea();
			JOptionPane.showMessageDialog(null, area, "Total Area", JOptionPane.PLAIN_MESSAGE);
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
		}
		else if (e.getSource() == lineButton) {
			selectedShape = new Line();
			for (DrawListener dl : drawListeners)
				dl.shapeChanged(selectedShape);
		}
		else if (e.getSource() == ovalButton) {
			selectedShape = new Oval();
			for (DrawListener dl : drawListeners)
				dl.shapeChanged(selectedShape);
		}
		else if (e.getSource() == perimeterButton) {
			String perimeter = "Total perimeter: " + shapes.getTotalPerimeter();
			JOptionPane.showMessageDialog(null, perimeter, "Total Perimeter", JOptionPane.PLAIN_MESSAGE);
		}
		else if (e.getSource() == rectangleButton) {
			selectedShape = new Rectangle();
			for (DrawListener dl : drawListeners)
				dl.shapeChanged(selectedShape);
		}
		else if (e.getSource() == triangleButton) {
			selectedShape = new Triangle();
			// TODO: Clean these up by not saving selectedShape?
			for (DrawListener dl : drawListeners)
				dl.shapeChanged(selectedShape);
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
		
		lineButton = new JButton(new ImageIcon("res/line.png"));
		ovalButton = new JButton(new ImageIcon("res/oval.png"));
		rectangleButton = new JButton(new ImageIcon("res/rectangle.png"));
		triangleButton = new JButton(new ImageIcon("res/triangle.png"));
		
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
}
