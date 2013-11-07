/**
 * 
 */
package crossword;

import java.util.Iterator;
import java.util.Random;

import dictionary.CwEntry;
import dictionary.Direction;
import dictionary.Entry;

/**
 * @author Jakub Fortunka
 *
 */
public class ComplicatedStrategy extends Strategy {

	/* (non-Javadoc)
	 * @see crossword.Strategy#findEntry(crossword.Crossword)
	 */
	@Override
	public CwEntry findEntry(Crossword cw) throws WordNotFoundException {
		// TODO all method
		Iterator<CwEntry> it = cw.getROEntryIter();
		if (!it.hasNext()) {
			Random rnd = new Random();
			int r = rnd.nextInt(7)+2;
			Entry n = cw.getCwDB().getRandom(r);
			
			CwEntry haslo = new CwEntry(n.getWord(),n.getClue(),Direction.VERT,0,0);
			return haslo;
		}
		else {
			
		}
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
