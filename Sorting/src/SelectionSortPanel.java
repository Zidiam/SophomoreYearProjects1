/*
 * SelectionSortPanel.java -- The GUI for selection sort
 * Jason Melnik
 * 9/19/2019
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SelectionSortPanel extends JPanel{
	private JButton enter, randomize;
	private JLabel timeL, elementsL, comparisonsL, error, delayL;
	private JTextField elements, delayT;
	private static Scanner scan = new Scanner(System.in);
	private long time = 0;
	private int comparisons = 0;
	private int delay = 0;
	private int oldelem = 100;
	private Integer[] array;
	private int Gwidth, Gheight, Gx, Gy;
	private Rectangle Grect, Srect;
	
	SelectionSortPanel(){
		Gwidth = 950;//arr.length;
		Gheight = 425;
		Gx = 50;
		Gy = 525;
		Grect = new Rectangle(50, 100, 1000, 700);
		Srect = new Rectangle(0, 0, 1000, 50);
		
		setBackground(Color.WHITE);
		
		addButtons();
		repaint();
		
    	setLayout(null);
    
	}
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		page.drawLine(25, 75, 975, 75);
		
		page.setColor(Color.BLUE);
		page.drawLine(50, 100, 50, 525);
		page.drawLine(50, 525, 950, 525);
		page.setColor(Color.MAGENTA);
		page.drawString("100", 25, 95);
		page.drawString("Elements", 450, 550);
		
		
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
	
	public void addButtons() {
		this.removeAll();
		enter = new JButton("Enter");
		randomize = new JButton("Random");
		timeL = new JLabel("Time: " + time + " ms");
		elements = new JTextField("100");
		delayT = new JTextField("0");
		elementsL = new JLabel("Number of Elements(2-300):");
		comparisonsL = new JLabel("Comparisons:" + comparisons);
		error = new JLabel("");
		delayL = new JLabel("Delay(ms):");
		
		error.setForeground(Color.red);
		
		delayL.setBounds(300, 0, 150, 25);
		error.setBounds(0, 475, 800, 25);
		enter.setBounds(614, 25, 75, 25);
		randomize.setBounds(600, 0, 100, 25);
		timeL.setBounds(0, 0, 800, 25);
		elements.setBounds(375, 25, 150, 25);
		delayT.setBounds(375, 0, 150, 25);
		comparisonsL.setBounds(0, 25, 200, 25);
		elementsL.setBounds(215, 25, 200, 25);
		
    	enter.addActionListener(new ButtonListener());
    	randomize.addActionListener(new ButtonListener());
    	
    	add(error);
    	add(delayL);
    	add(randomize);
    	add(delayT);
    	add(enter);
    	add(timeL);
    	add(elements);
    	add(elementsL);
    	add(comparisonsL);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == enter) {
				comparisons = 0;
				comparisonsL.setText("Comparisons:");
				time = 0;
				timeL.setText("Time:");
				delay = Integer.parseInt(delayT.getText());
				if(array == null || oldelem != Integer.parseInt(elements.getText())) {
					array = randomInts(Integer.parseInt(elements.getText()));
					oldelem = Integer.parseInt(elements.getText());
				}
				paintArray(array);
				long startTime = System.nanoTime();
				selectionSort(array);
				long endTime = System.nanoTime();
				long duration = (endTime - startTime)/1000000;
				time = duration;
				timeL.setText("Time: " + duration + " ms");
				
			}
			if(event.getSource() == randomize) {
				array = randomInts(Integer.parseInt(elements.getText()));
				paintArray(array);
			}
		}
    }
	
	public void paintArray(Integer[] arr) {
		this.paintImmediately(Grect);
		int temp = Gx;
		
		for(Integer z : arr) {
			getGraphics().drawRect(Gx, Gy-z, Gwidth/arr.length, z);
			Gx += Gwidth/arr.length;
		}
		
		Gx = temp;
		
		try {
			TimeUnit.MILLISECONDS.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public <T extends Comparable<T>> 
	void selectionSort(Integer[] data)
	{
		int min;

		for (int index = 0; index < data.length - 1; index++)
		{
			min = index;
			for (int scan = index + 1; scan < data.length; scan++) {
				comparisons ++;
				comparisonsL.setText("Comparisons: " + comparisons);
				this.paintImmediately(new Rectangle(0, 0, 300, 50));
				
				if (data[scan].compareTo(data[min]) < 0) {
					min = scan;
				}
			}
			
			swap(data, min, index);
			
			paintArray(data);
		}
	}

	/**
	 * Swaps to elements in an array. Used by various sorting algorithms.
	 * 
	 * @param data   the array in which the elements are swapped
	 * @param index1 the index of the first element to be swapped
	 * @param index2 the index of the second element to be swapped
	 */
	private <T extends Comparable<T>> 
	void swap(Integer[] data, int index1, int index2)
	{
		Integer temp = data[index1];
		data[index1] = data[index2];
		data[index2] = temp;
	}
	
	private static Random rand = new Random();
	
	private static Integer[] randomInts(int ammount) {
		Integer[] temp = new Integer[ammount];
		
		for(int x = 0; x < temp.length; x++) {
			temp[x] = (rand.nextInt(400) + 25);
		}
		
		return temp;
	}
	
}
