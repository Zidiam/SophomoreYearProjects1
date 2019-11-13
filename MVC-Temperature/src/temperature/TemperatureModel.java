package temperature;
import java.util.Observable;

import javax.swing.JFrame;


// barely worth putting in a class! but converts temperatures

@SuppressWarnings("deprecation")
public class TemperatureModel extends Observable {
	
	private double temperatureF = 32.0;
	private JFrame temperatureFrame;
	
	public double getF() {
		return temperatureF;
	}

	public double getC() {
		return (temperatureF - 32.0) * 5.0 / 9.0;
	}

	public void setF(double tempF) {
		temperatureF = tempF;
		setChanged();					// these are the interesting lines
		notifyObservers();
	}

	public void setC(double tempC) {
		temperatureF = tempC * 9.0 / 5.0 + 32.0;
		setChanged();					// these are the interesting lines
		notifyObservers();
	}

}
