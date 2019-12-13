/*
 * PermutationsPanel -- The GUI for permutations
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

public class LexicographicPanel extends JPanel{
	private JButton enter;
	private JLabel instructions, integers, limitL, error;
	private JTextField text, limit;
	private static Scanner scan = new Scanner(System.in);
	
	LexicographicPanel(){
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
			
			int[] permutate = getArrayInt(text.getText());
			
			int len = howMany(permutate);
			
			ArrayList<JLabel> labels = new ArrayList<JLabel>();
			
			int x = 0; 
			int y = 100;
			int limitCounter = 0;
			while(destroy == false && limitCounter < Integer.parseInt(limit.getText())) {
				limitCounter ++;
				try {
					JLabel temp = new JLabel(Algorithms.nextPermS(permutate));
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
		instructions = new JLabel("Enter below a list of integers and then click enter to calculate the next number in lexicographic order! Example: 1 2 3");
		text = new JTextField("1 2 3 4");
		limit = new JTextField("15");
		integers = new JLabel("Enter Integers:");
		limitL = new JLabel("Enter Limit:");
		error = new JLabel("");
		
		error.setForeground(Color.red);
		
		error.setBounds(0, 475, 800, 25);
		enter.setBounds(500, 50, 75, 25);
		instructions.setBounds(0, 0, 800, 25);
		text.setBounds(295, 50, 150, 25);
		limit.setBounds(100, 50, 25, 25);
		limitL.setBounds(25, 50, 100, 25);
		integers.setBounds(200, 50, 100, 25);
		
    	enter.addActionListener(new ButtonListener());
    	
    	add(error);
    	add(enter);
    	add(instructions);
    	add(text);
    	add(limit);
    	add(integers);
    	add(limitL);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == enter) {
				addText();
			}
		}
    }
	
}
