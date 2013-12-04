/**
 * 
 */
package interfaces;

import java.io.IOException;

import crossword.Crossword;
import crossword.WordNotFoundException;

/**
 * @author Jakub Fortunka
 *
 */
public interface Reader {

	public Crossword getAllCws() throws IOException, WordNotFoundException;
}
