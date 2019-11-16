import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GraphPanel extends JPanel{
	private ArrayList<Point> pointsList;
	private ArrayList<GraphPoint> graphPointList;
	private int verticies;
	 public GraphPanel() {
	    	setPreferredSize(new Dimension(1350, 625));
	    	setLayout(null);
	    	
	    	this.setBackground(Color.cyan);
	 }
	 
	 public void setuppointList(ArrayList<Point> pointList, int amount) {
		 pointsList = pointList;
		 verticies = amount;
	 }
	 
	 public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		
		setUpPoints();
		drawLines(page);
		drawPoints(page);
		
	}
	 
	 public void setUpPoints() {
		 	graphPointList = new ArrayList<GraphPoint>();
		 	int n = verticies;
		 	this.setPreferredSize(new Dimension(1250 + n*3, 750 + n*3));
		 	int a = this.getWidth() / 2;
	        int b = this.getHeight() / 2 - 75;
	        int m = Math.min(a, b);
	        int r = 4 * m / 5;
	        int r2 = Math.abs(m - r) / 2;
	        for (int i = 0; i < n; i++) {
	            double t = 2 * Math.PI * i / n;
	            int x = (int) Math.round(a + r * Math.cos(t));
	            int y = (int) Math.round(b + r * Math.sin(t));
	            GraphPoint graphPoint = new GraphPoint(x - r2, y - r2, i+1);
	            graphPoint.setBounds(x - r2, y - r2, 15, 15);
	            graphPointList.add(graphPoint);
	        }
	 }
	 
	 public void drawPoints(Graphics page) {
		 for(int x = 0; x < graphPointList.size(); x++) {
			 graphPointList.get(x).paint(page);
		 }
	 }
	 
	 public void drawLines(Graphics page) {
		 for(int scan = 0; scan < pointsList.size(); scan++) {
			 Point first = new Point();
			 Point second = new Point();
			 boolean foundFirst = false;
			 boolean foundSecond = false;
			 for(int find = 0; find < graphPointList.size(); find++) {
				 if(pointsList.get(scan).x == graphPointList.get(find).getValue()) {
					 first = new Point(graphPointList.get(find).getX()+7, graphPointList.get(find).getY()+7);
					 foundFirst = true;
				 }
				 if(pointsList.get(scan).y == graphPointList.get(find).getValue()) {
					 second = new Point(graphPointList.get(find).getX()+7, graphPointList.get(find).getY()+7);
					 foundSecond = true;
				 }
				 if(foundSecond == true && foundFirst == true) {
					 break;
				 }
			 }
			 page.setColor(Color.black);
			 page.drawLine(first.x, first.y, second.x, second.y);
		 }
	 }
	 
	 
	 
	 
}
