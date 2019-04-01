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
			String line = br.readLine();
			br.readLine();
			String[] uncleanLine = {};
			int currentLine = 2;
			int currentVertex = 0;
			int lineSeperator = 1;
			int edgeDivider;
			String[] widthHeight = line.split(" ");
			int width = Integer.parseInt(widthHeight[0]);
			int height = Integer.parseInt(widthHeight[1]);
			createLabyrinth(width*height);
			while (currentLine++ < height*2+1) {
				line = br.readLine();
				if(lineSeperator++ % 2 == 1) {
					// we are reading a line containning vertices
					edgeDivider = 2;
					uncleanLine = line.split("");
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
					uncleanLine = line.split("");
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
			
			/*
			String[] cleanResults;
			while ((line = br.readLine()) != null) {
				if(!(line.equals(""))) {
				    parts = line.split("[\\[\\]]");
				    potentialEarnings.add(Arrays.stream(parts[1].split(", ")).mapToInt(Integer::parseInt).toArray());
				    uncleanResults = parts[2].split(", ");
				    cleanResults = Arrays.copyOfRange(uncleanResults, 1, uncleanResults.length);
				    results.add(Arrays.stream(cleanResults).mapToInt(Integer::parseInt).toArray());
				}
			}
			*/
			br.close();
		} catch (IOException e) {
			System.out.println("hi4");
		}
	}
	
}
