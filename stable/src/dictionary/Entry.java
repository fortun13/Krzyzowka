/**
 * 
 */
package dictionary;

import java.io.Serializable;


/**
 * Klasa reprezentujaca wpis z bazy hasel. Posiada pola dla hasla i podpowiedzi do tego hasla
 *  
 * @author Jakub Fortunka
 * @version 1.0
 * 
 *
 */
public class Entry implements Serializable{

	/**
	 * pole potrzebne do serializacji obiektu klasy
	 *  
	 */
	private static final long serialVersionUID = 7099379369719944830L;
	
	/**
	 * haslo
	 */
	private String word;
	
	/**
	 * podpowiedz do hasla
	 */
	private String clue;
	
	/**
	 * Kontruktor parametryczny
	 * 
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
