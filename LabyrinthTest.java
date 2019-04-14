
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
	public void testPrintLabyrinthGraph() throws Exception {
		Labyrinth testSubject;

		// default test
		testSubject = createTestSubject();
		String fileName = "maze10.txt";
		testSubject.readLabyrinth(fileName);		
		testSubject.printLabyrinthGraph();
		for(int i = 0; i < testSubject.traceBFSBestPath().size(); i++) {
            System.out.println(testSubject.traceBFSBestPath().get(i));
        }
	}

	@Test
	public void testPrintMaze() throws Exception {
		Labyrinth testSubject;
		testSubject = createTestSubject();
		String fileName = "maze10.txt";
		testSubject.readLabyrinth(fileName);	
		testSubject.traceBFSBestPath();
		testSubject.printMaze();
	}
	
	@Test
	public void testGenerateMaze() throws Exception {
		Labyrinth testSubject;
		int length = 20;
		// default test
		System.out.println("start");
		testSubject=createTestSubject();
		testSubject.GenerateMaze(length);
		testSubject.traceBFSBestPath();
		testSubject.printMaze();
		System.out.println("finish");
	}
	
	
}
