/**
 * 
 */
package crossword;

import static org.junit.Assert.*;

import org.junit.Test;

import exception.TooBigCrosswordException;

/**
 * @author Jakub Fortunka
 *
 */
public class BoardTest {

	/**
	 * Test method for {@link crossword.Board#Board()}.
	 */
	@Test
	public void testBoard() {
		Board t = new Board();
		assertEquals(10, t.getHeight());
		assertEquals(10, t.getWidth());
	}

	/**
	 * Test method for {@link crossword.Board#Board(int, int)}.
	 * @throws TooBigCrosswordException 
	 */
	@Test
	public void testBoardIntInt() throws TooBigCrosswordException {
		Board t = new Board(5, 5);
		assertEquals(5, t.getHeight());
		assertEquals(5, t.getWidth());
	}
	
	/**
	 * Test method for {@link crossword.Board#Board(int, int)}
	 * 
	 * @throws TooBigCrosswordException
	 */
	@Test(expected=TooBigCrosswordException.class)
	public void testBoardIntIntTooBig() throws TooBigCrosswordException {
		Board t = new Board(40,40);
		fail();
	}

	/**
	 * Test method for {@link crossword.Board#createPattern(int, int, int, int)}.
	 * @throws TooBigCrosswordException 
	 */
	@Test
	public void testCreatePattern() throws TooBigCrosswordException {
		
		Board t = new Board(10,10);
		BoardCell c = new BoardCell();
		c.setContent("a");
		t.setCell(0, 0, c);
		String test = t.createPattern(0, 0, 5, 0, new SimpleStrategy());
		assertEquals("^a[A-Za-z]{2,5}$", test);
	}
	
}
