import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
		ArrayList<Point> edgeList = new ArrayList<Point>();
		
		for(int scan = 0; scan < vertexList.size(); scan++) {
			for(int scanVertex = 0; scanVertex < vertexList.get(scan).getVertexList().size(); scanVertex++) {
				Point temp = new Point(vertexList.get(scan).getVertex(), vertexList.get(scan).getVertexList().get(scanVertex));
				Point temp2 = new Point(temp.y, temp.x);
				if(!temp.equals(temp2) && !edgeList.contains(temp) && !edgeList.contains(temp2)) {
					edgeList.add(temp);
				}
				
			}
		}
		 
		return edgeList.size();
	}
	
	public ArrayList<Integer> getdegreeList(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int scan = 0; scan < vertexList.size(); scan++) {
			result.add(vertexList.get(scan).getVertexList().size());
		}
		
		Collections.sort(result);
		
		return result;
	}
	
	public boolean isSymmetric(SymmetryFinder vertList) {
		if(this.getammountVerticies() != vertList.getammountVerticies()) {
			return false;
		}
		else if(this.getammountEdges() != vertList.getammountEdges()) {
			return false;
		}
		else if(!this.getdegreeList().equals(vertList.getdegreeList())) {
			return false;
		}
		
		
		return true;
	}
	
	public String toString() {
		String result = "";
		
		for(int scan = 0; scan < vertexList.size(); scan++) {
			result += vertexList.get(scan) + " \n";
		}
		
		return result;
	}
}
