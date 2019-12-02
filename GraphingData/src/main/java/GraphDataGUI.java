import javax.swing.JFrame;

public class GraphDataGUI {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Graph Data");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new GraphingPanel());
		
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
	}
}
