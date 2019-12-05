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

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class PieChartPanel extends JPanel{
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
	protected PieChart chart;
	protected int titleInt = 0;
	protected Styler styler;
	
	public PieChartPanel(HashSet<DataObject> dataSet) {
		this.dataSet = dataSet;
		this.setLayout(new BorderLayout());
		
		this.setBackground(Color.LIGHT_GRAY);
		
		setupButtons();
		createChart();
	}
	
	protected void createChart() {
		chart = new PieChartBuilder().build();
	    chart.getStyler().setLegendVisible(true);
	    chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
	    chart.getStyler().setHasAnnotations(true);
	}
	
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
		buttonP.add(chooseObjectX);
		buttonP.add(chooseObjectY);
		chooseObjectY.setVisible(false);
		buttonP.add(addB);
		buttonP.add(combinedObjectsBox);
		buttonP.add(removeB);
		buttonP.add(errorL);
		buttonP.add(chooseX);
		buttonP.add(chooseY);
		chooseY.setVisible(false);
		buttonP.add(graphB);
		buttonP.add(rangeforX1);
		buttonP.add(rangeforX2);
		buttonP.add(applyXB);
		
		add(buttonP, BorderLayout.NORTH);
		buttonP.setVisible(false);
		add(editB, BorderLayout.NORTH);
	}
	
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
	
	protected void addData() {
		combinedObjectsBox.addItem(new DataObject((DataObject) chooseObjectX.getSelectedItem()));
	}
	
	protected void hideButtons() {
		editB.setVisible(true);
		buttonP.setVisible(false);
		add(editB, BorderLayout.NORTH);
	
		errorL.setVisible(false);
	}
	
	protected void updateScreen() {
		this.updateUI();
	}
	
	protected void removeData() {
		combinedObjectsBox.removeItemAt((combinedObjectsBox.getSelectedIndex()));
	}
	
	protected boolean containsIgnoreCase(String str, String searchStr){
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
	
	protected void editChooseX() {
		chooseObjectX.removeAllItems();
		for(DataObject data : dataSet) {
			if(containsIgnoreCase(data.getDataList().get(chooseSearchX.getSelectedIndex()-1), searchDataX.getText())){
				chooseObjectX.addItem(data);
			}
		}
	}
	
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
			
			if(event.getSource() == addB && !chooseObjectX.getSelectedItem().toString().contains("Choose X Data:")) {
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
			

			
			if(event.getSource() == graphB) {
				
				if(combinedObjectsBox.getItemCount() < 1) {
					errorL.setVisible(true);
				}
				else {
					//try {
						createChart();
						graphData();
						hideButtons();
					//}catch(Exception e){
						errorL.setText("Invalid inputs to create graph");
						errorL.setVisible(true);
					//}
				}
			}
		}
	}
	
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
	
	protected void graphData() {
		if(this.xPanel != null) {
			remove(xPanel);
		}
		
		titleInt = 0;
		
		HashSet<DataObject> compareDataSet = new HashSet<DataObject>();
		
		for(int scan = 1; scan < combinedObjectsBox.getItemCount(); scan++) {
			compareDataSet.add((DataObject) combinedObjectsBox.getItemAt(scan));
		}
	
		for(DataObject data : compareDataSet) {
			if(data.toString().equals("All Data")) {
				for(DataObject dataScan : dataSet) {
					double dataDouble = Double.parseDouble(dataScan.getDataList().get(chooseX.getSelectedIndex()-1));
					chart.addSeries("Data: " + titleInt, dataDouble);
					titleInt++;
				}
			}else {
				double dataDouble = Double.parseDouble(data.getDataList().get(chooseX.getSelectedIndex()-1));
				chart.addSeries("Data: " + titleInt, dataDouble);
				titleInt++;
			}
		}
	    
		xPanel = new XChartPanel(chart);
		add(xPanel, BorderLayout.CENTER);
		this.updateUI();
	}
}
