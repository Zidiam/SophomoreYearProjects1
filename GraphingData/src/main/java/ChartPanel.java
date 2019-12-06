import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.Styler;

/*
 * ChartPanel -- A panel that creates the buttons for the user to use and creates the graph for the user to see
 * By: Jason Melnik
 * Date: 12/1/2019
 */
public class ChartPanel extends JPanel{
	protected HashSet<DataObject> dataSet;
	protected JComboBox<String> chooseX, chooseY, chooseSearchX, chooseSearchY;
	protected JComboBox<DataObject> chooseObjectX, chooseObjectY, combinedObjectsBox;
	protected JTextField searchDataX, searchDataY, rangeforX1, rangeforX2, rangeforY1, rangeforY2;
	protected JButton graphB, addB, removeB, searchXB, searchYB, applyXB, applyYB, editB;
	protected XChartPanel xPanel;
	protected JLabel errorL;
	protected JPanel buttonP;
	protected double[] xData = new double[2];
	protected double[] yData = new double[2];
	protected XYChart chart;
	protected int titleInt = 0;
	protected Styler styler;
	
	/**
	 * This takes in a dataSet to that we can use that data to graph a chart to make it more visual
	 * @param dataSet is a set of DataObjects so that we can graph the data
	 */
	public ChartPanel(HashSet<DataObject> dataSet) {
		this.dataSet = dataSet;
		this.setLayout(new BorderLayout());
		
		this.setBackground(Color.LIGHT_GRAY);
		
		setupButtons();
		createChart();
	}
	
	/**
	 * Used to build a specific chart
	 */
	protected void createChart() {
		//type of chart you want
	}
	
	/**
	 * This creates all the buttons and the button panel that contains all the buttons
	 * This method also colors the buttons and sets them in the correct location
	 * This method then adds the button panel to the classes Panel
	 */
	protected void setupButtons() {
		buttonP = new JPanel();
		buttonP.setLayout(new GridLayout(7, 3));
		buttonP.setBackground(Color.LIGHT_GRAY);
		
		chooseX = new JComboBox<String>();
		chooseY = new JComboBox<String>();
		chooseSearchX = new JComboBox<String>(); 
		chooseSearchY = new JComboBox<String>(); 
		
		chooseObjectX = new JComboBox<DataObject>();
		chooseObjectY = new JComboBox<DataObject>();
		combinedObjectsBox = new JComboBox<DataObject>(); 
		
		searchDataX = new JTextField("Search Data for X");
		searchDataY = new JTextField("Search Data for Y");
		rangeforX1 = new JTextField("Range1X");
		rangeforX2 = new JTextField("Range2X");
		rangeforY1 = new JTextField("Range1Y");
		rangeforY2 = new JTextField("Range2Y");
		
		editB = new JButton("Edit");
		graphB = new JButton("Graph");
		addB = new JButton("Add Data");
		removeB = new JButton("Remove Data");
		searchXB = new JButton("SearchX");
		searchYB = new JButton("SearchY");
		applyXB = new JButton("Apply X");
		applyYB = new JButton("Apply Y");
		
		errorL = new JLabel("Please enter valid inputs");
		errorL.setForeground(Color.red);
		errorL.setVisible(false);
		
		chooseX.addItem("Choose X");
		chooseY.addItem("Choose Y");
		chooseSearchX.addItem("Choose Data to search in X");
		chooseSearchY.addItem("Choose Data to search in Y");
		
		chooseObjectX.addItem(new DataObject("Choose X Data:"));
		chooseObjectX.addItem(new DataObject("All Data"));
		
		chooseObjectY.addItem(new DataObject("Choose Y Data:"));
		chooseObjectY.addItem(new DataObject("All Data"));
		
		combinedObjectsBox.addItem(new DataObject("Data to Compare:"));
		
		setupBoxContents();
		
		editB.addActionListener(new ButtonListener());
		graphB.addActionListener(new ButtonListener());
		addB.addActionListener(new ButtonListener());
		removeB.addActionListener(new ButtonListener());
		searchXB.addActionListener(new ButtonListener());
		searchYB.addActionListener(new ButtonListener());
		applyXB.addActionListener(new ButtonListener());
		applyYB.addActionListener(new ButtonListener());
		
		searchXB.setBackground(Color.green);
		searchYB.setBackground(Color.green);
		addB.setBackground(Color.green);
		removeB.setBackground(Color.green);
		graphB.setBackground(Color.green);
		applyXB.setBackground(Color.green);
		applyYB.setBackground(Color.green);
		editB.setBackground(Color.green);
		
		chooseSearchX.setBackground(Color.yellow);
		chooseSearchY.setBackground(Color.yellow);
		chooseX.setBackground(Color.yellow);
		chooseY.setBackground(Color.yellow);
		combinedObjectsBox.setBackground(Color.yellow);
		chooseObjectX.setBackground(Color.yellow);
		chooseObjectY.setBackground(Color.yellow);
		
		searchDataX.setBackground(Color.cyan);
		searchDataY.setBackground(Color.cyan);
		rangeforX1.setBackground(Color.cyan);
		rangeforX2.setBackground(Color.cyan);
		rangeforY1.setBackground(Color.cyan);
		rangeforY2.setBackground(Color.cyan);
		
		errorL.setBackground(Color.black);
		
		buttonP.add(chooseSearchX);
		buttonP.add(searchDataX);
		buttonP.add(searchXB);
		buttonP.add(chooseSearchY);
		buttonP.add(searchDataY);
		buttonP.add(searchYB);
		buttonP.add(chooseObjectX);
		buttonP.add(chooseObjectY);
		buttonP.add(addB);
		buttonP.add(combinedObjectsBox);
		buttonP.add(removeB);
		buttonP.add(errorL);
		buttonP.add(chooseX);
		buttonP.add(chooseY);
		buttonP.add(graphB);
		buttonP.add(rangeforX1);
		buttonP.add(rangeforX2);
		buttonP.add(applyXB);
		buttonP.add(rangeforY1);
		buttonP.add(rangeforY2);
		buttonP.add(applyYB);
		
		add(buttonP, BorderLayout.NORTH);
		buttonP.setVisible(false);
		add(editB, BorderLayout.NORTH);
	}
	
