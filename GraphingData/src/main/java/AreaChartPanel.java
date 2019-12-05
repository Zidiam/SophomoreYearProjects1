import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.HashSet;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

public class AreaChartPanel extends ChartPanel{
	
	public AreaChartPanel(HashSet<DataObject> dataSet) {
		super(dataSet);;
	}
	
	protected void createChart() {
		chart = new XYChartBuilder().build();
	    chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);
	    chart.getStyler().setLegendVisible(true);
	    chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
	}
	
	protected void compareAllData(DataObject data) {
		int count2 = 0;
		for(DataObject scanData : dataSet) {
			if(rangeCheckDataY(scanData) || rangeCheckDataX(scanData)) {
				xData[count2%2] = Double.parseDouble(scanData.getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2] = Double.parseDouble(scanData.getDataList().get(chooseY.getSelectedIndex()-1));
				if (count2 != 0 && (count2 % 2) == 0){
					Arrays.parallelSort(xData);
					Arrays.parallelSort(yData);
					chart.addSeries("Data: " + titleInt, xData, yData);
					xData = new double[2];
					yData = new double[2];
					titleInt++;
				}
				
				count2 ++;
			}
		}
	}
	
	protected void compareAllDataToData(DataObject data) {
		int count2 = 0;
		for(DataObject scanData : dataSet) {
			if(rangeCheckDataY(scanData) || rangeCheckDataX(scanData)) {
				xData[count2%2] = Double.parseDouble(scanData.getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2] = Double.parseDouble(scanData.getDataList().get(chooseY.getSelectedIndex()-1));
				
				xData[count2%2+1] = Double.parseDouble(data.getObject2().getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2+1] = Double.parseDouble(data.getObject2().getDataList().get(chooseY.getSelectedIndex()-1));
				Arrays.parallelSort(xData);
				Arrays.parallelSort(yData);
				chart.addSeries("Data: " + titleInt, xData, yData);
				xData = new double[2];
				yData = new double[2];
				titleInt++;
				
				count2 += 2;
			}
		}
	}
	
	protected void compareDataToAllData(DataObject data) {
		int count2 = 0;
		for(DataObject scanData : dataSet) {
			if(rangeCheckDataY(scanData) || rangeCheckDataX(scanData)) {
				xData[count2%2] = Double.parseDouble(data.getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2] = Double.parseDouble(data.getDataList().get(chooseY.getSelectedIndex()-1));
				
				xData[count2%2+1] = Double.parseDouble(scanData.getDataList().get(chooseX.getSelectedIndex()-1));
				yData[count2%2+1] = Double.parseDouble(scanData.getDataList().get(chooseY.getSelectedIndex()-1));
				
				Arrays.parallelSort(xData);
				Arrays.parallelSort(yData);
				chart.addSeries("Data: " + titleInt, xData, yData);
				xData = new double[2];
				yData = new double[2];
				titleInt++;
				
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
					Arrays.parallelSort(xData);
					Arrays.parallelSort(yData);
					chart.addSeries("Data: " + titleInt, xData, yData);
					xData = new double[2];
					yData = new double[2];
					titleInt++;
					
					count += 2;
				}
			}
		}
	    
		xPanel = new XChartPanel<XYChart>(chart);
		add(xPanel, BorderLayout.CENTER);
		this.updateUI();
	}
	
}
