import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

public class GraphSymmetryPanel extends JPanel{
	
	private final int WIDTH = 1250, HEIGHT = 625;
	private int speed = 75;
	private Timer timer;
	private GraphPanel graphPanel;
	private JScrollPane scrollPane = new JScrollPane();
	private GraphSetupPanel graphsetupPanel;
	private JButton setupButton, createButton;
	
	public GraphSymmetryPanel() {
		addKeyListener(new DirectionListener());
		timer = new Timer(speed, new ReboundListener());
		
		setLayout(new BorderLayout());
		
		graphsetupPanel = new GraphSetupPanel();
		graphPanel = new GraphPanel();
		setupgraphPanel();
		setupButtons();
		//setLayout(null);
		
		
		setBackground(Color.green);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		timer.start();
		
	}
	
	public void setupgraphPanel() {
		remove(scrollPane);
		
		scrollPane = new JScrollPane(graphsetupPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //scrollPane.setBounds(0, 50, 1350, 625);
    	
        add(scrollPane, BorderLayout.CENTER);
        this.updateUI();
	}
	
	public void creategraphPanel() {
		remove(scrollPane);
		
		graphPanel.setuppointList(graphsetupPanel.getpointList(), graphsetupPanel.getVerticies());
		scrollPane = new JScrollPane(graphPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //scrollPane.setBounds(0, 50, 1350, 625);
    	
        add(scrollPane, BorderLayout.CENTER);
      	this.updateUI();
	}
	
	public void setupButtons() {
		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout());
		setupButton = new JButton("Setup");
		createButton = new JButton("Create");
		
		setupButton.addActionListener(new ButtonListener());
		createButton.addActionListener(new ButtonListener());
		
		//setupButton.setBounds(0, 0, 75, 25);
		//createButton.setBounds(100, 0, 75, 25);
		
		buttons.add(setupButton, BorderLayout.WEST);
		buttons.add(createButton, BorderLayout.EAST);
		add(buttons, BorderLayout.NORTH);
	}
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == setupButton) {
				setupgraphPanel();
			}
			if(event.getSource() == createButton) {
				creategraphPanel();
			}
		}
	}
	
	private class DirectionListener implements KeyListener{
		public void keyPressed(KeyEvent event) {
			if(event.getKeyCode() == KeyEvent.VK_A) {
				//do something
			}
		}
		public void keyTyped(KeyEvent event) {}
		public void keyReleased(KeyEvent event) {}
	}
	
	private class ReboundListener implements ActionListener
	{
		//--------------------------------------------------------------
		//  Updates the position of the image and possibly the direction
		//  of movement whenever the timer fires an action event.
		//--------------------------------------------------------------
		public void actionPerformed(ActionEvent event)
		{
			repaint();
			//happens over and over again
		}
	}
}
	
	

