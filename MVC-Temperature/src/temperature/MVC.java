package temperature;
import java.awt.*;

import javax.swing.*;
/*
 * Made by yo boi Jason Melnik
 */
public class MVC extends JFrame {

	private TemperatureModel model;

	private TemperatureGUI fGUI;
	private TemperatureGUI cGUI;
	private GaugeGUI gGUI;
	
	public MVC() {
		super("Temperature");
		setSize(600, 400);
//		setResizable(false);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		model = new TemperatureModel(); // Create the model

		fGUI = new TemperatureGUI("Fahrenheit Temperature", model,
				new FahrenheitStrategy(model));
		
		cGUI = new TemperatureGUI("Celsius Temperature", model,
				new CelsiusStrategy(model));

		gGUI = new GaugeGUI(model, new FahrenheitStrategy(model));
		
		add(gGUI, "Center");
		add(fGUI, "North");
		add(cGUI, "South");

		setVisible(true);

	}

	public static void main(String[] args) {
		new MVC();
	}
}
