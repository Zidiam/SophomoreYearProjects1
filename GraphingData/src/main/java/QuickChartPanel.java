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

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

public class QuickChartPanel extends JPanel{
	private HashSet<DataObject> dataSet;
	private JComboBox<String> chooseX, chooseY;
	private JComboBox<DataObject> chooseObjectX, chooseObjectY;
	private JButton graphB;
	private XChartPanel<XYChart> xPanel;
	private JLabel errorL;
	
	public QuickChartPanel(HashSet<DataObject> dataSet) {
		this.dataSet = dataSet;
		this.setLayout(new BorderLayout());
		
		setupButtons();
	}
	
	private void setupButtons() {
		JPanel buttonP = new JPanel();
		buttonP.setLayout(new GridLayout());
		
		chooseX = new JComboBox<String>();
		chooseY = new JComboBox<String>();
		chooseObjectX = new JComboBox<DataObject>();
		chooseObjectY = new JComboBox<DataObject>();
		graphB = new JButton("Graph");
		errorL = new JLabel("Please enter valid inputs");
		errorL.setForeground(Color.red);
		errorL.setVisible(false);
		
		chooseX.addItem("Choose X");
		chooseY.addItem("Choose Y");
		
		chooseObjectX.addItem(new DataObject("Choose X Data:"));
		chooseObjectX.addItem(new DataObject("All Data"));
		
		chooseObjectY.addItem(new DataObject("Choose Y Data:"));
		chooseObjectY.addItem(new DataObject("All Data"));
		
		ArrayList<String> dataContents = DataObject.getDataContents();
		
		for(int scan = 0; scan < dataContents.size(); scan++) {
			chooseX.addItem(dataContents.get(scan));
			chooseY.addItem(dataContents.get(scan));
		}
		
		for(DataObject data : dataSet) {
			chooseObjectX.addItem(data);
			chooseObjectY.addItem(data);
		}
		
		graphB.addActionListener(new ButtonListener());
		chooseX.addActionListener(new ButtonListener());
		chooseY.addActionListener(new ButtonListener());
		chooseObjectX.addActionListener(new ButtonListener());
		chooseObjectY.addActionListener(new ButtonListener());
		
		buttonP.add(chooseObjectX);
		buttonP.add(chooseObjectY);
		buttonP.add(chooseX);
		buttonP.add(chooseY);
		buttonP.add(graphB);
		buttonP.add(errorL);
		add(buttonP, BorderLayout.NORTH);
	}
	
	private void graphData() {
		if(xPanel != null) {
			remove(xPanel);
		}
		
		HashSet<DataObject> dataSetx = new HashSet<DataObject>(dataSet);
		HashSet<DataObject> dataSety = new HashSet<DataObject>(dataSet);
		
		if(chooseObjectX.getSelectedIndex() != 1) {
			dataSetx.clear();
			dataSetx.add((DataObject) chooseObjectX.getSelectedItem());
		}
		if(chooseObjectY.getSelectedIndex() != 1) {
			dataSety.clear();
			dataSety.add((DataObject) chooseObjectY.getSelectedItem());
		}
		
		double[] xData = new double[dataSetx.size() + dataSety.size()];
		double[] yData = new double[dataSetx.size() + dataSety.size()];
		
		int count = 0;
		for(DataObject data : dataSetx) {
			xData[count] = Double.parseDouble(data.getDataList().get(chooseX.getSelectedIndex()-1));
			yData[count] = Double.parseDouble(data.getDataList().get(chooseY.getSelectedIndex()-1));
			count ++;
		}
		
		for(DataObject data : dataSety) {
			xData[count] = Double.parseDouble(data.getDataList().get(chooseX.getSelectedIndex()-1));
			yData[count] = Double.parseDouble(data.getDataList().get(chooseY.getSelectedIndex()-1));
			count ++;
		}
		
		String x_axis = DataObject.getDataContents().get(chooseX.getSelectedIndex()-1);
		String y_axis = DataObject.getDataContents().get(chooseY.getSelectedIndex()-1);
		String title = x_axis + " To " + y_axis;
		XYChart chart = QuickChart.getChart(title, x_axis, y_axis, "y(x)", xData, yData);
		xPanel = new XChartPanel<XYChart>(chart);
		add(xPanel, BorderLayout.CENTER);
		this.updateUI();
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == graphB) {
				if(chooseX.getSelectedIndex() == 0 || chooseY.getSelectedIndex() == 0 || chooseObjectX.getSelectedIndex() == 0 || chooseObjectY.getSelectedIndex() == 0) {
					errorL.setVisible(true);
				}
				else {
					try {
						errorL.setVisible(false);
						graphData();
					}catch(Exception e){
						errorL.setVisible(true);
					}
				}
			}
		}
	}
	
}
