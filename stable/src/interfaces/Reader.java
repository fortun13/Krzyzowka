/**
 * 
 */
package interfaces;

import java.io.IOException;

import crossword.WordNotFoundException;

/**
 * @author Jakub Fortunka
 *
 */
public interface Reader {

	public void getAllCws(CwBrowser crosswords) throws IOException, WordNotFoundException;
}
