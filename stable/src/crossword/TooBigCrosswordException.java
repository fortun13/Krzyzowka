/**
 * 
 */
package crossword;

/**
 * @author Jakub Fortunka
 *
 */
public class TooBigCrosswordException extends Exception {

	/**
	 * pole potrzebne do serializacji
	 */
	private static final long serialVersionUID = 2693470580291033523L;

	/**
	 * 
	 */
	public TooBigCrosswordException() {
		super();
	}

	/**
	 * @param arg0 wiadomosc do przechowania w wyjatku
	 */
	public TooBigCrosswordException(String msg) {
		super(msg);
	}

}
