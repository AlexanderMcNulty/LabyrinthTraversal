import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class Labyrinth {
	Vertex[] currentLabyrinth;
	
	public Labyrinth() {
	}
	
	public void createLabyrinth(int size) {
		currentLabyrinth = new Vertex[size];
		for(int i = 0; i < size; i++) {
			currentLabyrinth[i] = new Vertex(i);
		}
	}
	
	public void printLabyrinthGraph() {
		for(Vertex vertex : currentLabyrinth) {
			System.out.print("\n" + vertex.getValue() + " -> ");
			Iterator<Vertex> iter = vertex.getEdgeIterator(); 
			while(iter.hasNext()) {
				System.out.print(iter.next().getValue() + ", ");
			}
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
					// we are reading a line containning vertices
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
		} catch (IOException e) {
			System.out.println("Filename not found!!!");
		}
	}
	
}
