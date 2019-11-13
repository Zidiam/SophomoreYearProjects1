package temperature;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class DisplayListener implements ActionListener {
	private ValueStrategy vs;
	
	public DisplayListener(ValueStrategy vs) {
		this.vs = vs;
	}
	
	public void actionPerformed(ActionEvent e) {
		JTextField source = ((JTextField) e.getSource());
		double value = Double.valueOf(source.getText());
		
		vs.set(value);
	}
}