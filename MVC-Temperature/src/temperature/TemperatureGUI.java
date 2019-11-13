package temperature;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import java.text.DecimalFormat;


/**
 * abstract GUI for temperature: displays temperature value and has "up" and
 * "down" buttons. Includes methods for adding listeners. Doesn't provide an
 * implementation of the crucial update() method -- that's for subclasses
 * 
 * @author sdexter72
 *
 */

public class TemperatureGUI extends JPanel implements Observer {
	private String label;
	private TemperatureModel model; // <-- this is the model this view
									// 'displays'
	private ValueStrategy vs; // this is the value-manipulation strategy for
								// this view

	DecimalFormat formatter = new DecimalFormat("####.##");
	
	private JTextField display = new JTextField(10);
	private JButton upButton = new JButton("Raise");
	private JButton downButton = new JButton("Lower");
	private SliderGUI sGUI;
	
	TemperatureGUI(String label, TemperatureModel m, ValueStrategy vs) {
		

		this.model = m;
		this.vs = vs;
		model.addObserver(this); // <-- this line connects the View to the Model
		
		display.addActionListener(new DisplayListener(vs));
		upButton.addActionListener(new UpListener(vs));
		downButton.addActionListener(new DownListener(vs));

		//makeSliderGUI
		sGUI = new SliderGUI(vs);
		
		// general layout stuff
		
		setLayout(new FlowLayout());
		add(new JLabel(label));
		setDisplay(formatter.format(vs.get()));
		add(display);
		add(upButton);
		add(downButton);
		add(sGUI);

		setVisible(true);
	}

	public void update(Observable t, Object o) {
		sGUI.update();
		setDisplay(formatter.format(vs.get()));
	}	

	public void setDisplay(String s) {
		
		display.setText(s);
	}

	public double getDisplay() {
		double result=0.0;
		try {
			result = Double.valueOf(display.getText()).doubleValue();
		} catch (NumberFormatException e) {
			// do nothing, for the moment
		}
		return result;
	}

	protected TemperatureModel model() {
		return model;
	}

	public static class CloseListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			e.getWindow().setVisible(false);
			System.exit(0);
		}
	}
}