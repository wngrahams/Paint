package paint;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Drawer extends JFrame {
	
	private ButtonPanel buttonPanel;
	private DrawPanel drawingPanel;

	public Drawer() {
		initializePanels();
		this.setBackground(Color.WHITE);
		
		buttonPanel.addDrawListener(drawingPanel);
		
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
	    add(drawingPanel, BorderLayout.CENTER);
	}

}
