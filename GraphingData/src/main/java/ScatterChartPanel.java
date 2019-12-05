import java.awt.BorderLayout;
import java.util.HashSet;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

public class ScatterChartPanel extends ChartPanel{
	public ScatterChartPanel(HashSet<DataObject> dataSet) {
		super(dataSet);
	}
	
	protected void createChart() {
		chart = new XYChartBuilder().build();
		chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
	    chart.getStyler().setLegendVisible(true);
	    chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
	    chart.getStyler().setMarkerSize(8);
	}
	
}
