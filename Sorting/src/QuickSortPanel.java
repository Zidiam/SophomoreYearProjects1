/*
 * QuickSortPanel.java -- The GUI for quick sort
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

public class QuickSortPanel extends JPanel{
	private JButton enter, randomize;
	private JLabel timeL, elementsL, comparisonsL, error, delayL;
	private JTextField elements, delayT;
	private static Scanner scan = new Scanner(System.in);
	private long time = 0;
	private int comparisons = 0;
	private int delay = 0;
	private int oldelem = 200;
	private Integer[] array;
	private int Gwidth, Gheight, Gx, Gy;
	private Rectangle Grect, Srect;
	
	QuickSortPanel(){
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
		elements = new JTextField("200");
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
				quickSort(array);
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
	}
	/**
	 * Sorts the specified array of objects using the quick sort algorithm.
	 * 
	 * @param data the array to be sorted
	 */
	public <T extends Comparable<T>> 
	void quickSort(Integer[] data)
	{
		quickSort(data, 0, data.length - 1);
	}

	/**
	 * Recursively sorts a range of objects in the specified array using the
	 * quick sort algorithm. 
	 * 
	 * @param data the array to be sorted
	 * @param min  the minimum index in the range to be sorted
	 * @param max  the maximum index in the range to be sorted
	 */
	private <T extends Comparable<T>> 
	void quickSort(Integer[] data, int min, int max)
	{
		
		if (min < max)
		{
			// create partitions
			int indexofpartition = partition(data, min, max);

			// sort the left partition (lower values)
			quickSort(data, min, indexofpartition - 1);

			// sort the right partition (higher values)
			quickSort(data, indexofpartition + 1, max);
		}
	}
	
	private <T extends Comparable<T>> 
	Integer partition(Integer[] data, int min, int max)
	{
		Integer partitionelement;
		int left, right;
		int middle = (min + max) / 2;

		// use the middle data value as the partition element
		partitionelement = data[middle];
		
		// move it out of the way for now
		swap(data, middle, min);

		left = min;
		right = max;

		while (left < right)
		{
			// search for an element that is > the partition element
			while (left < right && data[left].compareTo(partitionelement) <= 0) {
				comparisons ++;
				comparisonsL.setText("Comparisons: " + comparisons);
				this.paintImmediately(new Rectangle(0, 0, 300, 50));
				left++;
			}
			comparisons ++;
			comparisonsL.setText("Comparisons: " + comparisons);
			this.paintImmediately(new Rectangle(0, 0, 300, 50));

			// search for an element that is < the partition element
			while (data[right].compareTo(partitionelement) > 0) {
				comparisons ++;
				comparisonsL.setText("Comparisons: " + comparisons);
				this.paintImmediately(new Rectangle(0, 0, 300, 50));
				right--;
			}
			comparisons ++;
			comparisonsL.setText("Comparisons: " + comparisons);
			this.paintImmediately(new Rectangle(0, 0, 300, 50));

			// swap the elements
			if (left < right) {
				try {
					TimeUnit.MILLISECONDS.sleep(delay);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				swap(data, left, right);
				paintArray(data);
			}
		}
		
		// move the partition element into place
		try {
			TimeUnit.MILLISECONDS.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		swap(data, min, right);
		paintArray(data);
		return right;
	}
	
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
