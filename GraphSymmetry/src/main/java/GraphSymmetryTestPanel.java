import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GraphSymmetryTestPanel extends JPanel{
	JLabel graphA, graphB, verticiesA, verticiesB, edgesA, edgesB, symmetric, symmetricPath;
	SymmetryFinder listA, listB;
	ArrayList<GraphVertex> vertexListB;
	
	public GraphSymmetryTestPanel(ArrayList<GraphVertex> vertListA, ArrayList<GraphVertex> vertListB) {
		listA = new SymmetryFinder(vertListA);
		listB = new SymmetryFinder(vertListB);
		
		setPreferredSize(new Dimension(1350, 625));
		setLayout(new GridLayout(4, 4));
		setupLabels();
		
	}
	
	private void setupLabels() {
		graphA = new JLabel("Graph A: ");
		graphB = new JLabel("Graph B: ");
		verticiesA = new JLabel("Graph A Verticies: " + listA.getammountVerticies());
		verticiesB = new JLabel("Graph B Verticies: " + listB.getammountVerticies()); 
		edgesA = new JLabel("Graph A Edges: " + listA.getammountEdges()); 
		edgesB = new JLabel("Graph B Edges: " + listB.getammountEdges()); 
		symmetric = new JLabel("Graph A and B are Symmetric " + listA.isSymmetric(listB)); 
		symmetricPath = new JLabel("Path: " + listA.getisomorphismString());
		
		add(graphA);
		add(graphB);
		add(verticiesA);
		add(verticiesB);
		add(edgesA);
		add(edgesB);
		add(symmetric);
		add(symmetricPath);
	}
}
