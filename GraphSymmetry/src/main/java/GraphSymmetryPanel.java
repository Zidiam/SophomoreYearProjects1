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
	private GraphSetupPanel graphsetupPanel, graphsetupPanel2;
	private JButton setupButton, createButton, symmetryButton;
	private JPanel graphs;
	private GraphSymmetryTestPanel symmetryTestPanel;
	public GraphSymmetryPanel() {
		addKeyListener(new DirectionListener());
		timer = new Timer(speed, new ReboundListener());
		
		setLayout(new BorderLayout());
		
		graphs = new JPanel();
		graphsetupPanel = new GraphSetupPanel();
		graphsetupPanel2 = new GraphSetupPanel();
		symmetryTestPanel = new GraphSymmetryTestPanel(graphsetupPanel.getGraphVertex(), graphsetupPanel2.getGraphVertex());
		setupgraphPanel();
		setupButtons();
		//setLayout(null);
		
		
		setBackground(Color.green);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		timer.start();
		
	}
	
	public void setupgraphPanel() {
		remove(graphs);
		remove(symmetryTestPanel);
		
		graphs = new JPanel();
		graphs.setLayout(new GridLayout());
		
		JScrollPane scrollPane = new JScrollPane();
		JScrollPane scrollPane2 = new JScrollPane();
		
		scrollPane = new JScrollPane(graphsetupPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        scrollPane2 = new JScrollPane(graphsetupPanel2);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        //scrollPane.setBounds(0, 50, 1350, 625);
    	
        graphs.add(scrollPane);
        graphs.add(scrollPane2);
        add(graphs, BorderLayout.CENTER);
        this.updateUI();
	}
	
	public void creategraphPanel() {
		remove(graphs);
		remove(symmetryTestPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		JScrollPane scrollPane2 = new JScrollPane();
		GraphPanel graphPanel = new GraphPanel();
		GraphPanel graphPanel2 = new GraphPanel();
		
		graphs = new JPanel();
		graphs.setLayout(new GridLayout());
		
		graphPanel.setuppointList(graphsetupPanel.getpointList(), graphsetupPanel.getVerticies());
		scrollPane = new JScrollPane(graphPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	
        graphPanel2.setuppointList(graphsetupPanel2.getpointList(), graphsetupPanel2.getVerticies());
		scrollPane2 = new JScrollPane(graphPanel2);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        graphs.add(scrollPane);
        graphs.add(scrollPane2);
        add(graphs, BorderLayout.CENTER);
      	this.updateUI();
	}
	
	public void setupButtons() {
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout());
		setupButton = new JButton("Setup");
		createButton = new JButton("Create");
		symmetryButton = new JButton("Symmetry");
		
		setupButton.addActionListener(new ButtonListener());
		createButton.addActionListener(new ButtonListener());
		symmetryButton.addActionListener(new ButtonListener());
		
		//setupButton.setBounds(0, 0, 75, 25);
		//createButton.setBounds(100, 0, 75, 25);
		buttons.setBackground(Color.green);
		buttons.add(setupButton);
		buttons.add(createButton);
		buttons.add(symmetryButton);
		add(buttons, BorderLayout.NORTH);
	}
	
	public void createsymmetryPanel() {
		remove(symmetryTestPanel);
		
		symmetryTestPanel = new GraphSymmetryTestPanel(graphsetupPanel.getGraphVertex(), graphsetupPanel2.getGraphVertex());
		remove(graphs);
		
		add(symmetryTestPanel, BorderLayout.CENTER);
		this.updateUI();
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
			if(event.getSource() == symmetryButton) {
				createsymmetryPanel();
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
	
	

