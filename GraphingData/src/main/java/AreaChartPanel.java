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
	protected XYChart chart = new XYChartBuilder().build();
	
	public AreaChartPanel(HashSet<DataObject> dataSet) {
		super(dataSet);
	    chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Area);
	    chart.getStyler().setLegendVisible(true);
	    chart.getStyler().setLegendPosition(LegendPosition.OutsideS);
	}
	
}
