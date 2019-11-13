/*
 * SortingGUI.java -- The main sorting class for the sorting gui
 * Jason Melnik
 * 9/19/2019
 * Version 2.0
 */

import javax.swing.JFrame;

public class SortingGUI {
	public static void main(String[] args) {
		JFrame frame = new JFrame("SORTING");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new SortingPanel());
		
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
