
import javax.annotation.Generated;

import org.junit.Test;

@Generated(value = "org.junit-tools-1.1.0")
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
	}
}