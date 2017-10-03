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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Drawer extends JFrame {
	
	private ButtonPanel buttonPanel;
	private DrawPanel drawingPanel;

	public Drawer() {
		initializePanels();
		
		setVisible(true);
	}
	
	private void initializePanels() {
		setTitle("Paint");
		setSize(1200, 750);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	    setLayout(new BorderLayout());
	    
	    //Button panel
	    buttonPanel = new ButtonPanel();
	    add(buttonPanel, BorderLayout.SOUTH);
	    
	    //Drawing panel
	    drawingPanel = new DrawPanel();
	    drawingPanel.setBackground(Color.WHITE);
	    
	    add(drawingPanel, BorderLayout.CENTER);
	}

}
