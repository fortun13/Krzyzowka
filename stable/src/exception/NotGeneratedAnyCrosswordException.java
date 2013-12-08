/**
 * 
 */
package exception;

/**
 * Wyjatek pojawiajacy sie gdy uzytkownik chce zapisac/wydrukowac krzyzowke, jesli nie wczytal/wygenerowal
 * zadnej krzyzowki
 * 
 * @author Jakub Fortunka
 * @version 1.0
 */
public class NotGeneratedAnyCrosswordException extends Exception {

	/**
	 * potrzebne do serializacji
	 */
	private static final long serialVersionUID = -2473799945346472471L;

	/**
	 * Konstrktor domyslny
	 */
	public NotGeneratedAnyCrosswordException() {
		super();
	}

	/**
	 * @param msg wiadomosc do przeslania dalej
	 */
	public NotGeneratedAnyCrosswordException(String msg) {
		super(msg);
	}

}
