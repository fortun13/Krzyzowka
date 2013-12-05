/**
 * 
 */
package interfaces;
import java.io.FileNotFoundException;
import java.io.IOException;

import crossword.*;

/**
 * Interfejs do tworzenia klas zapisujacych krzyzowke
 * 
 * @author Jakub Fortunka
 * @version 1.0
 */
public interface Writer  {
	
	/**
	 * metoda (do nadpisania) zapisujaca krzyzowke do pliku
	 * 
	 * @param cw krzyzowka do zapisania
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void write(Crossword cw) throws FileNotFoundException, IOException;
	/**
	 * @return date zapisywania krzyzowki w milisekundach, ktora stanowi rowniez ID krzyzowki
	 */
	public long getUniqueID();
	
}
