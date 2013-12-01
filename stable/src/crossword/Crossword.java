/**
 * 
 */
package crossword;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import dictionary.CwEntry;
import dictionary.InteliCwDB;

/**
 * Klasa reprezentujaca krzyzowke, zapewniajaca metode do generowania krzyzowek
 * 
 * @author Jakub Fortunka
 *
 */
public class Crossword {

	private LinkedList<CwEntry> entries = new LinkedList<CwEntry>();
	private Board b;
	private InteliCwDB cwdb;
	private final long ID;
	/**
	 * 
	 */
	public Crossword() {
		// TODO Auto-generated constructor stub
		ID=-1;
		b = new Board();
	}
	
	/**
	 * @param id numer krzyzowki
	 */
	public Crossword(long id) {
		ID=id;
	}
	
	/**
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
	 * @return zwraca iterator po wpisach krzyzowki, ktory nie moze usuwac wpisow z krzyzowki
	 */
	public Iterator<CwEntry> getROEntryIter() {
		//Iterator<CwEntry> it = Collections.unmodifiableCollection(entries).iterator();
		//return it;
		//return Collections.unmodifiableCollection(entries).iterator();
		return Collections.unmodifiableList(entries).iterator();
	}
	
	/**
	 * @return metoda zwraca kopie "tablicy" (czyli calej krzyzowki)
	 */
	public Board getBoardCopy() {
		return b;
	}
	
	/**
	 * @return metoda zwraca baze hasel
	 */
	public InteliCwDB getCwDB() {
		return cwdb;
	}
	
	/**
	 * Metoda ustawia baze hasel dla krzyzowki
	 * 
	 * @param cwdb baza hasel do przypisania
	 */
	public void setCwDB(InteliCwDB cwdb) {
		this.cwdb=cwdb;
	}
	
	/**
	 * @param b plansza reprezentujaca krzyzowke
	 */
	public void setBoard (Board b) {
		this.b=b;
	}
	
	/**
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
	 */
	public final void generate(Strategy s) throws WordNotFoundException {
		  CwEntry e = null;
		  while((e = s.findEntry(this)) != null) {
		    addCwEntry(e,s);
		  }
	}
}
