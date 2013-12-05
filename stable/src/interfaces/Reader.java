/**
 * 
 */
package interfaces;

import java.io.IOException;
import java.util.LinkedList;

import crossword.Crossword;
import crossword.WordNotFoundException;

/**
 * @author Jakub Fortunka
 *
 */
public interface Reader {

	public LinkedList<Crossword> getAllCws() throws IOException, WordNotFoundException;
}
