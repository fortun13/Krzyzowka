/**
 * 
 */
package crossword;

import dictionary.CwEntry;

/**
 * @author Jakub Fortunka
 *
 */
public abstract class Strategy {

	/**
	 * 
	 */
	public Strategy() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param cw Krzyzowka w ktorej ma zostac wygenerowana zawartosc
	 * @return Wpis z bazy hasel ktory nadaje sie do krzyzowki
	 * @throws WordNotFoundException
	 */
	public abstract CwEntry findEntry(Crossword cw) throws WordNotFoundException;
	/**
	 * @param b plansza krzyzowki
	 * @param e wpis z bazy hasel ktory nadaje sie do krzyzowki
	 */
	public abstract void updateBoard(Board b, CwEntry e);

}
