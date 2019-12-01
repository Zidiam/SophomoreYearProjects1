import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GraphDataPanel extends JPanel{
	private GraphingPanel graphingPanel;
	
	public GraphDataPanel() {
		this.setPreferredSize(new Dimension(1280, 720));
		
		this.setLayout(new BorderLayout());
		
		graphingPanel = new GraphingPanel();
		
		add(graphingPanel);
	}	
}
