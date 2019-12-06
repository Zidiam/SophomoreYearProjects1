import javax.swing.JFrame;
/*
 * GraphDataGUI -- The main class to run a program that graphs data in many ways
 * By: Jason Melnik
 * Date: 12/1/2019
 */
public class GraphDataGUI {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Graphing Data Program");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new GraphingPanel());
		
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
	}
}
