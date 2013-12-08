/**
 * 
 */
package dictionary;

import java.io.Serializable;

/**
 * Klasa dziedziczaca po klasie Entry. Rozszerza jej funkcjonalnosc o informacje na temat polozenia na krzyzowce.
 * (posiada informacje o polozeniu (x,y), oraz o tym, w jaki sposob wyraz jest polozony (horyzontalnie czy wertykalnie))
 * 
 * @author Jakub Fortunka
 * @version 1.0
 * @see Entry
 * 
 */
public class CwEntry extends Entry implements Serializable {

	/**
	 * pole potrzebne do serializacji obiektu tej klasy
	 * 
	*/
	private static final long serialVersionUID = -6258824129283325212L;	
	/**
	 * wspolrzedna x/y pierwszego znaku wyrazu
	 */
	private int x,y;
	/**
	 * Polozenie wyrazu (HORIZ lub VERT) 
	 * @see Direction
	 */
	private Direction d;
	
	/**
	 * Kontruktor klasy
	 * 
	 * @param _word slowo do wprowadzenia
	 * @param _clue podpowiedz do slowa
	 */
	public CwEntry(String _word, String _clue,Direction d,int x,int y) {
		super(_word, _clue);
		// TODO Auto-generated constructor stub
		this.x=x;
		this.y=y;
		this.d=d;
	}

	/**
	 * Zwraca wsp. x wpisu
	 * @return wartosc x (polozenie na osi OX)
	 */
	public int getX() {
		return x;
	}

	/**
	 * Zwraca wsp. y wpisu
	 * @return wartosc y (polozenie na osi OY)
	 */
	public int getY() {
		return y;
	}

	/**
	 * Zwraca sposob polozenia wpisu
	 * @see Direction
	 * @return rodzaj polozenia (czy haslo ma byc ulozone poziomo czy pionowo) (HORIZ albo VERT)
	 */
	public Direction getD() {
		return d;
	}
	

}
