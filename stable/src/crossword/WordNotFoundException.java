/**
 * 
 */
package crossword;

/**
 * Wyjatek ktory obsluguje sytuacje w ktorej program nie jest w stanie znalezc odpowiedniego slowa w slowniku
 * 
 * @author Jakub Fortunka
 * @version 1.0
 *
 */
public class WordNotFoundException extends Exception {

	private static final long serialVersionUID = 2354623261297457403L;
	
	/**
	 * @param msg wiadomosc do przekazania dalej na temat przyczyny wyrzucenia wyjatku
	 */
	public WordNotFoundException(String msg) {
		super(msg);
	}
}
