/**
 * 
 */
package crossword;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Jakub Fortunka
 *
 */
public class BoardCellTest {

	/**
	 * Test method for {@link crossword.BoardCell#BoardCell()}.
	 */
	@Test
	public void testBoardCell() {
		BoardCell t = new BoardCell();
		assertEquals("", t.getContent());
		assertNotNull(t);
	}

}
