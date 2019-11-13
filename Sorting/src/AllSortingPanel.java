/*
 *AllSortingPanel.java -- The GUI for all the sorting
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

public class AllSortingPanel extends JPanel{
	private JButton enter, randomize, Ascending, Descending, enterSS, enterIS, enterQS, enterBS, enterMS,
					randomizeSS, randomizeIS, randomizeQS, randomizeBS, randomizeMS,
					ascendingSS, ascendingIS, ascendingQS, ascendingBS, ascendingMS,
					descendingSS, descendingIS, descendingQS, descendingBS, descendingMS;
	private JLabel timeL, elementsL, comparisonsL, error, state, timeSSL, comparisonSSL, timeISL, comparisonISL,
					timeQSL, timeBSL, timeMSL, comparisonQSL, comparisonBSL, comparisonMSL, 
					stateQSL, stateBSL, stateMSL, stateISL, stateSSL;
	private JTextField elementSS, elementIS, elementQS, elementBS, elementMS;
	private static Scanner scan = new Scanner(System.in);
	private long timeSS, timeIS, timeQS, timeBS, timeMS;
	private int comparisonSS, comparisonIS, comparisonQS, comparisonBS, comparisonMS;
	private int oldelemSS, oldelemIS, oldelemQS, oldelemBS, oldelemMS;
	private Integer[] arraySS, arrayIS, arrayQS, arrayBS, arrayMS;
	
	AllSortingPanel(){
		comparisonSS = comparisonIS = comparisonQS = comparisonBS = comparisonMS = 0;
		timeSS = timeIS = timeQS = timeBS = timeMS = 0;
		oldelemSS = oldelemIS = oldelemQS = oldelemBS = oldelemMS = 100;
		
		arraySS = randomInts(100);
		arrayIS = randomInts(100);
		arrayQS = randomInts(100);
		arrayMS = randomInts(100);
		arrayBS = randomInts(100);
		
		setBackground(Color.WHITE);
		
		addButtons();
		repaint();
		
    	setLayout(null);
    
	}
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		
		page.setColor(Color.BLUE);
		
		//vertical
		page.drawLine(150, 0, 150, 525);
		page.drawLine(300, 0, 300, 525);
		page.drawLine(450, 0, 450, 525);
		page.drawLine(600, 0, 600, 525);
		page.drawLine(750, 0, 750, 525);
		
		//horizontal
		page.drawLine(0, 25, 1000, 25);
		page.drawLine(0, 125, 1000, 125);
		page.drawLine(0, 225, 1000, 225);
		page.drawLine(0, 325, 1000, 325);
		page.drawLine(0, 425, 1000, 425);
		page.drawLine(0, 525, 1000, 525);
		
		page.setColor(Color.RED);
		page.drawString("Selection", 50, 75);
		page.drawString("Insertion", 50, 175);
		page.drawString("Quick", 50, 275);
		page.drawString("Merge", 50, 375);
		page.drawString("Bubble", 50, 475);
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
		enterSS = new JButton("Enter");
		enterIS = new JButton("Enter");
		enterQS = new JButton("Enter");
		enterMS = new JButton("Enter");
		enterBS = new JButton("Enter");
		
		randomize = new JButton("Random");
		randomizeSS = new JButton("Random");
		randomizeIS = new JButton("Random");
		randomizeQS = new JButton("Random");
		randomizeMS = new JButton("Random");
		randomizeBS = new JButton("Random");
		
		Ascending = new JButton("Ascending");
		ascendingSS = new JButton("Ascending");
		ascendingIS = new JButton("Ascending");
		ascendingQS = new JButton("Ascending");
		ascendingMS = new JButton("Ascending");
		ascendingBS = new JButton("Ascending");
		
		Descending = new JButton("Descending");
		descendingSS = new JButton("Descending");
		descendingIS = new JButton("Descending");
		descendingQS = new JButton("Descending");
		descendingMS = new JButton("Descending");
		descendingBS = new JButton("Descending");
		
		timeL = new JLabel("Time: ");
		
		elementSS = new JTextField("100");
		elementIS = new JTextField("100");
		elementQS = new JTextField("100");
		elementMS = new JTextField("100");
		elementBS = new JTextField("100");
		
		elementsL = new JLabel("Number of Elements:");
		comparisonsL = new JLabel("Comparisons:");
		error = new JLabel("");
		state = new JLabel("State:");
		
		timeSSL = new JLabel(timeSS + "");
		timeISL = new JLabel(timeIS + "");
		timeQSL = new JLabel(timeQS + "");
		timeMSL = new JLabel(timeMS + "");
		timeBSL = new JLabel(timeBS + "");
		
		comparisonSSL = new JLabel(comparisonSS + "");
		comparisonISL = new JLabel(comparisonIS + "");
		comparisonQSL = new JLabel(comparisonQS + "");
		comparisonMSL = new JLabel(comparisonMS + "");
		comparisonBSL = new JLabel(comparisonBS + "");
		
		stateSSL = new JLabel("Random");
		stateISL = new JLabel("Random");
		stateQSL = new JLabel("Random");
		stateMSL = new JLabel("Random");
		stateBSL = new JLabel("Random");
		
		error.setForeground(Color.red);
		
		error.setBounds(0, 475, 800, 25);
		
		enter.setBounds(900, 538, 75, 25);
		enterSS.setBounds(835, 25, 75, 25);
		enterIS.setBounds(835, 125, 75, 25);
		enterQS.setBounds(835, 225, 75, 25);
		enterMS.setBounds(835, 325, 75, 25);
		enterBS.setBounds(835, 425, 75, 25);
		
		randomize.setBounds(775, 538, 100, 25);
		randomizeSS.setBounds(822, 50, 100, 25);
		randomizeIS.setBounds(822, 150, 100, 25);
		randomizeQS.setBounds(822, 250, 100, 25);
		randomizeMS.setBounds(822, 350, 100, 25);
		randomizeBS.setBounds(822, 450, 100, 25);
		
		Descending.setBounds(25, 538, 125, 25);
		descendingSS.setBounds(809, 100, 125, 25);
		descendingIS.setBounds(809, 200, 125, 25);
		descendingQS.setBounds(809, 300, 125, 25);
		descendingMS.setBounds(809, 400, 125, 25);
		descendingBS.setBounds(809, 500, 125, 25);
		
		Ascending.setBounds(225, 538, 100, 25);
		ascendingSS.setBounds(822, 75, 100, 25);
		ascendingIS.setBounds(822, 175, 100, 25);
		ascendingQS.setBounds(822, 275, 100, 25);
		ascendingMS.setBounds(822, 375, 100, 25);
		ascendingBS.setBounds(822, 475, 100, 25);
		
		timeL.setBounds(500, 0, 800, 25);
		comparisonsL.setBounds(325, 0, 200, 25);
		elementsL.setBounds(165, 0, 200, 25);
		state.setBounds(650, 0, 200, 25);
		
		timeSSL.setBounds(450, 50, 800, 25);
		timeISL.setBounds(450, 150, 800, 25);
		timeQSL.setBounds(450, 250, 800, 25);
		timeMSL.setBounds(450, 350, 800, 25);
		timeBSL.setBounds(450, 450, 800, 25);
		
		elementSS.setBounds(150, 50, 150, 25);
		elementIS.setBounds(150, 150, 150, 25);
		elementQS.setBounds(150, 250, 150, 25);
		elementMS.setBounds(150, 350, 150, 25);
		elementBS.setBounds(150, 450, 150, 25);
		
		comparisonSSL.setBounds(300, 50, 200, 25);
		comparisonISL.setBounds(300, 150, 200, 25);
		comparisonQSL.setBounds(300, 250, 200, 25);
		comparisonMSL.setBounds(300, 350, 200, 25);
		comparisonBSL.setBounds(300, 450, 200, 25);
		
		stateSSL.setBounds(600, 50, 800, 25);
		stateISL.setBounds(600, 150, 800, 25);
		stateQSL.setBounds(600, 250, 800, 25);
		stateMSL.setBounds(600, 350, 800, 25);
		stateBSL.setBounds(600, 450, 800, 25);
		
    	enter.addActionListener(new ButtonListener());
    	enterSS.addActionListener(new ButtonListener());
    	enterIS.addActionListener(new ButtonListener());
    	enterQS.addActionListener(new ButtonListener());
    	enterMS.addActionListener(new ButtonListener());
    	enterBS.addActionListener(new ButtonListener());
    	
    	randomize.addActionListener(new ButtonListener());
    	randomizeSS.addActionListener(new ButtonListener());
    	randomizeIS.addActionListener(new ButtonListener());
    	randomizeQS.addActionListener(new ButtonListener());
    	randomizeMS.addActionListener(new ButtonListener());
    	randomizeBS.addActionListener(new ButtonListener());
    	
    	Descending.addActionListener(new ButtonListener());
    	descendingSS.addActionListener(new ButtonListener());
    	descendingIS.addActionListener(new ButtonListener());
    	descendingQS.addActionListener(new ButtonListener());
    	descendingMS.addActionListener(new ButtonListener());
    	descendingBS.addActionListener(new ButtonListener());
    	
    	Ascending.addActionListener(new ButtonListener());
    	ascendingSS.addActionListener(new ButtonListener());
    	ascendingIS.addActionListener(new ButtonListener());
    	ascendingQS.addActionListener(new ButtonListener());
    	ascendingMS.addActionListener(new ButtonListener());
    	ascendingBS.addActionListener(new ButtonListener());
    	
    	add(timeSSL);
    	add(timeISL);
    	add(timeQSL);
    	add(timeMSL);
    	add(timeBSL);
    	
    	add(comparisonSSL);
    	add(comparisonISL);
    	add(comparisonQSL);
    	add(comparisonMSL);
    	add(comparisonBSL);
    	
    	add(stateSSL);
    	add(stateISL);
    	add(stateQSL);
    	add(stateMSL);
    	add(stateBSL);
    	
    	add(elementSS);
    	add(elementIS);
    	add(elementQS);
    	add(elementMS);
    	add(elementBS);
    	
    	add(error);
    	
    	add(randomize);
    	add(randomizeSS);
    	add(randomizeIS);
    	add(randomizeQS);
    	add(randomizeMS);
    	add(randomizeBS);
    	
    	add(enter);
    	add(enterSS);
    	add(enterIS);
    	add(enterQS);
    	add(enterMS);
    	add(enterBS);
    	
    	add(Ascending);
    	add(ascendingSS);
    	add(ascendingIS);
    	add(ascendingQS);
    	add(ascendingMS);
    	add(ascendingBS);
    	
    	add(Descending);
    	add(descendingSS);
    	add(descendingIS);
    	add(descendingQS);
    	add(descendingMS);
    	add(descendingBS);
    	
    	add(timeL);
    	add(elementsL);
    	add(comparisonsL);
    	add(state);
	}
	private void resetValues() {
		Sorting.resetInts();
		comparisonSS = comparisonIS = comparisonQS = comparisonBS = comparisonMS = 0;
		timeSS = timeIS = timeQS = timeBS = timeMS = 0;
		
	}
	
	public void runSelection() {
		if(oldelemSS != Integer.parseInt(elementSS.getText())) {
			arraySS = randomInts(Integer.parseInt(elementSS.getText()));
			oldelemSS = Integer.parseInt(elementSS.getText());
		}

		long startTime = System.nanoTime();
		arraySS = Sorting.selectionSort(arraySS);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		timeSS = duration;
		timeSSL.setText(duration + " ns");
		comparisonSS = Sorting.getComparedSS();
		comparisonSSL.setText("" + comparisonSS);
	}
	
	public void runInsertion() {
		if(oldelemIS != Integer.parseInt(elementIS.getText())) {
			arrayIS = randomInts(Integer.parseInt(elementIS.getText()));
			oldelemIS = Integer.parseInt(elementIS.getText());
		}
		
		long startTime = System.nanoTime();
		arrayIS = Sorting.insertionSort(arrayIS);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		timeIS = duration;
		timeISL.setText(duration + " ns");
		comparisonIS = Sorting.getComparedIS();
		comparisonISL.setText("" + comparisonIS);
	}
	
	public void runQuick() {
		if(oldelemQS != Integer.parseInt(elementQS.getText())) {
			arrayQS = randomInts(Integer.parseInt(elementQS.getText()));
			oldelemQS = Integer.parseInt(elementQS.getText());
		}
		
		long startTime = System.nanoTime();
		arrayQS = Sorting.quickSort(arrayQS);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		timeQS = duration;
		timeQSL.setText(duration + " ns");
		comparisonQS = Sorting.getComparedQS();
		comparisonQSL.setText("" + comparisonQS);
	}
	
	public void runMerge() {
		if(oldelemMS != Integer.parseInt(elementMS.getText())) {
			arrayMS = randomInts(Integer.parseInt(elementMS.getText()));
			oldelemMS = Integer.parseInt(elementMS.getText());
		}
		
		long startTime = System.nanoTime();
		arrayMS = Sorting.mergeSort(arrayMS);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		timeMS = duration;
		timeMSL.setText(duration + " ns");
		comparisonMS = Sorting.getComparedMS();
		comparisonMSL.setText("" + comparisonMS);
	}
	
	public void runBubble() {
		if(oldelemBS != Integer.parseInt(elementBS.getText())) {
			arrayBS = randomInts(Integer.parseInt(elementBS.getText()));
			oldelemBS = Integer.parseInt(elementBS.getText());
		}
		
		long startTime = System.nanoTime();
		arrayBS = Sorting.bubbleSort(arrayBS);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		timeBS = duration;
		timeBSL.setText(duration + " ns");
		comparisonBS = Sorting.getComparedBS();
		comparisonBSL.setText("" + comparisonBS);
	}
	
	public void runSelectionA() {
		if(arraySS == null || oldelemSS != Integer.parseInt(elementSS.getText())) {
			arraySS = randomInts(Integer.parseInt(elementSS.getText()));
			oldelemSS = Integer.parseInt(elementSS.getText());
		}
		arraySS = Sorting.quickSort(arraySS);
	}
	
	public void runInsertionA() {
		if(arrayIS == null || oldelemIS != Integer.parseInt(elementIS.getText())) {
			arrayIS = randomInts(Integer.parseInt(elementIS.getText()));
			oldelemIS = Integer.parseInt(elementIS.getText());
		}
		
		arrayIS = Sorting.quickSort(arrayIS);
	}
	
	public void runQuickA() {
		if(arrayQS == null || oldelemQS != Integer.parseInt(elementQS.getText())) {
			arrayQS = randomInts(Integer.parseInt(elementQS.getText()));
			oldelemQS = Integer.parseInt(elementQS.getText());
		}
		
		arrayQS = Sorting.quickSort(arrayQS);
	}
	
	public void runMergeA() {
		if(arrayMS == null || oldelemMS != Integer.parseInt(elementMS.getText())) {
			arrayMS = randomInts(Integer.parseInt(elementMS.getText()));
			oldelemMS = Integer.parseInt(elementMS.getText());
		}
		
		arrayMS = Sorting.quickSort(arrayMS);
	}
	
	public void runBubbleA() {
		if(arrayBS == null || oldelemBS != Integer.parseInt(elementBS.getText())) {
			arrayBS = randomInts(Integer.parseInt(elementBS.getText()));
			oldelemBS = Integer.parseInt(elementBS.getText());
		}
		
		arrayBS = Sorting.quickSort(arrayBS);
	}
	
	public void runSelectionD() {
		if(arraySS == null || oldelemSS != Integer.parseInt(elementSS.getText())) {
			arraySS = randomInts(Integer.parseInt(elementSS.getText()));
			oldelemSS = Integer.parseInt(elementSS.getText());
		}
		arraySS = descending(Sorting.quickSort(arraySS));
	}
	
	public void runInsertionD() {
		if(arrayIS == null || oldelemIS != Integer.parseInt(elementIS.getText())) {
			arrayIS = randomInts(Integer.parseInt(elementIS.getText()));
			oldelemIS = Integer.parseInt(elementIS.getText());
		}
		
		arrayIS = descending(Sorting.quickSort(arrayIS));
	}
	
	public void runQuickD() {
		if(arrayQS == null || oldelemQS != Integer.parseInt(elementQS.getText())) {
			arrayQS = randomInts(Integer.parseInt(elementQS.getText()));
			oldelemQS = Integer.parseInt(elementQS.getText());
		}
		
		arrayQS = descending(Sorting.quickSort(arrayQS));
	}
	
	public void runMergeD() {
		if(arrayMS == null || oldelemMS != Integer.parseInt(elementMS.getText())) {
			arrayMS = randomInts(Integer.parseInt(elementMS.getText()));
			oldelemMS = Integer.parseInt(elementMS.getText());
		}
		
		arrayMS = descending(Sorting.quickSort(arrayMS));
	}
	
	public void runBubbleD() {
		if(arrayBS == null || oldelemBS != Integer.parseInt(elementBS.getText())) {
			arrayBS = randomInts(Integer.parseInt(elementBS.getText()));
			oldelemBS = Integer.parseInt(elementBS.getText());
		}
		
		arrayBS = descending(Sorting.quickSort(arrayBS));
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == enter) {
				resetValues();
				runSelection();
				runInsertion();
				runQuick();
				runMerge();
				runBubble();
				stateSSL.setText("Ascending");
				stateISL.setText("Ascending");
				stateQSL.setText("Ascending");
				stateMSL.setText("Ascending");
				stateBSL.setText("Ascending");
			}
			if(event.getSource() == enterSS) {
				resetValues();
				runSelection();
				stateSSL.setText("Ascending");
			}
			if(event.getSource() == enterIS) {
				resetValues();
				runInsertion();
				stateISL.setText("Ascending");
			}
			if(event.getSource() == enterQS) {
				resetValues();
				runQuick();
				stateQSL.setText("Ascending");
			}
			if(event.getSource() == enterMS) {
				resetValues();
				runMerge();
				stateMSL.setText("Ascending");
			}
			if(event.getSource() == enterBS) {
				resetValues();
				runBubble();
				stateBSL.setText("Ascending");
			}
			
			if(event.getSource() == Ascending) {
				resetValues();
				runSelectionA();
				runInsertionA();
				runQuickA();
				runMergeA();
				runBubbleA();
				stateSSL.setText("Ascending");
				stateISL.setText("Ascending");
				stateQSL.setText("Ascending");
				stateMSL.setText("Ascending");
				stateBSL.setText("Ascending");
			}
			if(event.getSource() == ascendingSS) {
				resetValues();
				runSelectionA();
				stateSSL.setText("Ascending");
			}
			if(event.getSource() == ascendingIS) {
				resetValues();
				runInsertionA();
				stateISL.setText("Ascending");
			}
			if(event.getSource() == ascendingQS) {
				resetValues();
				runQuickA();
				stateQSL.setText("Ascending");
			}
			if(event.getSource() == ascendingMS) {
				resetValues();
				runMergeA();
				stateMSL.setText("Ascending");
			}
			if(event.getSource() == ascendingBS) {
				resetValues();
				runBubbleA();
				stateBSL.setText("Ascending");
			}
			
			
			
			if(event.getSource() == descendingSS) {
				resetValues();
				runSelectionD();
				stateSSL.setText("Descending");
			}
			if(event.getSource() == descendingIS) {
				resetValues();
				runInsertionD();
				stateISL.setText("Descending");
			}
			if(event.getSource() == descendingQS) {
				resetValues();
				runQuickD();
				stateQSL.setText("Descending");
			}
			if(event.getSource() == descendingMS) {
				resetValues();
				runMergeD();
				stateMSL.setText("Descending");
			}
			if(event.getSource() == descendingBS) {
				resetValues();
				runBubbleD();
				stateBSL.setText("Descending");
			}
			if(event.getSource() == Descending) {
				resetValues();
				runSelectionD();
				runInsertionD();
				runQuickD();
				runMergeD();
				runBubbleD();
				
				stateSSL.setText("Descending");
				stateISL.setText("Descending");
				stateQSL.setText("Descending");
				stateMSL.setText("Descending");
				stateBSL.setText("Descending");
				
			}
			
			if(event.getSource() == randomize) {
				arraySS = randomInts(Integer.parseInt(elementSS.getText()));
				arrayIS = randomInts(Integer.parseInt(elementIS.getText()));
				arrayQS = randomInts(Integer.parseInt(elementIS.getText()));
				arrayMS = randomInts(Integer.parseInt(elementIS.getText()));
				arrayBS = randomInts(Integer.parseInt(elementIS.getText()));
				
				stateSSL.setText("Random");
				stateISL.setText("Random");
				stateQSL.setText("Random");
				stateMSL.setText("Random");
				stateBSL.setText("Random");
			}
			if(event.getSource() == randomizeSS) {
				arraySS = randomInts(Integer.parseInt(elementSS.getText()));
				stateSSL.setText("Random");
			}
			if(event.getSource() == randomizeIS) {
				arrayIS = randomInts(Integer.parseInt(elementIS.getText()));
				stateISL.setText("Random");
			}
			if(event.getSource() == randomizeQS) {
				arrayQS = randomInts(Integer.parseInt(elementQS.getText()));
				stateQSL.setText("Random");
			}
			if(event.getSource() == randomizeMS) {
				arrayMS = randomInts(Integer.parseInt(elementMS.getText()));
				stateMSL.setText("Random");
			}
			if(event.getSource() == randomizeBS) {
				arrayBS = randomInts(Integer.parseInt(elementBS.getText()));
				stateBSL.setText("Random");
			}
		}
    }
	
	private static Integer[] descending(Integer[] ascending) {
		Integer[] temp = new Integer[ascending.length];
		for(int x = 0; x<ascending.length; x++) {
			temp[x] = ascending[ascending.length-x-1];
		}
		
		
		return temp;
		
	}
	
	private static Random rand = new Random();
	
	private static Integer[] randomInts(int ammount) {
		Integer[] temp = new Integer[ammount];
		
		for(int x = 0; x < temp.length; x++) {
			temp[x] = (rand.nextInt(100) + 1);
		}
		
		return temp;
	}
	
}
