package temperature;
import java.awt.event.*;

class UpListener implements ActionListener {
	private ValueStrategy vs;

	public UpListener(ValueStrategy vs) {
		this.vs = vs;
	}

	public void actionPerformed(ActionEvent e) {
		vs.set(vs.get()+1.0);
	}
}