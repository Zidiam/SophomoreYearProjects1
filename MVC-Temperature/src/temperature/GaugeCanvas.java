package temperature;
import java.awt.LayoutManager;

import javax.swing.JPanel;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class GaugeCanvas extends JPanel{

	private TemperatureGauge gauge;
	private static final int WIDTH = 20;
	private static final int TOP = 20;
	private static final int LEFT = 100;
	private static final int RIGHT = 250;
	private static final int HEIGHT = 200;

	public GaugeCanvas(TemperatureGauge gauge) {
		this.gauge = gauge;
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.black);
		g.drawRect(LEFT, TOP, WIDTH, HEIGHT);
		g.setColor(Color.red);
		g.fillOval(LEFT - WIDTH / 2, TOP + HEIGHT - WIDTH / 3, WIDTH * 2,
				WIDTH * 2);
		g.setColor(Color.black);
		g.drawOval(LEFT - WIDTH / 2, TOP + HEIGHT - WIDTH / 3, WIDTH * 2,
				WIDTH * 2);
		g.setColor(Color.white);
		g.fillRect(LEFT + 1, TOP + 1, WIDTH - 1, HEIGHT - 1);
		g.setColor(Color.red);
		long redtop = HEIGHT * (gauge.get() - gauge.getMax())
				/ (gauge.getMin() - gauge.getMax());
		g.fillRect(LEFT + 1, TOP + (int) redtop, WIDTH - 1, HEIGHT
				- (int) redtop);
	}

}
