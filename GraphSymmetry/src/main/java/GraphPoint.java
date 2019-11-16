import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JLabel;

public class GraphPoint extends JLabel{
	private Point point;
	private int value;
	
	public GraphPoint(int x, int y, int val) {
		point = new Point(x, y);
		value = val;
		repaint();
	}
	
	public int getValue() {
		return value;
	}
	
	public Point getCords() {
		return point;
	}
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		page.setColor(Color.red);
		page.fillOval(point.x, point.y, 15, 15);
		page.setColor(Color.MAGENTA);
		page.drawString(value + "", point.x, point.y);
	}
}
