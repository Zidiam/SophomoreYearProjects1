import java.util.ArrayList;

public class GraphVertex {
	int vertex;
	ArrayList<Integer> vertexList;
	
	public GraphVertex(int vert) {
		vertex = vert;
		vertexList = new ArrayList<Integer>();
	}
	
	public GraphVertex(int vert, ArrayList<Integer> vertList) {
		vertex = vert;
		vertexList = vertList;
	}
	
	public void addVertex(int vert) {
		boolean found = false;
		for(int x = 0; x < vertexList.size(); x++) {
			if(vert == vertexList.get(x)) {
				found = true;
				break;
			}
		}
		if(found == false) {
			vertexList.add(vert);
		}
	}
	
	public int getDegree() {
		return vertexList.size();
	}
	
	public ArrayList<Integer> getVertexList(){
		return vertexList;
	}
	
	public int getVertex() {
		return vertex;
	}
	
	public String toString() {
		return "Vertex: " + vertex + " VertexList: " + vertexList;
	}
	
}
