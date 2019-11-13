package temperature;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.*;

class SlideListener implements AdjustmentListener {
	
	private ValueStrategy vs;

	public SlideListener(ValueStrategy vs) {
		this.vs = vs;
	}
	public void adjustmentValueChanged(AdjustmentEvent e) {
		vs.set(e.getValue());
	}
}