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

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

public class ChartPanel extends JPanel{
	protected HashSet<DataObject> dataSet;
	protected JComboBox<String> chooseX, chooseY, chooseSearchX, chooseSearchY;
	protected JComboBox<DataObject> chooseObjectX, chooseObjectY, combinedObjectsBox;
	protected JTextField searchDataX, searchDataY, rangeforX1, rangeforX2, rangeforY1, rangeforY2;
	protected JButton graphB, addB, removeB, searchXB, searchYB, applyXB, applyYB, editB;
	protected XChartPanel<XYChart> xPanel;
	protected JLabel errorL;
	protected JPanel buttonP;
	
	public ChartPanel(HashSet<DataObject> dataSet) {
		this.dataSet = dataSet;
		this.setLayout(new BorderLayout());
		
		setupButtons();
	}
	
	protected void setupButtons() {
		buttonP = new JPanel();
		buttonP.setLayout(new GridLayout(7, 3));
		
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
		chooseX.addActionListener(new ButtonListener());
		chooseY.addActionListener(new ButtonListener());
		chooseObjectX.addActionListener(new ButtonListener());
		chooseObjectY.addActionListener(new ButtonListener());
		combinedObjectsBox.addActionListener(new ButtonListener());
		chooseSearchX.addActionListener(new ButtonListener());
		chooseSearchY.addActionListener(new ButtonListener());
		applyXB.addActionListener(new ButtonListener());
		applyYB.addActionListener(new ButtonListener());
		
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
		DataObject newObject = new DataObject((DataObject) chooseObjectX.getSelectedItem());
		newObject.addObject(new DataObject((DataObject) chooseObjectY.getSelectedItem()));
		combinedObjectsBox.addItem(newObject);
	}
	
	protected void graphData() {
		this.updateUI();
	}
	
	protected void updateScreen() {
		this.updateUI();
	}
	
	protected class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == editB) {
				editB.setVisible(false);
				buttonP.setVisible(true);
				add(buttonP, BorderLayout.NORTH);
				updateScreen();
			}
			
			if(event.getSource() == addB) {
				addData();
			}
			
			if(event.getSource() == graphB) {
				
				if(chooseX.getSelectedIndex() == 0 || chooseY.getSelectedIndex() == 0 || chooseObjectX.getSelectedIndex() == 0 || chooseObjectY.getSelectedIndex() == 0) {
					errorL.setVisible(true);
				}
				else {
					//try {
						editB.setVisible(true);
						buttonP.setVisible(false);
						add(editB, BorderLayout.NORTH);
					
						errorL.setVisible(false);
						graphData();
					//}catch(Exception e){
					//	errorL.setVisible(true);
					//}
				}
			}
		}
	}
	
}
