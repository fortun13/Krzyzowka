/**
 * 
 */
package dictionary;


/**
 * @author Jakub Fortunka
 *
 */
public class Entry {

	private String word;
	private String clue;
	
	/**
	 * @param _word slowo do wprowadzenia
	 * @param _clue podpowiedz do wprowadzanego slowa
	 */
	public Entry(String _word,String _clue) {
		word=_word;
		clue=_clue;
	}

	/**
	 * @return zwraca slowo
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @return zwraca podpowiedz dla slowa
	 */
	public String getClue() {
		return clue;
	}
	
}
