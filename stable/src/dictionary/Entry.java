/**
 * 
 */
package dictionary;

import java.io.Serializable;


/**
 * @author Jakub Fortunka
 *
 */
public class Entry implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7099379369719944830L;
	
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
