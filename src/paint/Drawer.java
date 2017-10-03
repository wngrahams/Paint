package paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Drawer extends JFrame implements ActionListener {
	
	private ShapesList shapeList = new ShapesList();
	
	private JPanel buttonPanel;
	private JPanel drawingPanel;
	
	private JButton areaButton;
	private JButton colorButton;
	private JButton lineButton;
	private JButton ovalButton;
	private JButton perimeterButton;
	private JButton rectangleButton;
	private JButton triangleButton;
	
	private Color drawColor = Color.BLACK;

	public Drawer() {
		initializePanels();
		initializeButtons();
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Use an interface for this?
		if (e.getSource() == areaButton) {
			String area = "Total Area: " + shapeList.getTotalArea();
			JOptionPane.showMessageDialog(null, area, "Total Area", JOptionPane.PLAIN_MESSAGE);
		}
		else if (e.getSource() == colorButton) {
			Color oldColor = drawColor;
			drawColor = JColorChooser.showDialog(this, "Choose a color", drawColor);
			if (null == drawColor)
				drawColor = oldColor;
			else
				changeColorIcon(drawColor);
			
			// TODO: change button's icon to match
		}
		else if (e.getSource() == lineButton) {
			
		}
		else if (e.getSource() == ovalButton) {
					
		}
		else if (e.getSource() == perimeterButton) {
			String perimeter = "Total perimeter: " + shapeList.getTotalPerimeter();
			JOptionPane.showMessageDialog(null, perimeter, "Total Perimeter", JOptionPane.PLAIN_MESSAGE);
		}
		else if (e.getSource() == rectangleButton) {
			
		}
		else if (e.getSource() == triangleButton) {
			
		}
		
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
		changeColorIcon(drawColor);
		
		areaButton.addActionListener(this);
		colorButton.addActionListener(this);
		lineButton.addActionListener(this);
		ovalButton.addActionListener(this);
		perimeterButton.addActionListener(this);
		rectangleButton.addActionListener(this);
		triangleButton.addActionListener(this);
		
		buttonPanel.add(rectangleButton);
		buttonPanel.add(ovalButton);
		buttonPanel.add(triangleButton);
		buttonPanel.add(lineButton);
		buttonPanel.add(colorButton);
		buttonPanel.add(areaButton);
		buttonPanel.add(perimeterButton);
	}
	
	private void initializePanels() {
		setTitle("Paint");
		setSize(1200, 750);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	    setLayout(new BorderLayout());
	    
	    //Button panel
	    buttonPanel = new JPanel();
	    buttonPanel.setBackground(Color.LIGHT_GRAY);
	    buttonPanel.setLayout(new GridLayout(1, 0));
	    buttonPanel.setBorder(new LineBorder(Color.DARK_GRAY));
	    
	    add(buttonPanel, BorderLayout.SOUTH);
	    
	    
	    //Drawing panel
	    drawingPanel = new JPanel();
	    drawingPanel.setBackground(Color.WHITE);
	    
	    add(drawingPanel, BorderLayout.CENTER);
	}

}
