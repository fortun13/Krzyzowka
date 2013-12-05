/**
 * 
 */
package interfaces;

import java.io.IOException;
import java.util.LinkedList;

import crossword.Crossword;

/**
 * Interfejs obslugujacy wczytywanie krzyzowek
 * 
 * @author Jakub Fortunka
 *
 */
public interface Reader {
	/**
	 * Metoda ma zwracac wszystkie krzyzowki z danej sciezki
	 * 
	 * @return liste {@link Crossword} zawierajaca wszystkie krzyzowki z danego folderu
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public LinkedList<Crossword> getAllCws() throws IOException, ClassNotFoundException;
}
