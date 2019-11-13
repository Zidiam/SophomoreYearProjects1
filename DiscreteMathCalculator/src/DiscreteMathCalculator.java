/*
 * DiscreteMathCalculator.java -- The main calculator gui class
 * Jason Melnik
 * 9/19/2019
 */

import javax.swing.JFrame;

public class DiscreteMathCalculator {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Discrete Math Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new DMC_Panel());
		
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
