/**
 * 
 */
package crossword;

import dictionary.CwEntry;
import exception.TooBigCrosswordException;
import exception.WordNotFoundException;

/**
 * Klasa abstrakcyjna stanowiaca niejako interfejs do tworzenia roznych strategii.
 * Przy tworzeniu nowej strategii nie trzeba siê przejmowaæ zmianami w kodzie - wszystko powinno dzia³aæ bez zarzutu, o ile oczywiœcie
 * dobrze napisaliœmy nowy rodzaj strategii :)
 * 
 * @author Jakub Fortunka
 * @version 1.0
 * 
 */
public abstract class Strategy {
	
	/**
	 * Metoda do znajdowania wpisu, ktory mozna dodac do krzyzowki
	 * @param cw Krzyzowka w ktorej ma zostac wygenerowana zawartosc
	 * @return Wpis z bazy hasel ktory nadaje sie do krzyzowki
	 * @throws WordNotFoundException
	 */
	public abstract CwEntry findEntry(Crossword cw) throws WordNotFoundException, TooBigCrosswordException;
	/**
	 * metoda aktualizuje stan tablicy o nowy wpis krzyzowki
	 * @param b plansza krzyzowki
	 * @param e wpis z bazy hasel ktory nadaje sie do krzyzowki
	 */
	public abstract void updateBoard(Board b, CwEntry e);

}
