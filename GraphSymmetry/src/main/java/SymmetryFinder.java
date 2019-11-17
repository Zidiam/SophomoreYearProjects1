import java.util.ArrayList;

public class SymmetryFinder {
	
	private ArrayList<GraphVertex> vertexList;
	
	public SymmetryFinder() {
		vertexList = new ArrayList<GraphVertex>();
	}
	
	public SymmetryFinder(ArrayList<GraphVertex> vertList) {
		vertexList = vertList;
	}
	
	public ArrayList<GraphVertex> getList(){
		return vertexList;
	}
	
	public int getammountVerticies() {
		return vertexList.size();
	}
	
	public int getammountEdges() {
		return 0;
	}
	
	public boolean isSymmetric(ArrayList<GraphVertex> vertList) {
		
		return false;
	}
}
