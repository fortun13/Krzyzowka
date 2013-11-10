/**
 * 
 */
package interfaces;
import java.io.FileNotFoundException;
import java.io.IOException;

import crossword.*;

/**
 * @author Jakub Fortunka
 *
 */
public interface Writer  {
	
	public void write(Crossword cw) throws FileNotFoundException, IOException;
	long getUniqueID();
	
}
