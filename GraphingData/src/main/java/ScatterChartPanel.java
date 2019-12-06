import java.util.HashSet;

import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

/*
 * ScatterChartPanel -- A panel that contains a scatter chart and button controls that can edit that scatter chart
 * This class also extends ChartPanel to help reduce code since it will use the super classes other methods
 * By: Jason Melnik
 * Date: 12/1/2019
 */
public class ScatterChartPanel extends ChartPanel{
	
	/**
	 * This takes in a dataSet to that we can use that data to graph onto a scatter chart to make it more visual
	 * @param dataSet is a set of DataObjects so that we can graph the data
	 */
	public ScatterChartPanel(HashSet<DataObject> dataSet) {
		super(dataSet);
	}
	
	/**
	 * This method overrides the super method because it needs to build a chart specific for scatter charts
	 * This method creates a chart object for the class to input data into
	 */
	@Override
	protected void createChart() {
		chart = new XYChartBuilder().build();
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
	    chart.getStyler().setLegendVisible(true);
	    chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
	    chart.getStyler().setMarkerSize(8);
	}
	
}
