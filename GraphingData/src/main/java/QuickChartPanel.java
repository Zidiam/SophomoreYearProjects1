import java.awt.BorderLayout;
import java.util.HashSet;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.Styler.LegendPosition;

public class QuickChartPanel extends ChartPanel{
	private double[] xData = new double[2];
	private double[] yData = new double[2];
	private XYChart chart = null;
	private int titleInt = 0;
	public QuickChartPanel(HashSet<DataObject> dataSet) {
		super(dataSet);
	}
	
	private boolean rangeCheckDataY(DataObject data) {
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
	
	private boolean rangeCheckDataX(DataObject data) {
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
	
	private void compareAllData(DataObject data) {
		int count2 = 0;
		for(DataObject scanData : dataSet) {
			if(rangeCheckDataY(scanData) || rangeCheckDataX(scanData)) {
				xData[count2%2] = Double.parseDouble(scanData.getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2] = Double.parseDouble(scanData.getDataList().get(chooseY.getSelectedIndex()-1));
				
				if(chart == null && count2 == 1) {
					String x_axis = DataObject.getDataContents().get(chooseX.getSelectedIndex()-1);
					String y_axis = DataObject.getDataContents().get(chooseY.getSelectedIndex()-1);
					String title = x_axis + " To " + y_axis;
					chart = QuickChart.getChart(title, x_axis, y_axis, "Data: " + titleInt, xData, yData);
					chart.getStyler().setLegendVisible(true);
				    chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
				    titleInt++;
				}
				else if (count2 != 0 && count2 % 2 == 0){
					chart.addSeries("Data: " + titleInt, xData, yData);
					xData = new double[2];
					yData = new double[2];
					titleInt++;
				}
				
				count2 ++;
			}
		}
	}
	
	private void compareAllDataToData(DataObject data) {
		int count2 = 0;
		for(DataObject scanData : dataSet) {
			if(rangeCheckDataY(scanData) || rangeCheckDataX(scanData)) {
				xData[count2%2] = Double.parseDouble(scanData.getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2] = Double.parseDouble(scanData.getDataList().get(chooseY.getSelectedIndex()-1));
				
				xData[count2%2+1] = Double.parseDouble(data.getObject2().getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2+1] = Double.parseDouble(data.getObject2().getDataList().get(chooseY.getSelectedIndex()-1));
				
				if(chart == null) {
					String x_axis = DataObject.getDataContents().get(chooseX.getSelectedIndex()-1);
					String y_axis = DataObject.getDataContents().get(chooseY.getSelectedIndex()-1);
					String title = x_axis + " To " + y_axis;
					chart = QuickChart.getChart(title, x_axis, y_axis, "Data: " + titleInt, xData, yData);
					chart.getStyler().setLegendVisible(true);
				    chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
				    titleInt++;
				}
				else{
					chart.addSeries("Data: " + titleInt, xData, yData);
					xData = new double[2];
					yData = new double[2];
					titleInt++;
				}
				
				count2 += 2;
			}
		}
	}
	
	private void compareDataToAllData(DataObject data) {
		int count2 = 0;
		for(DataObject scanData : dataSet) {
			if(rangeCheckDataY(scanData) || rangeCheckDataX(scanData)) {
				xData[count2%2] = Double.parseDouble(data.getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2] = Double.parseDouble(data.getDataList().get(chooseY.getSelectedIndex()-1));
				
				xData[count2%2+1] = Double.parseDouble(scanData.getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2+1] = Double.parseDouble(scanData.getDataList().get(chooseY.getSelectedIndex()-1));
				
				if(chart == null) {
					String x_axis = DataObject.getDataContents().get(chooseX.getSelectedIndex()-1);
					String y_axis = DataObject.getDataContents().get(chooseY.getSelectedIndex()-1);
					String title = x_axis + " To " + y_axis;
					chart = QuickChart.getChart(title, x_axis, y_axis, "Data: " + titleInt, xData, yData);
					chart.getStyler().setLegendVisible(true);
				    chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
				    titleInt++;
				}
				else{
					chart.addSeries("Data: " + titleInt, xData, yData);
					xData = new double[2];
					yData = new double[2];
					titleInt++;
				}
				
				count2 += 2;
			}
		}
	}
	
	protected void graphData() {
		if(this.xPanel != null) {
			remove(xPanel);
		}
		
		xData = new double[2];
		yData = new double[2];
		chart = null;
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
				if(rangeCheckDataY(data) || rangeCheckDataX(data) && (rangeCheckDataY(data.getObject2()) || rangeCheckDataX(data.getObject2()))) {
					xData[count%2] = Double.parseDouble(data.getDataList().get(chooseX.getSelectedIndex()-1));
					yData[count%2] = Double.parseDouble(data.getDataList().get(chooseY.getSelectedIndex()-1));
					
					xData[count%2+1] = Double.parseDouble(data.getObject2().getDataList().get(chooseX.getSelectedIndex()-1));
					yData[count%2+1] = Double.parseDouble(data.getObject2().getDataList().get(chooseY.getSelectedIndex()-1));
					
					if(chart == null) {
						String x_axis = DataObject.getDataContents().get(chooseX.getSelectedIndex()-1);
						String y_axis = DataObject.getDataContents().get(chooseY.getSelectedIndex()-1);
						String title = x_axis + " To " + y_axis;
						chart = QuickChart.getChart(title, x_axis, y_axis, "Data: " + titleInt, xData, yData);
						chart.getStyler().setLegendVisible(true);
					    chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
					    titleInt++;
					}
					else{
						chart.addSeries("Data: " + titleInt, xData, yData);
						xData = new double[2];
						yData = new double[2];
						titleInt++;
					}
					
					count += 2;
				}
			}
		}
	    
		xPanel = new XChartPanel<XYChart>(chart);
		add(xPanel, BorderLayout.CENTER);
		this.updateUI();
	}
	
}
