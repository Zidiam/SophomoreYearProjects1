/*
 * rComboPanel.java -- The GUI for r combination permutations
 * Jason Melnik
 * 9/19/2019
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class rComboPanel extends JPanel{
	private JButton enter;
	private JLabel instructions, setL, limitL, error, subsetL;
	private JTextField set, limit, subset;
	private static Scanner scan = new Scanner(System.in);
	
	rComboPanel(){
		setBackground(Color.WHITE);
		
		addButtons();
    	 
    	setLayout(null);
    
	}
	
	public int[] getArrayInt(String str) {
		String[] numbers = str.split(" ");
		int[] fin = new int[numbers.length];
		
		for(int x = 0; x < numbers.length; x++) {
			fin[x] = Integer.parseInt(numbers[x]);
		}
		
		return fin;
	}
	
	public int howMany(int[] fin) {
		return fin.length;
	}
	
	public void addText() {
		try {
			error.setText("");
			boolean destroy = false;
			
			int[] sset = getArrayInt(set.getText());
			int[] ssubset = getArrayInt(subset.getText());
			
			int len = howMany(ssubset);
			
			ArrayList<JLabel> labels = new ArrayList<JLabel>();
			
			int x = 0; 
			int y = 100;
			int limitCounter = 0;
			while(destroy == false && limitCounter < Integer.parseInt(limit.getText())) {
				limitCounter ++;
				try {
					JLabel temp = new JLabel(Algorithms.nextRComboS(ssubset, sset));
					temp.setBounds(x, y + 25, len*25, 25);
					labels.add(temp);
					add(temp);
					y += 25;
					if(y >= 450) {
						x += len*25 - 25;
						y = 100;
					}
				}
				catch(Exception e) {
					destroy = true;
				}
			}
			
			this.update(this.getGraphics());
			
			for(int z = 0; z < labels.size(); z++) {
				remove(labels.get(z));
			}
		}
		catch(Exception e) {
			error.setText("Error: Please Follow Instructions and enter correct Values(Integers)!");
			this.update(this.getGraphics());
		}
	}
	
	public void addButtons() {
		enter = new JButton("Enter");
		instructions = new JLabel("Enter below a list of integers and then click enter to calculate the next r-permutations! Example: 1 2 3 (You can limit permitations)");
		set = new JTextField("1 2 3 4");
		limit = new JTextField("15");
		setL = new JLabel("Enter Set:");
		limitL = new JLabel("Enter Limit:");
		error = new JLabel("");
		subsetL = new JLabel("Enter Subset:");
		subset = new JTextField("1 2 3");
		
		error.setForeground(Color.red);
		
		error.setBounds(0, 475, 800, 25);
		enter.setBounds(650, 50, 75, 25);
		instructions.setBounds(0, 0, 800, 25);
		set.setBounds(215, 50, 150, 25);
		limit.setBounds(100, 50, 25, 25);
		limitL.setBounds(25, 50, 100, 25);
		setL.setBounds(150, 50, 100, 25);
		subset.setBounds(470, 50, 150, 25);
		subsetL.setBounds(385, 50, 100, 25);
		
    	enter.addActionListener(new ButtonListener());
    	
    	add(error);
    	add(enter);
    	add(instructions);
    	add(set);
    	add(limit);
    	add(setL);
    	add(limitL);
    	add(subset);
    	add(subsetL);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == enter) {
				addText();
			}
		}
    }
	
}
