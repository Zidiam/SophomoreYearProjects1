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
		vertexList.add(vert);
	}
	
	public ArrayList<Integer> getVertexList(){
		return vertexList;
	}
	
	public int getVertex() {
		return vertex;
	}
	
}
