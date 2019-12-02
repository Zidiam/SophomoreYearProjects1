import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;

public class GraphingPanel extends JPanel{
	private JButton setupB, addFileB;
	private HashSet<DataObject> dataSet;
	private JComboBox<File> fileChooser;
	private JComboBox<String> graphChooser;
	private QuickChartPanel quickP;
	private JLabel errorL;
	
	public GraphingPanel() {
		this.setBackground(Color.green);
		this.setLayout(new BorderLayout());
		setupComponenet();
	}
	
	private void setupComponenet() {
		this.setPreferredSize(new Dimension(1280, 720));
		this.setLayout(new BorderLayout());
		
		JPanel buttonP = new JPanel();
		buttonP.setLayout(new GridLayout());
		
		fileChooser = new JComboBox<File>();
		graphChooser = new JComboBox<String>();
		setupB = new JButton("SetUp");
		addFileB = new JButton("Add File");
		errorL = new JLabel("Please select a file and graph");
		
		graphChooser.addActionListener(new ButtonListener());
		fileChooser.addActionListener(new ButtonListener());
		setupB.addActionListener(new ButtonListener());
		addFileB.addActionListener(new ButtonListener());
		
		fileChooser.addItem(new File("Choose File:"));
		
		graphChooser.addItem("Choose Graph:");
		graphChooser.addItem("QuickChart");
		
		errorL.setForeground(Color.red);
		errorL.setVisible(false);
		
		buttonP.add(addFileB);
		buttonP.add(fileChooser);
		buttonP.add(graphChooser);
		buttonP.add(setupB);
		buttonP.add(errorL);
		
		add(buttonP, BorderLayout.NORTH);
	}
	
	private void chooseFiles() {
		JFileChooser fileChoose = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("EXCEL FILES(CSV)", "csv");
		fileChoose.setFileFilter(filter);
		int returnVal = fileChoose.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			fileChooser.addItem(fileChoose.getSelectedFile());
		}
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == addFileB) {
				chooseFiles();
			}
			if(event.getSource() == setupB) {
				if(fileChooser.getSelectedIndex() == 0 || graphChooser.getSelectedIndex() == 0) {
					errorL.setVisible(true);
				}
				else if(fileChooser.getSelectedIndex() != 0 && fileChooser.getSelectedItem() != null) {
					removeGraphs();
					errorL.setVisible(false);
					showFile((File) fileChooser.getSelectedItem());
					if(fileChooser.getSelectedIndex() != 0 && fileChooser.getSelectedItem() != null) {
						graphData((String) graphChooser.getSelectedItem());
					}
				}
			}
		}
	}
	
	private void graphData(String graphType) {
		if(graphType.equals("QuickChart")) {
			loadQuickChart();
		}
	}
	
	private void loadQuickChart() {
		quickP = new QuickChartPanel(dataSet);
		add(quickP, BorderLayout.CENTER);
		this.updateUI();
	}
	
	private void removeGraphs() {
		DataObject.setDataContents(new ArrayList<String>());
		if(quickP != null) {
			remove(quickP);
		}
	}
	
	private void showFile(File data) {
		try (
	            Reader reader = Files.newBufferedReader(Paths.get(data.getAbsolutePath()));
	            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
	        ) {
				dataSet = new HashSet<DataObject>();
				boolean first = true;
	            for (CSVRecord csvRecord : csvParser) {
	                // Accessing Values by Column Index
	            	String result = "";
	            	if(first == false) {
	            		for(int scan = 0; scan < csvRecord.size(); scan++) {
		            		result += csvRecord.get(scan) + ",,";
		            	}
		            	result = result.substring(0, result.length()-2);
		            	
		            	DataObject tempData = new DataObject(result);
		            	dataSet.add(tempData);
	            	}
	            	if(first == true) {
	            		first = false;
	            		for(int scan = 0; scan < csvRecord.size(); scan++) {
		            		result += csvRecord.get(scan) + ",,";
		            	}
		            	result = result.substring(0, result.length()-2);
		            	
		            	DataObject.setupStartData(result);
	            	}
	            }
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
