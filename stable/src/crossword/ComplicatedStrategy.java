/**
 * 
 */
package crossword;

import dictionary.CwEntry;
import exception.WordNotFoundException;

/**
 * Nie zaimplementowana klasa generujaca skomplikowana wersje krzyzowki (krzyzujaca sie)
 * 
 * @author Jakub Fortunka
 * @version 0.1
 */
public class ComplicatedStrategy extends Strategy {

	/* (non-Javadoc)
	 * @see crossword.Strategy#findEntry(crossword.Crossword)
	 */
	@Override
	public CwEntry findEntry(Crossword cw) throws WordNotFoundException {
		// TODO all method
		return null;
	}

	/* (non-Javadoc)
	 * @see crossword.Strategy#updateBoard(crossword.Board, dictionary.CwEntry)
	 */
	@Override
	public void updateBoard(Board b, CwEntry e) {
		// TODO
	}

}
