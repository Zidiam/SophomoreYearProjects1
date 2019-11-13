package temperature;
import javax.swing.*;

import java.awt.BorderLayout;

public class SliderGUI extends JPanel{

	private JScrollBar tempControl = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, -210, 310);
	private ValueStrategy vs;
	private SlideListener slideListener;

	public SliderGUI(ValueStrategy VS) {
		this.vs = VS;
		slideListener = new SlideListener(vs);
		
		setLayout(new BorderLayout());
		tempControl.setValue((int) vs.get());
		tempControl.addAdjustmentListener(slideListener);
		add(tempControl);
	}

	public void update() {
		tempControl.removeAdjustmentListener(slideListener);
		double temp = vs.get();
		tempControl.setValue((int) temp); // Move the slider thumb
		tempControl.addAdjustmentListener(slideListener);
	}

}