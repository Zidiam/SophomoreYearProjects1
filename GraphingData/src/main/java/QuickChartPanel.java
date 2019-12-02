import java.awt.BorderLayout;
import java.util.HashSet;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.Styler.LegendPosition;

public class QuickChartPanel extends ChartPanel{
	
	public QuickChartPanel(HashSet<DataObject> dataSet) {
		super(dataSet);
	}

	
	protected void graphData() {
		if(this.xPanel != null) {
			remove(xPanel);
		}
		
		HashSet<DataObject> dataSetx = new HashSet<DataObject>(dataSet);
		
		if(chooseObjectX.getSelectedIndex() != 1) {
			dataSetx.clear();
			dataSetx.add((DataObject) chooseObjectX.getSelectedItem());
			dataSetx.add((DataObject) chooseObjectY.getSelectedItem());
		}
	
		double[] xData = new double[2];
		double[] yData = new double[2];
		XYChart chart = null;
		int count = 0;
		for(DataObject data : dataSetx) {
			xData[count%2] = Double.parseDouble(data.getDataList().get(chooseX.getSelectedIndex()-1));
			yData[count%2] = Double.parseDouble(data.getDataList().get(chooseY.getSelectedIndex()-1));
			if(count == 1) {
				String x_axis = DataObject.getDataContents().get(chooseX.getSelectedIndex()-1);
				String y_axis = DataObject.getDataContents().get(chooseY.getSelectedIndex()-1);
				String title = x_axis + " To " + y_axis;
				chart = QuickChart.getChart(title, x_axis, y_axis, "Data: 0", xData, yData);
				chart.getStyler().setLegendVisible(true);
			    chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
			}
			else if(count % 2 == 0 && count != 0) {
				chart.addSeries("Data: " + count/2, xData, yData);
				xData = new double[2];
				yData = new double[2];
			}
			
			count ++;
		}
	    
		xPanel = new XChartPanel<XYChart>(chart);
		add(xPanel, BorderLayout.CENTER);
		this.updateUI();
	}
	
}
