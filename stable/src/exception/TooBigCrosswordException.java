/**
 * 
 */
package exception;

/**
 * Wyjatek obslugujacy sytuacje w ktorej uzytkownik podal zbyt duza wartosc wysokosci/szerokosci krzyzowki
 * @author Jakub Fortunka
 * @version 1.0
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
	 * @param msg wiadomosc do przechowania w wyjatku
	 */
	public TooBigCrosswordException(String msg) {
		super(msg);
	}

}
