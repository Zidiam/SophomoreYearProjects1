import javax.swing.JFrame;

public class GraphSymmetryGUI {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Graph Symmetry");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new GraphSymmetryPanel());
		
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(true);
	}
}
