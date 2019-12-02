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

public class ChartPanel extends JPanel{
	protected HashSet<DataObject> dataSet;
	protected JComboBox<String> chooseX, chooseY;
	protected JComboBox<DataObject> chooseObjectX, chooseObjectY;
	protected JButton graphB;
	protected XChartPanel<XYChart> xPanel;
	protected JLabel errorL;
	
	public ChartPanel(HashSet<DataObject> dataSet) {
		this.dataSet = dataSet;
		this.setLayout(new BorderLayout());
		
		setupButtons();
	}
	
	protected void setupButtons() {
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
	
	protected void graphData() {
		this.updateUI();
	}
	
	protected class ButtonListener implements ActionListener{
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
