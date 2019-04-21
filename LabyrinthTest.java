import javax.annotation.Generated;

import org.junit.Test;
import java.util.*;
import org.junit.Assert;

public class LabyrinthTest {

	private Labyrinth createTestSubject() {
		return new Labyrinth();
	}

	@Test
	public void testReadLabyrinth() throws Exception {
		Labyrinth testSubject;
		String fileName = "maze10.txt";

		// default test
		testSubject = createTestSubject();
		testSubject.readLabyrinth(fileName);
	}

	@Test
	public void testCreateLabyrinth() throws Exception {
		Labyrinth testSubject;
		int size = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.createLabyrinth(size);
	}
	
	@Test
	public void testProfessorTest() throws Exception{
		Labyrinth testSubject;
		testSubject=createTestSubject();
	
		String[] fileNames =  {"maze10.txt", "maze20.txt", "maze4.txt", "maze6.txt", "maze8.txt", "maze2.txt"};
		
		
		for(String s: fileNames) {
			testSubject.readLabyrinth(s);
			testSubject.printLabyrinthText(true);
			
			System.out.println("Professor Test (" + testSubject.getLabyrinthLength() + " " + testSubject.getLabyrinthLength() + ")");
			System.out.println("Depth First Search");
			
			ArrayList<Integer> path = testSubject.traceDFSBestPath();
			
			System.out.println("The order by which all of the vertices were found(BFS):");
			testSubject.printLabyrinthText(true);
			
			System.out.println("The best path to finish the maze(DFS):");
			testSubject.printLabyrinthText(false);
			
			System.out.print("Path(DFS):");
			for(int i = 0; i < path.size(); i++) {
				System.out.print(path.get(i) + ",");
			}
			System.out.println();
			System.out.println("Length of Path: " + path.size());
			System.out.println("Visited Cells: " + testSubject.getVisitedCells());
			System.out.println();
			
			
			
			System.out.println("Breadth First Search");
			path = testSubject.traceBFSBestPath();
			
			System.out.println("The order by which all of the vertices were found(BFS):");
			testSubject.printLabyrinthText(true);
			
			System.out.println("The best path to finish the maze(BFS):");
			testSubject.printLabyrinthText(false);
			
			System.out.print("Path(BFS): ");
			for(int i = 0; i < path.size(); i++) {
				System.out.print(path.get(i) + ",");
			}
			System.out.println();
			System.out.println("Length of Path: " + path.size());
			System.out.println("Visited Cells: " + testSubject.getVisitedCells());
			System.out.println();
		}
		
	}
	
}
