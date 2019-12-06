import java.util.HashSet;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

/*
 * LineChartPanel -- A panel that contains an line chart and button controls that can edit that line chart
 * This class also extends ChartPanel to help reduce code since it will use the super classes other methods
 * By: Jason Melnik
 * Date: 12/1/2019
 */
public class LineChartPanel extends ChartPanel{
	public LineChartPanel(HashSet<DataObject> dataSet) {
		super(dataSet);
	}
	
	/**
	 * Used to build a chart specific for Line charts
	 */
	@Override
	protected void createChart() {
		chart = new XYChartBuilder().build();
		chart.getStyler().setYAxisLogarithmic(false);
		chart.getStyler().setXAxisLogarithmic(false);
	    chart.getStyler().setXAxisLabelRotation(45);
	    chart.getStyler().setLegendVisible(true);
	    chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
	}
	
}
