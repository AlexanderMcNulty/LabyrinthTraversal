import java.util.ArrayList;
import java.util.Iterator;

public class Vertex {
	private int value;
	ArrayList<Vertex> edges;
	
	public Vertex(int value) {
		this.value = value;
		edges = new ArrayList<>();
	}
	
	public void addEdge(Vertex vertex) {
		edges.add(vertex);
	}
	
	public Iterator<Vertex> getEdgeIterator() {
		return edges.iterator();
	}
	
	public int getValue() {
		return value;
	}
	
}
