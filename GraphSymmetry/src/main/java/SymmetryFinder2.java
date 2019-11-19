import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import Isomorphism.src.*;

public class SymmetryFinder2 {
	
	private ArrayList<GraphVertex> vertexList;
	
	public SymmetryFinder2() {
		vertexList = new ArrayList<GraphVertex>();
	}
	
	public SymmetryFinder2(ArrayList<GraphVertex> vertList) {
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
	
	public boolean isSymmetric(SymmetryFinder2 vertList) {
		if(this.getammountVerticies() != vertList.getammountVerticies()) {
			return false;
		}
		else if(this.getammountEdges() != vertList.getammountEdges()) {
			return false;
		}
		else if(!this.getdegreeList().equals(vertList.getdegreeList())) {
			return false;
		}

		testSymmetry(vertList);
		
		System.out.println("List1:");
		for(int scan = 0; scan < vertexList.size(); scan++) {
			System.out.println(vertexList.get(scan));
		}
		
		System.out.println("List2:");
		for(int scan = 0; scan < vertList.getList().size(); scan++) {
			System.out.println(vertList.getList().get(scan));
		}
		
		return true;
	}
	
	
	private void testSymmetry(SymmetryFinder2 vertList) {
		ArrayList<GraphVertex> vert = vertList.getList();
		for(int scan = 0; scan < vertexList.size(); scan++) {
			for(int scanN = 0; scanN < vert.size(); scanN++) {
				System.out.println(vertexList.get(scan).getVertex() + " ---> " + vert.get(scanN).getVertex());
			}
			
		}
		
	}
	
	public String toString() {
		String result = "";
		
		for(int scan = 0; scan < vertexList.size(); scan++) {
			result += vertexList.get(scan) + " \n";
		}
		
		return result;
	}
}
