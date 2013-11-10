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

import crossword.*;
import dictionary.*;

/**
 * @author Jakub Fortunka
 *
 *Struktura pliku z ktorego nalezy czytac:
 *pierwsza linia: wysokosc szerokosc rodzajAlgorytmu(false - simple; true - complicated)
 *np. 30 30 false
 *kolejne linie - wpis pozycjaPrzestrzenna x y
 *np. kabina HORIZ 10 10
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
	public void getAllCws() throws IOException, WordNotFoundException {
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
		    	String[] linia = p.nextLine().split(" ");
		    	int height = Integer.parseInt(linia[0]);
		    	int width = Integer.parseInt(linia[1]);
		    	Board b = new Board();
		    	Strategy s;
		    	if (linia[2].equals("false")) s = new SimpleStrategy();
		    	else s = new ComplicatedStrategy();
		    	while (p.hasNext()) {
		    		String[] liniaWyraz = p.nextLine().split(" ");
		    		Entry e = db.get(liniaWyraz[0]);
		    		boolean horizontal=false;
		    		if (liniaWyraz[1].equals(Direction.HORIZ)) horizontal=true;
		    		int x = Integer.parseInt(liniaWyraz[2]);
		    		int y = Integer.parseInt(liniaWyraz[3]);
		    		CwEntry cwe;
		    		if (horizontal) cwe = new CwEntry(e.getWord(), e.getClue(), Direction.HORIZ, x, y);
		    		else cwe = new CwEntry(e.getWord(), e.getClue(), Direction.VERT, x, y);
		    		cw.addCwEntry(cwe, s);
		    	}
		    	p.close();
		    }
		}
	}

}
