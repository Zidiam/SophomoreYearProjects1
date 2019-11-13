package temperature;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import java.awt.*;

public class GaugeGUI extends JPanel implements Observer {

	private TemperatureModel model;
	private TemperatureGauge gauge;
	private GaugeCanvas canvas;
	private ValueStrategy vs;
	public GaugeGUI(TemperatureModel m, ValueStrategy VS) {
		this.model = m;
		this.vs = VS;
		gauge = new TemperatureGauge(-200, 300);
		canvas = new GaugeCanvas(gauge);
		model.addObserver(this); // Connect to the model

		canvas.setSize(20, 200);
		add(canvas);
		repaint();
		setVisible(true);
	}

	public void update(Observable obs, Object o) // Respond to changes
	{
		repaint();
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		int farenheit = (int) vs.get();
		gauge.set(farenheit);
		canvas.paint(g);
	}
}