import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jgrapht.*;
import org.jgrapht.alg.isomorphism.*;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.AbstractGraph;
import org.jgrapht.graph.DefaultGraphType;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.util.SupplierUtil;

import java.util.function.*;

public class SymmetryFinder {
	
	private ArrayList<GraphVertex> vertexList;
	private String isomorphismString;
	
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
		ArrayList<Integer> verticies = new ArrayList<Integer>();
		for(int scan = 0; scan < vertexList.size(); scan++) {
			if(!verticies.contains(vertexList.get(scan).getVertex())) {
				verticies.add(vertexList.get(scan).getVertex());
			}
			for(int scan2 = 0; scan2 < vertexList.get(scan).getVertexList().size(); scan2++) {
				if(!verticies.contains(vertexList.get(scan).getVertexList().get(scan2))) {
					verticies.add(vertexList.get(scan).getVertexList().get(scan2));
				}
			}
		}
		return verticies.size();
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
		
		SimpleGraph<Integer, Integer> graph1 = new SimpleGraph<Integer, Integer>(SupplierUtil.createIntegerSupplier(), SupplierUtil.createIntegerSupplier(), false);
		SimpleGraph<Integer, Integer> graph2 = new SimpleGraph<Integer, Integer>(SupplierUtil.createIntegerSupplier(), SupplierUtil.createIntegerSupplier(), false);
		
		for(int scan = 0; scan < vertexList.size(); scan++) {
			graph1.addVertex(vertexList.get(scan).getVertex());
			for(int scan2 = 0; scan2 < vertexList.get(scan).getVertexList().size(); scan2 ++) {
				graph1.addVertex(vertexList.get(scan).getVertexList().get(scan2));
			}
		}
		for(int scan = 0; scan < vertList.getList().size(); scan++) {
			graph2.addVertex(vertList.getList().get(scan).getVertex());
			for(int scan2 = 0; scan2 < vertList.getList().get(scan).getVertexList().size(); scan2 ++) {
				graph2.addVertex(vertList.getList().get(scan).getVertexList().get(scan2));
			}
		}
		
		for(int scan = 0; scan < vertexList.size(); scan++) {
			for(int scanV = 0; scanV < vertexList.get(scan).getVertexList().size(); scanV++) {
				graph1.addEdge(vertexList.get(scan).getVertex(), vertexList.get(scan).getVertexList().get(scanV));
			}
		}
		
		for(int scan = 0; scan < vertList.getList().size(); scan++) {
			for(int scanV = 0; scanV < vertList.getList().get(scan).getVertexList().size(); scanV++) {
				graph2.addEdge(vertList.getList().get(scan).getVertex(), vertList.getList().get(scan).getVertexList().get(scanV));
			}
		}
		
		
		VF2GraphIsomorphismInspector<Integer, Integer> graphchecker = new VF2GraphIsomorphismInspector<Integer, Integer>(graph1, graph2);
		
		Iterator<GraphMapping<Integer, Integer>> integer = graphchecker.getMappings();
		
		if(graphchecker.isomorphismExists()) {
			isomorphismString = integer.next().toString();
		}
		
		return graphchecker.isomorphismExists();
	}
	
	public String getisomorphismString() {
		return isomorphismString;
	}
	
	public String toString() {
		String result = "";
		
		for(int scan = 0; scan < vertexList.size(); scan++) {
			result += vertexList.get(scan) + " \n";
		}
		
		return result;
	}
}
