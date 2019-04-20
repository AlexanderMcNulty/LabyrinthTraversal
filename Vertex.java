import java.util.LinkedList;
import java.awt.Color;
import java.util.Iterator;
/**
 * 
 * @author Anthony Jhong and Alexander McNulty 
 * Vertex that will make up our adjacency list for our labyrinth
 */

public class Vertex {
	private int index;     				//the index of the vertex
	private String value;				//the value of the vertex will either be " " or "#"
	private LinkedList<Vertex> edges;	//this holds all of the neighbors of the vertex 
	private Color vColor;				//color of the vertex
	private int distance;				//distance that was traveled from the first vertex (vertex 0)
	private Vertex parent;				//parent of the vertex
	private int viewNumber;				//the view number used by dfs and bfs
	
	/**
	 * Constructor creates and initializes all private instance variables
	 * @param index set the index when reading in the maze
	 */
	public Vertex(int index) {
		this.index = index;
		value = " ";
		edges = new LinkedList<>();
		vColor = Color.WHITE;
		distance = 0;
		parent = null;
		viewNumber = Integer.MIN_VALUE;
	}
	/**
	 * Adds a vertex to the the edge linkedlist 
	 * @param vertex vertex next to current vertex
	 */
	public void addEdge(Vertex vertex) {
		edges.add(vertex);
	}
	/**
	 * Returns the LinkedList of Edges
	 * @return ArrayList<Vertex> edges
	 */
	public Iterator<Vertex> getEdgeIterator() {
		return edges.iterator();
	}
	/**
	 * Returns the index of the vertex
	 * @return index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * returns the color of the vertex
	 * @return Color
	 */
	public Color getColor() {
		return vColor;
	}
	/**
	 * Sets the color of the vertex
	 * @param c color of the vertex
	 */
	public void setColor(Color c) {
		vColor = c;
	}
	/**
	 * Sets the distance of the vertex
	 * @param d distance
	 */
	public void setDistance(int d) {
		distance = d;
	}
	/**
	 * Returns the distance
	 * @return int distance
	 */
	public int getDistance() {
		return distance;
	}
	/**
	 * Returns the parent of this vertex
	 * @return Vertex parent
	 */
	public Vertex getParent() {
		return parent;
	}
	/**
	 * Sets the parent of the vertex
	 * @param v parent
	 */
	public void setParent(Vertex v) {
		parent = v;
	}
	/**
	 * Returns the value of the vertex
	 * @return String value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * Sets the value of the vertex
	 * @param s value
	 */
	public void setValue(String s) {
		value = s;
	}
	/**
	 * Sets the view number of the vertex
	 * @param num viewNumber
	 */
	public void setViewNumber(int num) {
		viewNumber = num;
	}
	/**
	 * Returns the viewNumber of the vertex
	 * @return int viewNumber
	 */
	public int getViewNumber() {
		return viewNumber;
	}
	/**
	 * Resets all of the values of the vertex
	 */
	public void reset() {
		value = " ";
		vColor = Color.WHITE;
		distance = 0;
		parent = null;
		viewNumber = Integer.MIN_VALUE;
	}
}
