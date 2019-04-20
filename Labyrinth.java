
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
		//for(int i = 0; i < testSubject.traceDFSBestPath().size(); i++) {
          //  System.out.println(testSubject.traceDFSBestPath().get(i));
        //}
	}

	@Test
	public void testPrintLabyrinth() throws Exception {
		Labyrinth testSubject;
		testSubject = createTestSubject();
		String fileName = "maze10.txt";
		testSubject.readLabyrinth(fileName);	
		testSubject.traceDFSBestPath();
		testSubject.printLabyrinthText();
	}
	
	@Test
	public void testGenerateLabyrinth() throws Exception {
		Labyrinth testSubject;
		int length = 10;
		// default test
		testSubject=createTestSubject();
		testSubject.GenerateLabyrinth(length);
		testSubject.traceDFSBestPath();
		testSubject.printLabyrinthText();
	}
	
	
}
