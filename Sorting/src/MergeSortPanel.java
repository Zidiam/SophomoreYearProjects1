/*
 * MergeSortPanel.java -- The GUI for merge sorting
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

public class MergeSortPanel extends JPanel{
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
	
	MergeSortPanel(){
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
				mergeSort(array);
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
	
	/**
	 * Sorts the specified array of objects using the merge sort
	 * algorithm.
	 *
	 * @param data the array to be sorted
	 */
	public <T extends Comparable<T>>
	void mergeSort(Integer[] data)
	{
		mergeSort(data, 0, data.length - 1);
	}

	/**
	 * Recursively sorts a range of objects in the specified array using the
	 * merge sort algorithm.
	 *
	 * @param data the array to be sorted
	 * @param min  the index of the first element 
	 * @param max  the index of the last element
	 */
	private <T extends Comparable<T>>
	void mergeSort(Integer[] data, int min, int max)
	{
		if (min < max)
		{
			int mid = (min + max) / 2;
			mergeSort(data, min, mid);
			mergeSort(data, mid+1, max);
			merge(data, min, mid, max);
		}
	}

	/**
	 * Merges two sorted subarrays of the specified array.
	 *
	 * @param data the array to be sorted
	 * @param first the beginning index of the first subarray 
	 * @param mid the ending index fo the first subarray
	 * @param last the ending index of the second subarray
	 */
	@SuppressWarnings("unchecked")
	private <T extends Comparable<T>>
	void merge(Integer[] data, int first, int mid, int last)
	{
		Integer[] temp = new Integer[data.length];

		int first1 = first, last1 = mid;  // endpoints of first subarray
		int first2 = mid + 1, last2 = last;  // endpoints of second subarray
		int index = first1;  // next index open in temp array

		//  Copy smaller item from each subarray into temp until one
		//  of the subarrays is exhausted
		while (first1 <= last1 && first2 <= last2)
		{
			if (data[first1].compareTo(data[first2]) < 0)
			{
				comparisons ++;
				comparisonsL.setText("Comparisons: " + comparisons);
				this.paintImmediately(new Rectangle(0, 0, 300, 50));
				temp[index] = data[first1];
				first1++;
				paintArray(data);
			}
			else
			{
				comparisons ++;
				comparisonsL.setText("Comparisons: " + comparisons);
				this.paintImmediately(new Rectangle(0, 0, 300, 50));
				temp[index] = data[first2];
				first2++;
				paintArray(data);
			}
			index++;
		}

		//  Copy remaining elements from first subarray, if any
		while (first1 <= last1)
		{
			temp[index] = data[first1];
			first1++;
			index++;
			paintArray(data);
		}

		//  Copy remaining elements from second subarray, if any
		while (first2 <= last2)
		{
			temp[index] = data[first2];
			first2++;
			index++;
			paintArray(data);
		}

		//  Copy merged data into original array
		for (index = first; index <= last; index++) {
			data[index] = temp[index];
			paintArray(data);
		}
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
