/**
 * 
 */
package crossword;

/**
 * @author Jakub Fortunka
 *
 */
public class WordNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2354623261297457403L;
	
	/**
	 * 
	 */
	public WordNotFoundException() {
		// TODO Auto-generated constructor stub
		System.out.println("Nie znaleziono wyrazu w bazie");
	}

	/**
	 * @param arg0
	 */
	public WordNotFoundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public WordNotFoundException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public WordNotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public WordNotFoundException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

}
