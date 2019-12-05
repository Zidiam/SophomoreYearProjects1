import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.HashSet;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

/*
 * AreaChartPanel -- A panel that contains an area chart and button controls that can edit that area chart
 * This class also extends ChartPanel to help reduce code since it will use the super classes other methods
 * By: Jason Melnik
 * Date: 12/1/2019
 */
public class AreaChartPanel extends ChartPanel{
	
	/**
	 * This takes in a dataSet to that we can use that data to graph onto a area chart to make it more visual
	 * @param dataSet is a set of DataObjects so that we can graph the data
	 */
	public AreaChartPanel(HashSet<DataObject> dataSet) {
		super(dataSet);;
	}
	
	/**
	 * This method overrides the super method because it needs to build a chart specific for area charts
	 * This method creates a chart object for the class to input data into
	 */
	@Override
	protected void createChart() {
		chart = new XYChartBuilder().build();
	    chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);
	    chart.getStyler().setLegendVisible(true);
	    chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
	}
	
	/**
	 * This method adds data to the chart
	 * @param data is a DataObject that we use to compare all the data in the dataSet to that data
	 */
	@Override
	protected void compareAllData(DataObject data) {
		int count2 = 0;
		for(DataObject scanData : dataSet) {
			if(rangeCheckDataY(scanData) && rangeCheckDataX(scanData)) {
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
	
	/**
	 * This method adds data to the chart
	 * @param data is a DataObject that we use to compare all the data in the dataSet to that data
	 */
	@Override
	protected void compareAllDataToData(DataObject data) {
		int count2 = 0;
		for(DataObject scanData : dataSet) {
			if(rangeCheckDataY(scanData) && rangeCheckDataX(scanData)) {
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
	
	/**
	 * This method adds data to the chart
	 * @param data is a DataObject that we use to compare all the data in the dataSet to that data
	 */
	@Override
	protected void compareDataToAllData(DataObject data) {
		int count2 = 0;
		for(DataObject scanData : dataSet) {
			if(rangeCheckDataY(scanData) && rangeCheckDataX(scanData)) {
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
	
	/**
	 * This method builds the graph full of data and then inputs in into the panel
	 */
	@Override
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
