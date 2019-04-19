import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Labyrinth {
	private Vertex[] currentLabyrinth;
	private String[] maze;
	private int dfsIterationNum;

	public Labyrinth() {
	}

	public void createLabyrinth(int size) {
		currentLabyrinth = new Vertex[size];
		for (int i = 0; i < size; i++) {
			currentLabyrinth[i] = new Vertex(i);
		}
	}

	public void createMaze(int height) {
		maze = new String[height];
	}

	public void printLabyrinthGraph() {
		for (Vertex vertex : currentLabyrinth) {
			System.out.print("\n" + vertex.getIndex() + " -> ");
			Iterator<Vertex> iter = vertex.getEdgeIterator();
			while (iter.hasNext()) {
				System.out.print(iter.next().getIndex() + ", ");
			}
		}
		System.out.println();
	}

	public void generateMazeString() {
		int length = (int) (Math.sqrt((double) (currentLabyrinth.length)));
		maze = new String[2 * length + 1];

		int visitedCells = 0;

		// first line done
		maze[0] = "+ +";
		for (int i = 0; i < length - 1; i++) {
			maze[0] += "-+";
		}

		for (int j = 1; j < maze.length - 1; j += 2) {
			maze[j] = "|";
			maze[j + 1] = "+";
			for (int i = visitedCells; i < visitedCells + length; i++) {
				Iterator<Vertex> iter = currentLabyrinth[i].getEdgeIterator();
				boolean next = false;
				boolean below = false;
				while (iter.hasNext()) {

					Vertex v = iter.next();
					if (v.getIndex() == i + 1) {
						next = true;
					}

					if (v.getIndex() == i + length) {
						below = true;
					}

				}

				if (next == true)
					maze[j] += currentLabyrinth[i].getViewNumber() + " ";
				else
					maze[j] += currentLabyrinth[i].getViewNumber() + "|";

				if (i == length * length - 1) {
					below = true;
				}

				if (below == true)
					maze[j + 1] += " " + "+";
				else
					maze[j + 1] += "-" + "+";

			}
			visitedCells += length;
		}
	}

	public void printMaze() {
		generateMazeString();
		for (String s : maze) {
			System.out.println(s);
		}
	}

	public void readLabyrinth(String fileName) {
		try {
			// br - short for BufferReader
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			int currentLine = 2;
			int currentVertex = 0;
			int lineSeperator = 1;
			int edgeDivider;
			String[] widthHeight = br.readLine().split(" ");
			int width = Integer.parseInt(widthHeight[0]);
			int height = Integer.parseInt(widthHeight[1]);
			createLabyrinth(width * height);
			br.readLine();
			String[] uncleanLine = {};
			while (currentLine++ < height * 2 + 1) {
				uncleanLine = br.readLine().split("");
				if (lineSeperator++ % 2 == 1) {
					// we are reading a line containing vertices
					edgeDivider = 2;
					while (edgeDivider < uncleanLine.length) {
						if (uncleanLine[edgeDivider].equals(" ")) {
							currentLabyrinth[currentVertex].addEdge(currentLabyrinth[currentVertex + 1]);
							currentLabyrinth[currentVertex + 1].addEdge(currentLabyrinth[currentVertex]);
						}
						edgeDivider = edgeDivider + 2;
						currentVertex++;
					}
				} else {
					// we are reading a line between vertices
					edgeDivider = 1;
					currentVertex = currentVertex - width;
					while (edgeDivider < uncleanLine.length) {
						if (uncleanLine[edgeDivider].equals(" ")) {
							currentLabyrinth[currentVertex].addEdge(currentLabyrinth[currentVertex + width]);
							currentLabyrinth[currentVertex + width].addEdge(currentLabyrinth[currentVertex]);
						}
						edgeDivider = edgeDivider + 2;
						currentVertex++;
					}
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("hi4");
		}
	}

	public void resetAllVertices() {
		for (int i = 0; i < currentLabyrinth.length; i++) {
			currentLabyrinth[i].reset();
		}
	}

 	public void dfs() {
 		resetAllVertices(); //resets all of the vertices to their original state

 		dfsIterationNum = 0; //sets the current iteration number to zero
 		currentLabyrinth[0].setDistance(0); //since all mazes start at zero we can set d to zero
 		dfsVisit(currentLabyrinth[0]); //we only need to start form the 0th vertex
 	}

 	public void dfsVisit(Vertex v) {
 		v.setColor(Color.GRAY); //set color of current vertex to grey

 		v.setViewNumber(dfsIterationNum); //sets the view number of the vertex

 		if(dfsIterationNum == 9) //if iterationNum = 9 change it to zero
 			dfsIterationNum = 0;
 		else 
 			dfsIterationNum++; //else add 1 to it
 		
 		//Initialization of the vertex iterator
 		Iterator<Vertex> iter = v.getEdgeIterator();

 		while(iter.hasNext()) {
 			Vertex temp = iter.next(); //gets the next vertex in the LinkedList
 			if(temp.getColor().equals(Color.WHITE)) { //if the color of the vertex is white
 				temp.setParent(v); //sets the parent of the temporary vertex
 				temp.setDistance(v.getDistance() + 1); //sets the distance of the temporary vertex
 				dfsVisit(temp); //Recursive call of the of dfsVisit using the temp vertex
 			}
 		}
 		v.setColor(Color.BLACK); //sets the color of the current vertex to black
	}
 	
 	public ArrayList<Integer> traceDFSBestPath(){
 		dfs();
 		// this will hold an ArrayList of integers that represent the path to take to
 		// complete the maze
 		ArrayList<Integer> path = new ArrayList<>();
 		// Instance of a vertex that keeps track of which vertex we are on
 		Vertex current = currentLabyrinth[currentLabyrinth.length - 1];

 		// this will loop through all of the parents of the last vertex(the exit) until
 		// we hit the entrance
 		while (current.getParent() != null) { // exit if the vertex has no parent
 			current.setValue("#"); // set the value of the current vertex to # for printing later
 			path.add(0, current.getIndex()); // adds the current index to the start of the ArrayList
 			current = current.getParent(); // sets current to the parent of current
 		}

 		current.setValue("#");
 		path.add(0, 0); // adds the entrance to the ArrayList

 		return path; // returns the shortest path to the exit
 	}

	public void bfs() {
		resetAllVertices();

		Queue<Vertex> queue = new LinkedList<>(); // initializes the queue
		queue.add(currentLabyrinth[0]); // adds the first vertex to the queue
		int counter = 0;// what step we are on

		while (queue.size() != 0) {

			Vertex u = queue.remove(); // removes the vertex but stores it in u

			u.setViewNumber(counter);

			if (counter == 9)
				counter = 0;
			else
				counter++;

			Iterator<Vertex> iter = u.getEdgeIterator();
			while (iter.hasNext()) {
				Vertex temp = iter.next();
				if (temp.getColor().equals(Color.WHITE)) { // if the color of the vertex is white
					temp.setColor(Color.GRAY); // change the color of the vertex to grey because it has been discovered
					temp.setDistance(u.getDistance() + 1); // set the distance of the current verex to its parents+1
					temp.setParent(u); // sets the parent of the vertex
					queue.add(temp);
				}
			}
			u.setColor(Color.BLACK); // set the color of u to black as all of its edges have been discovered
		}
	}

	public ArrayList<Integer> traceBFSBestPath() {

		bfs(); // this calls BFS alg which will find the distance between 0 and every other
				// connected vertex

		// this will hold an ArrayList of integers that represent the path to take to
		// complete the maze
		ArrayList<Integer> path = new ArrayList<>();
		// Instance of a vertex that keeps track of which vertex we are on
		Vertex current = currentLabyrinth[currentLabyrinth.length - 1];

		// this will loop through all of the parents of the last vertex(the exit) until
		// we hit the entrance
		while (current.getParent() != null) { // exit if the vertex has no parent
			current.setValue("#"); // set the value of the current vertex to # for printing later
			path.add(0, current.getIndex()); // adds the current index to the start of the ArrayList
			current = current.getParent(); // sets current to the parent of current
		}

		current.setValue("#");
		path.add(0, 0); // adds the entrance to the ArrayList

		return path; // returns the shortest path to the exit
	}

	public void GenerateMaze(int length) {
		createLabyrinth(length*length);
		ArrayList<Vertex> potentialNeighbors;
		ArrayList<Vertex> stack = new ArrayList<>();
		Vertex currentCell = currentLabyrinth[0];
        Vertex newNeighbor;
		Random rand = new Random(); 
		int visitedCells = 1;
		while(visitedCells < length*length) {
			potentialNeighbors = getPotentialNeighbors(length, currentCell.getIndex());
			if(potentialNeighbors.size() > 0) {
				newNeighbor = potentialNeighbors.get(rand.nextInt(potentialNeighbors.size()));
				currentCell.addEdge(newNeighbor);
				newNeighbor.addEdge(currentCell);
				stack.add(currentCell);
				currentCell = newNeighbor;
				visitedCells++;
			} else {
				if(stack.size() > 0) {
					currentCell = stack.get(stack.size()-1);
					stack.remove(stack.size()-1);
				}
			}
		}
	}

	public ArrayList<Vertex> getPotentialNeighbors(int length, int index) {
		ArrayList<Vertex> potentialNeighbors = new ArrayList<>();
		//top left
		if (index == 0) {
			potentiallyAdd(currentLabyrinth[index + 1], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index + length], potentialNeighbors);
		} 
		//top right
		else if (index == length - 1) {
			potentiallyAdd(currentLabyrinth[index - 1], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index + length], potentialNeighbors);
		} 
		//top edge
		else if (index < length) {
			potentiallyAdd(currentLabyrinth[index + 1], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index - 1], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index + length], potentialNeighbors);
		} 
		//left edge
		else if (index % length == 0 && index < length * (length - 1)) {
			potentiallyAdd(currentLabyrinth[index + 1], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index - length], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index + length], potentialNeighbors);
		}
		//right edge
		else if (index % length == length - 1 && index < length * (length - 1)) {
			potentiallyAdd(currentLabyrinth[index - 1], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index - length], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index + length], potentialNeighbors);
		} 
		//bottom left
		else if (index == length * (length - 1)) {
			potentiallyAdd(currentLabyrinth[index + 1], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index - length], potentialNeighbors);
		}
		//bottom right
		else if (index == length * length - 1) {
			potentiallyAdd(currentLabyrinth[index - 1], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index - length], potentialNeighbors);
		} 
		//bottom edge
		else if (index > length * (length - 1)) {
			potentiallyAdd(currentLabyrinth[index - 1], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index + 1], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index - length], potentialNeighbors);
		} 
		// for all over cells
		else {
			potentiallyAdd(currentLabyrinth[index - 1], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index + 1], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index - length], potentialNeighbors);
			potentiallyAdd(currentLabyrinth[index + length], potentialNeighbors);
		}
		return potentialNeighbors;
	}
	
	public void potentiallyAdd(Vertex cur, ArrayList<Vertex> potentialNeighbors) {
		Iterator<Vertex> iter = cur.getEdgeIterator();
		if(!(iter.hasNext())) {
			potentialNeighbors.add(cur);
		}
	}
}
