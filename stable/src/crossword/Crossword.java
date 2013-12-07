/**
 * 
 */
package crossword;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import dictionary.CwEntry;
import dictionary.InteliCwDB;

/**
 * Klasa reprezentujaca krzyzowke, zapewniajaca metode do generowania krzyzowek
 * 
 * @author Jakub Fortunka
 * @version 1.0
 */
public class Crossword implements Serializable {

	/**
	 * zmienna potrzebna do serializacji obiektu krzyzowki
	 */
	private static final long serialVersionUID = -5777932156704852796L;
	
	/**
	 * Lista {@link CwEntry} przechowujaca wszystkie wpisy znajdujace sie w tej krzyzowce
	 */
	private LinkedList<CwEntry> entries = new LinkedList<CwEntry>();
	/**
	 * plansza krzyzowki
	 * @see Board
	 */
	private Board b;
	/**
	 * baza hasel sluzacych do generowania krzyzowki
	 * @see InteliCwDB
	 */
	private InteliCwDB cwdb;
	/**
	 * numer krzyzowki (przy zapisywaniu jest nadawany, domyslnie ustawiony na -1)
	 */
	private final long ID;
	
	/**
	 * Kontruktor domyslny. Ustawia ID na -1; wywoluje konstruktor {@link Board}
	 */
	public Crossword() {
		ID=-1;
		b = new Board();
	}
	
	/**
	 * Kontruktor ustawiajacy id krzyzowki
	 * @param id numer krzyzowki
	 */
	public Crossword(long id) {
		ID=id;
	}
	
	/**
	 * Konstruktor ktory wczytuje slownik hasel z pliku
	 * 
	 * @param file z jakiego pliku ma zostac utworzona baza hasel
	 * @param id numer krzyzowki
	 * @throws FileNotFoundException
	 */
	public Crossword(String file,long id) throws FileNotFoundException {
		ID=id;
		b = new Board();
		cwdb = new InteliCwDB(file);
	}

	/**
	 * tworzy iterator RO (Read Only)
	 * 
	 * @return iterator po wpisach krzyzowki, ktory nie moze usuwac wpisow z krzyzowki
	 */
	public Iterator<CwEntry> getROEntryIter() {
		return Collections.unmodifiableList(entries).iterator();
	}
	
	/**
	 * Tworzy kopie tablicy i ja zwraca
	 * @see Board
	 * @return metoda zwraca kopie "tablicy" (czyli calej krzyzowki)
	 * @throws TooBigCrosswordException 
	 */
	public Board getBoardCopy() throws TooBigCrosswordException {
		//return b;
		Board board = new Board(b.getWidth(),b.getHeight());
		for (int i=0;i<board.getHeight();i++) {
			for (int j=0;j<board.getWidth();j++) {
				board.setCell(j, i, b.getCell(j, i));
			}
		}
		return board;
	}
	
	/**
	 * zwraca baze hasel
	 * @see InteliCwDB
	 * @return metoda zwraca baze hasel
	 */
	public InteliCwDB getCwDB() {
		return cwdb;
	}
	
	/**
	 * Metoda ustawia baze hasel dla krzyzowki
	 * 
	 * @param cwdb baza hasel do przypisania
	 * @throws FileNotFoundException rzucany gdy nie znaleziono pliku
	 */
	public void setCwDB(InteliCwDB cwdb) throws FileNotFoundException {
		InteliCwDB cwDB = null;
		if (cwdb==null) 	cwDB = new InteliCwDB("cwdb.txt");
		else cwDB = cwdb;
		this.cwdb=cwDB;
	}
	
	/**
	 * metoda ustawia plansze na ta zadana parametrem
	 * 
	 * @param b plansza reprezentujaca krzyzowke
	 */
	public void setBoard (Board b) {
		this.b=b;
	}
	
	/**
	 * Metoda sprawdza czy podane slowo zostalo juz uzyte w krzyzowce
	 * 
	 * @param word slowo do sprawdzenia
	 * @return true jesli slowo zawiera sie w krzyzowce (znajduje sie w entries); false jesli go tam nie ma
	 */
	public boolean contains(String word) {
		for (CwEntry e : entries) {
			if (e.getWord().equals(word)) return true;
		}
		return false;
	}
	
	/**
	 * Dodaje wpis do krzyzowki
	 * @param cwe Wpis z bazy hasel
	 * @param s wybrana strategia (SimpleStrategy badz ComplicatedStrategy)
	 */
	public final void addCwEntry(CwEntry cwe, Strategy s) {
		 entries.add(cwe);
		 s.updateBoard(b,cwe);
	}
	
	/**
	 * Metoda zajmuje sie generowaniem krzyzowki wedlug wybranej strategii
	 * 
	 * @param s wybrana strategia (SimpleStrategy badz ComplicatedStrategy)
	 * @throws WordNotFoundException
	 * @throws TooBigCrosswordException 
	 */
	public final void generate(Strategy s) throws WordNotFoundException, TooBigCrosswordException {
		  CwEntry e = null;
		  while((e = s.findEntry(this)) != null) {
		    addCwEntry(e,s);
		  }
	}
}
