package interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import crossword.Crossword;

/**
 * Klasa implementujaca interfejs {@link Writer}.
 * Zapisuje krzyzowke do folderu "savedCw". Jako nazwa pliku podawane jest uzyskane unikalne ID.
 * 
 * @author Jakub Fortunka
 * @version 1.0
 *
 */
public class ImplementedWriter implements Writer {

	/** 
	 * Zapisuje krzyzowke w folderze "savedCw"
	 * 
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void write(Crossword cw) throws FileNotFoundException,IOException {		
		try
	      {
			File dir = new File("savedCw");
			if (!dir.exists()) dir.mkdir();
	         FileOutputStream fileOut = new FileOutputStream(String.valueOf("savedCw\\" + this.getUniqueID()) + ".txt");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(cw);
	         out.close();
	         fileOut.close();
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	/** 
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public long getUniqueID() {
		return new Date().getTime();
	}

}
