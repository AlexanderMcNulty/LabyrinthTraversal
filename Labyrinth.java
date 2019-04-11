import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.File;

public class Labyrinth {
	private Vertex[] currentLabyrinth;
	private String[] maze;
	private int dfsIterationNum;
	
	public Labyrinth() {
	}
	
	public void createLabyrinth(int size) {
		currentLabyrinth = new Vertex[size];
		for(int i = 0; i < size; i++) {
			currentLabyrinth[i] = new Vertex(i);
		}
	}
	
	public void createMaze(int height) {
		maze = new String[height];
	}
	
	public void printLabyrinthGraph() {
		for(Vertex vertex : currentLabyrinth) {
			System.out.print("\n" + vertex.getIndex() + " -> ");
			Iterator<Vertex> iter = vertex.getEdgeIterator(); 
			while(iter.hasNext()) {
				System.out.print(iter.next().getIndex() + ", ");
			}
		}
		System.out.println();
	}
	
	public void readGraph(String fileName) {
		try {
			//br - short for BufferReader
		    Scanner in = new Scanner(new File(fileName), "UTF-8");	
		    in.nextLine();
		    for(int i = 0; i < maze.length; i++) {
		    	maze[i] = in.nextLine();
		    }
			in.close();
		} 
		catch (IOException e) {
			System.out.println("hi4");
		}
	}
	
	public void readLabyrinth(String fileName) {
		try {
			//br - short for BufferReader
		    BufferedReader br = new BufferedReader(new FileReader(fileName));		
			int currentLine = 2;
			int currentVertex = 0;
			int lineSeperator = 1;
			int edgeDivider;
			String[] widthHeight = br.readLine().split(" ");
			int width = Integer.parseInt(widthHeight[0]);
			int height = Integer.parseInt(widthHeight[1]);
			createLabyrinth(width*height);
			br.readLine();
			String[] uncleanLine = {};
			while (currentLine++ < height*2+1) {
				uncleanLine = br.readLine().split("");
				if(lineSeperator++ % 2 == 1) {
					// we are reading a line containing vertices
					edgeDivider = 2;
					while(edgeDivider < uncleanLine.length) {
						if(uncleanLine[edgeDivider].equals(" ")) {
							currentLabyrinth[currentVertex].addEdge(currentLabyrinth[currentVertex+1]);
							currentLabyrinth[currentVertex+1].addEdge(currentLabyrinth[currentVertex]);
						}
						edgeDivider = edgeDivider + 2;
						currentVertex++;
					}
				} else {
					// we are reading a line between vertices
					edgeDivider = 1;
					currentVertex = currentVertex - width;
					while(edgeDivider < uncleanLine.length) {
						if(uncleanLine[edgeDivider].equals(" ")) {
							currentLabyrinth[currentVertex].addEdge(currentLabyrinth[currentVertex+width]);
							currentLabyrinth[currentVertex+width].addEdge(currentLabyrinth[currentVertex]);
						}
						edgeDivider = edgeDivider + 2;
						currentVertex++;
					}
				}
			}
			br.close();
		} 
		catch (IOException e) {
			System.out.println("hi4");
		}
	}
	
	public void resetAllVertices() {
		for(int i = 0; i < currentLabyrinth.length; i++) {
			currentLabyrinth[i].reset();
		}
	}
	
// 	public void dfs() {
// 		resetAllVertices();
		
// 		dfsIterationNum = 0;
		
// //		for(int i = 0; i < currentLabyrinth.length; i++) {
// //			if(currentLabyrinth[i].getColor().equals(Color.WHITE)) {
// //				dfsVisit(currentLabyrinth[i]);
// //			}
// //		}
// 		currentLabyrinth[0].setDistance(0);
// 		dfsVisit(currentLabyrinth[0]);
// 	}
	
// 	public void dfsVisit(Vertex v) {
// 		v.setColor(Color.GRAY);
	
// 		v.setViewNumber(dfsIterationNum);
		
// 		if(dfsIterationNum == 9)
// 			dfsIterationNum = 0;
// 		else 
// 			dfsIterationNum++;
		
// 		Iterator<Vertex> iter = v.getEdgeIterator();
		
// 		while(iter.hasNext()) {
// 			Vertex temp = iter.next();
// 			if(temp.getColor().equals(Color.WHITE)) {
// 				temp.setParent(v);
// 				temp.setDistance(v.getDistance() + 1);
// 				dfsVisit(temp);
// 			}
// 		}
		
// 		v.setColor(Color.BLACK);
	}
	
	public void bfs() {
		resetAllVertices();
		
		Queue<Vertex> queue = new LinkedList<>(); //initializes the queue 
		queue.add(currentLabyrinth[0]); //adds the first vertex to the queue
		int counter = 0;//what step we are on
		
		while(queue.size() != 0) {
	
			Vertex u = queue.remove(); //removes the vertex but stores it in u
			
			u.setViewNumber(counter);
			
			if(counter == 9)
				counter = 0;
			else 
				counter++;
			
			Iterator<Vertex> iter = u.getEdgeIterator();
			while(iter.hasNext()) {
				Vertex temp = iter.next();
				if(temp.getColor().equals(Color.WHITE)) { //if the color of the vertex is white
					temp.setColor(Color.GRAY); //change the color of the vertex to grey because it has been discovered
					temp.setDistance(u.getDistance()+1); //set the distance of the current verex to its parents+1
					temp.setParent(u); //sets the parent of the vertex
					queue.add(temp);
				}
			}
			u.setColor(Color.BLACK); //set the color of u to black as all of its edges have been discovered
		}
	}
	
	public ArrayList<Integer> traceBFSBestPath() {
		
		bfs(); //this calls BFS alg which will find the distance between 0 and every other connected vertex
		
		//this will hold an ArrayList of integers that represent the path to take to complete the maze
		ArrayList<Integer> path = new ArrayList<>(); 
		//Instance of a vertex that keeps track of which vertex we are on
		Vertex current = currentLabyrinth[currentLabyrinth.length-1];
	
		//this will loop through all of the parents of the last vertex(the exit) until we hit the entrance
		while(current.getParent() != null) {	//exit if the vertex has no parent		
			current.setValue("#"); //set the value of the current vertex to # for printing later
			path.add(0, current.getIndex()); //adds the current index to the start of the ArrayList
			current = current.getParent(); //sets current to the parent of current
		}
		
		path.add(0,0); //adds the entrance to the ArrayList
		
		return path; //returns the shortest path to the exit 
	}
}

