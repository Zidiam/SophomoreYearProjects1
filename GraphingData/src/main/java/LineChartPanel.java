import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.HashSet;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.Styler.LegendPosition;

public class LineChartPanel extends ChartPanel{
	public LineChartPanel(HashSet<DataObject> dataSet) {
		super(dataSet);
	}
	
	protected void createChart() {
		chart = new XYChartBuilder().build();
		chart.getStyler().setYAxisLogarithmic(false);
		chart.getStyler().setXAxisLogarithmic(false);
	    chart.getStyler().setXAxisLabelRotation(45);
	    chart.getStyler().setLegendVisible(true);
	    chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
	}
	
}