	/**
	 * This sets up whats inside the JComboBox which would be the choices that the user can choose
	 * This will add items in the JComboBox such as the data or the type of data
	 */
	protected void setupBoxContents() {
		ArrayList<String> dataContents = DataObject.getDataContents();
		
		for(int scan = 0; scan < dataContents.size(); scan++) {
			chooseX.addItem(dataContents.get(scan));
			chooseY.addItem(dataContents.get(scan));
			
			chooseSearchX.addItem(dataContents.get(scan));
			chooseSearchY.addItem(dataContents.get(scan));
		}
		
		for(DataObject data : dataSet) {
			chooseObjectX.addItem(data);
			chooseObjectY.addItem(data);
		}
	}
	
	/**
	 * This method adds data to your JComboBox called combinedObjectsBox which is a combination of two objects
	 */
	protected void addData() {
		DataObject newObject = new DataObject((DataObject) chooseObjectX.getSelectedItem());
		newObject.addObject(new DataObject((DataObject) chooseObjectY.getSelectedItem()));
		combinedObjectsBox.addItem(newObject);
	}
	
	/**
	 * This method is used to hide the big panel filled with buttons and just show one button that says edit
	 */
	protected void hideButtons() {
		editB.setVisible(true);
		buttonP.setVisible(false);
		add(editB, BorderLayout.NORTH);
	
		errorL.setVisible(false);
	}
	
	/**
	 * This method just updates the jpanel
	 */
	protected void updateScreen() {
		this.updateUI();
	}
	
	/**
	 * This method removes the data from the combinedObjectsBox which is the JComboBox 
	 * that contains the objects that will be used to be compared
	 */
	protected void removeData() {
		combinedObjectsBox.removeItemAt((combinedObjectsBox.getSelectedIndex()));
	}
	
	/**
	 * This method checks if a string contains certain characters ignoring case
	 * @param str = this is the String we will check if its in the main string
	 * @param searchStr = this is the main String in which will be used to see if a str is contained in it
	 * @return this will return true if str is inside seachStr and false otherwise
	 */
	protected boolean containsIgnoreCase(String str, String searchStr)     {
	    if(str == null || searchStr == null) return false;

	    final int length = searchStr.length();
	    if (length == 0)
	        return true;

	    for (int i = str.length() - length; i >= 0; i--) {
	        if (str.regionMatches(true, i, searchStr, 0, length))
	            return true;
	    }
	    return false;
	}
	
	/**
	 * This is to edit chooseObjectX and only have it contain what the people want to search for in search X
	 */
	protected void editChooseX() {
		chooseObjectX.removeAllItems();
		for(DataObject data : dataSet) {
			if(containsIgnoreCase(data.getDataList().get(chooseSearchX.getSelectedIndex()-1), searchDataX.getText())){
				chooseObjectX.addItem(data);
			}
		}
	}
	
