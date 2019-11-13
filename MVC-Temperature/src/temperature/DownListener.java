package temperature;
import java.awt.event.*;

class DownListener implements ActionListener {
	private ValueStrategy vs;

	public DownListener(ValueStrategy vs) {
		this.vs = vs;
	}

	public void actionPerformed(ActionEvent e) {
		vs.set(vs.get() - 1.0);
	}
}