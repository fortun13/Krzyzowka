/**
 * 
 */
package dictionary;

/**
 * @author Jakub Fortunka
 *
 */
public class CwEntry extends Entry {

	private int x,y;
	private Direction d;
	
	/**
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
	 * @return zwraca wartosc x (polozenie na osi OX)
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return zwraca wartosc y (polozenie na osi OY)
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return zwraca rodzaj polozenia (czy haslo ma byc ulozone poziomo czy pionowo) (HORIZ albo VERT)
	 */
	public Direction getD() {
		return d;
	}
	

}
