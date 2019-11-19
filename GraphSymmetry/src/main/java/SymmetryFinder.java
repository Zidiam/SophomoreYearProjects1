import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import Isomorphism.src.*;

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
		else if(testSymmetry(vertList) == false) {
			return false;
		}
		
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
	
	
	private boolean testSymmetry(SymmetryFinder vertList) {
		AbstractGraphIsomorphismChecker<DirectedGraphNode> checker = new TrivialDirectedGraphIsomorphismChecker();
		Graph<DirectedGraphNode> graph1 = new Graph<>();
		ArrayList<Point> pointList = new ArrayList<Point>();
		ArrayList<DirectedGraphNode> nodeList = new ArrayList<DirectedGraphNode>();
		for(int scan = 0; scan < vertexList.size(); scan++) {
			DirectedGraphNode temp = new DirectedGraphNode(vertexList.get(scan).getVertex() + "");
			graph1.addNode(temp);
			nodeList.add(temp);
		}
		
		for(int scan = 0; scan < vertexList.size(); scan++) {
			for(int tempScan = 0; tempScan < vertexList.get(scan).getVertexList().size(); tempScan++) {
				Point tempPoint = new Point(vertexList.get(scan).getVertex(), (vertexList.get(scan).getVertexList().get(tempScan)));
				Point tempPoint2 = new Point(tempPoint.y, tempPoint.x);
				if(!pointList.contains(tempPoint) && !pointList.contains(tempPoint2)) {
					pointList.add(tempPoint);
					for(int find = 0; find < nodeList.size(); find++) {
						if(nodeList.get(find).getName().equals(tempPoint.x + "")) {
							for(int find2 = 0; find2 < nodeList.size(); find2++) {
								if(nodeList.get(find2).getName().equals(vertexList.get(scan).getVertexList().get(tempScan) + "")) {
									nodeList.get(find).addChild(nodeList.get(find2));
								}
							}
							
						}
					}
				}
			}
		}
		
		Graph<DirectedGraphNode> graph2 = new Graph<>();
		pointList = new ArrayList<Point>();
		nodeList = new ArrayList<DirectedGraphNode>();
		for(int scan = 0; scan < vertList.getList().size(); scan++) {
			DirectedGraphNode temp = new DirectedGraphNode(vertList.getList().get(scan).getVertex() + "");
			graph2.addNode(temp);
			nodeList.add(temp);
		}
		
		for(int scan = 0; scan < vertList.getList().size(); scan++) {
			for(int tempScan = 0; tempScan < vertList.getList().get(scan).getVertexList().size(); tempScan++) {
				Point tempPoint = new Point(vertList.getList().get(scan).getVertex(), (vertList.getList().get(scan).getVertexList().get(tempScan)));
				Point tempPoint2 = new Point(tempPoint.y, tempPoint.x);
				if(!pointList.contains(tempPoint) && !pointList.contains(tempPoint2)) {
					pointList.add(tempPoint);
					for(int find = 0; find < nodeList.size(); find++) {
						if(nodeList.get(find).getName().equals(tempPoint.x + "")) {
							for(int find2 = 0; find2 < nodeList.size(); find2++) {
								if(nodeList.get(find2).getName().equals(vertList.getList().get(scan).getVertexList().get(tempScan) + "")) {
									nodeList.get(find).addChild(nodeList.get(find2));
								}
							}
							
						}
					}
				}
			}
		}
		
		Map<DirectedGraphNode, DirectedGraphNode> isomorphism = checker.getIsomorphism(graph1, graph2);
		if(isomorphism == null) {
			return false;
		}
		else
			return Utils.isIsomorphism(isomorphism);
	}
	
	public String toString() {
		String result = "";
		
		for(int scan = 0; scan < vertexList.size(); scan++) {
			result += vertexList.get(scan) + " \n";
		}
		
		return result;
	}
}
