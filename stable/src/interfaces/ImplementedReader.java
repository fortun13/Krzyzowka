/**
 * 
 */
package interfaces;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

import crossword.Board;
import crossword.BoardCell;
import crossword.Crossword;
import dictionary.InteliCwDB;

/**
 * @author Jakub Fortunka
 *
 */
public class ImplementedReader implements Reader {

	/**
	 * 
	 */
	String sciezka;
	public ImplementedReader(String sciezka) {
		this.sciezka=sciezka;
	}
	/* (non-Javadoc)
	 * @see interfaces.Reader#getAllCws()
	 */
	@Override
	public void getAllCws() throws IOException {
		// TODO Auto-generated method stub
		File folder = new File(sciezka);
		File[] pliki = folder.listFiles();
		for (File plik : pliki) {
		    if (plik.isFile()) {
		      //  System.out.println(file.getName());
		    	InteliCwDB db = new InteliCwDB("cwdb.txt");
		    	Crossword cw = new Crossword(Long.parseLong(plik.getName()));
		    	cw.setCwDB(db);
		    	Scanner p = new Scanner(new FileInputStream(plik));
		    	LineNumberReader lnr = new LineNumberReader(new FileReader(plik));
		    	lnr.skip(Long.MAX_VALUE);
		    	String linia = p.nextLine();
		    	Board b = new Board(linia.length(),lnr.getLineNumber());
		    	for (int i=0;i<b.getHeight();i++) {
		    		for (int j=0;j<b.getWidth();j++) {
		    			BoardCell cell = new BoardCell();
		    			cell.setContent(linia.substring(j, j+1));
		    			b.setCell(j, i, cell);
		    		}
		    		if (i!=b.getHeight()-1) linia = p.nextLine();
		    	}
		    	
		    	lnr.close();
		    	p.close();
		    }
		}
	}

}