	/**
	 * This is to edit chooseObjectY and only have it contain what the people want to search for in search Y
	 */
	protected void editChooseY() {
		chooseObjectY.removeAllItems();
		for(DataObject data : dataSet) {
			if(containsIgnoreCase(data.getDataList().get(chooseSearchY.getSelectedIndex()-1), searchDataY.getText())){
				chooseObjectY.addItem(data);
			}
		}
	}
	
	/**
	 * This class will be used to hear action events that are caused in the buttons and do things to the graph
	 */
	protected class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == editB) {
				editB.setVisible(false);
				buttonP.setVisible(true);
				add(buttonP, BorderLayout.NORTH);
				updateScreen();
			}
			
			if(event.getSource() == applyXB) {
				try {
					errorL.setVisible(false);
					createChart();
					graphData();
					hideButtons();
				}catch(Exception e) {
					errorL.setVisible(true);
					errorL.setText("Invalid range for X1 to X2 (Try clearing the ranges)");
				}
			}
			
			if(event.getSource() == applyYB) {
				try {
					errorL.setVisible(false);
					createChart();
					graphData();
					hideButtons();
				}catch(Exception e) {
					errorL.setVisible(true);
					errorL.setText("Invalid range for Y1 to Y2 (Try clearing the ranges)");
				}
			}
			
			if(event.getSource() == addB && !chooseObjectX.getSelectedItem().toString().contains("Choose X Data:") && !chooseObjectY.getSelectedItem().toString().contains("Choose Y Data:")) {
				addData();
			}
			
			if(event.getSource() == removeB && combinedObjectsBox.getSelectedIndex() != 0) {
				removeData();
			}
			
			if(event.getSource() == searchXB && chooseSearchX.getSelectedIndex() != 0) {
				try {
					errorL.setVisible(false);
					editChooseX();
				}catch(Exception e) {
					errorL.setVisible(true);
					errorL.setText("Invalid search for X");
				}
			}
			
			if(event.getSource() == searchYB && chooseSearchY.getSelectedIndex() != 0) {
				try {
					errorL.setVisible(false);
					editChooseY();
				}catch(Exception e) {
					errorL.setVisible(true);
					errorL.setText("Invalid search for Y");
				}
			}
			
			if(event.getSource() == graphB) {
				
				if(chooseX.getSelectedIndex() == 0 || chooseY.getSelectedIndex() == 0 || combinedObjectsBox.getItemCount() < 1) {
					errorL.setVisible(true);
				}
				else {
					try {
						createChart();
						graphData();
						hideButtons();
					}catch(Exception e){
						errorL.setText("Invalid inputs to create graph");
						errorL.setVisible(true);
					}
				}
			}
		}
	}
	
	/**
	 * This method filters out the graph so that only things in the Y range are shown on the graph
	 * @param data this takes in data to compare if weather or not the data object is within the bounds of the range of Y
	 * @return this returns true if data is in the range and false if not
	 */
	protected boolean rangeCheckDataY(DataObject data) {
		if(rangeforY1.getText().equals("") || rangeforY2.getText().equals("")) {
			return true;
		}
		if(rangeforY1.getText().equals("Range1Y") || rangeforY2.getText().equals("Range2Y")) {
			return true;
		}
		double checkD = Double.parseDouble(data.getDataList().get(chooseY.getSelectedIndex()-1));
		double checkY1 = Double.parseDouble(rangeforY1.getText());
		double checkY2 = Double.parseDouble(rangeforY2.getText());
		if(checkY1 < checkD && checkD < checkY2) {
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * This method filters out the graph so that only things in the X range are shown on the graph
	 * @param data this takes in data to compare if weather or not the data object is within the bounds of the range of X
	 * @return this returns true if data is in the range and false if not
	 */
	protected boolean rangeCheckDataX(DataObject data) {
		if(rangeforX1.getText().equals("") || rangeforX2.getText().equals("")) {
			return true;
		}
		if(rangeforX1.getText().equals("Range1X") || rangeforX2.getText().equals("Range2X")) {
			return true;
		}
		double checkD = Double.parseDouble(data.getDataList().get(chooseX.getSelectedIndex()-1));
		double checkX1 = Double.parseDouble(rangeforX1.getText());
		double checkX2 = Double.parseDouble(rangeforX2.getText());
		if(checkX1 < checkD && checkD < checkX2) {
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * This method adds data to the chart
	 * @param data is a DataObject that we use to compare all the data in the dataSet to that data
	 */
	protected void compareAllData(DataObject data) {
		int count2 = 0;
		for(DataObject scanData : dataSet) {
			if(rangeCheckDataY(scanData) && rangeCheckDataX(scanData)) {
				xData[count2%2] = Double.parseDouble(scanData.getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2] = Double.parseDouble(scanData.getDataList().get(chooseY.getSelectedIndex()-1));
				if (count2 != 0 && (count2 % 2) == 0){
					chart.addSeries("Data: " + titleInt, xData, yData);
					xData = new double[2];
					yData = new double[2];
					titleInt++;
				}
				
				count2 ++;
			}
		}
	}
	
	/**
	 * This method adds data to the chart
	 * @param data is a DataObject that we use to compare all the data in the dataSet to that data
	 */
	protected void compareAllDataToData(DataObject data) {
		int count2 = 0;
		for(DataObject scanData : dataSet) {
			if(rangeCheckDataY(scanData) && rangeCheckDataX(scanData)) {
				xData[count2%2] = Double.parseDouble(scanData.getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2] = Double.parseDouble(scanData.getDataList().get(chooseY.getSelectedIndex()-1));
				
				xData[count2%2+1] = Double.parseDouble(data.getObject2().getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2+1] = Double.parseDouble(data.getObject2().getDataList().get(chooseY.getSelectedIndex()-1));
				
				chart.addSeries("Data: " + titleInt, xData, yData);
				xData = new double[2];
				yData = new double[2];
				titleInt++;
				
				count2 += 2;
			}
		}
	}
	
	/**
	 * This method adds data to the chart
	 * @param data is a DataObject that we use to compare all the data in the dataSet to that data
	 */
	protected void compareDataToAllData(DataObject data) {
		int count2 = 0;
		for(DataObject scanData : dataSet) {
			if(rangeCheckDataY(scanData) && rangeCheckDataX(scanData)) {
				xData[count2%2] = Double.parseDouble(data.getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2] = Double.parseDouble(data.getDataList().get(chooseY.getSelectedIndex()-1));
				
				xData[count2%2+1] = Double.parseDouble(scanData.getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2+1] = Double.parseDouble(scanData.getDataList().get(chooseY.getSelectedIndex()-1));
				

				chart.addSeries("Data: " + titleInt, xData, yData);
				xData = new double[2];
				yData = new double[2];
				titleInt++;
				
				count2 += 2;
			}
		}
	}
	
	/**
	 * This method builds the graph full of data and then inputs in into the panel
	 */
	protected void graphData() {
		if(this.xPanel != null) {
			remove(xPanel);
		}
		
		xData = new double[2];
		yData = new double[2];
		titleInt = 0;
		
		HashSet<DataObject> compareDataSet = new HashSet<DataObject>();
		
		for(int scan = 1; scan < combinedObjectsBox.getItemCount(); scan++) {
			compareDataSet.add((DataObject) combinedObjectsBox.getItemAt(scan));
		}
	
		int count = 0;
		for(DataObject data : compareDataSet) {
			if(data.toString().substring(0, 8).contains("All Data") && data.getObject2().toString().substring(0, 8).contains("All Data")) {
				compareAllData(data);
			}
			else if(data.toString().substring(0, 8).contains("All Data")) {
				compareAllDataToData(data);
			}
			else if(data.getObject2().toString().substring(0, 8).contains("All Data")) {
				compareDataToAllData(data);
			}
			else {
				if(rangeCheckDataY(data) && rangeCheckDataX(data) && (rangeCheckDataY(data.getObject2()) && rangeCheckDataX(data.getObject2()))) {
					xData[count%2] = Double.parseDouble(data.getDataList().get(chooseX.getSelectedIndex()-1));
					yData[count%2] = Double.parseDouble(data.getDataList().get(chooseY.getSelectedIndex()-1));
					
					xData[count%2+1] = Double.parseDouble(data.getObject2().getDataList().get(chooseX.getSelectedIndex()-1));
					yData[count%2+1] = Double.parseDouble(data.getObject2().getDataList().get(chooseY.getSelectedIndex()-1));
					
					chart.addSeries("Data: " + titleInt, xData, yData);
					xData = new double[2];
					yData = new double[2];
					titleInt++;
					
					count += 2;
				}
			}
		}
	    
		xPanel = new XChartPanel(chart);
		add(xPanel, BorderLayout.CENTER);
		this.updateUI();
	}
	
}
