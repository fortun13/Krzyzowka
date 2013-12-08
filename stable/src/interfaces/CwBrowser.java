/**
 * interfaces Pakiet z interfejsami zajmujacymi sie wczytywaniem i zapisywaniem krzyzowek
 */
package interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import crossword.*;
import dictionary.InteliCwDB;
import exception.NotGeneratedAnyCrosswordException;
import exception.TooBigCrosswordException;
import exception.WordNotFoundException;

/**
 * Klasa zajmujaca sie organizacja i zarzadzaniem wszystkimi krzyzowkami (wygenerowanymi i wczytanymi z plikow)
 * 
 * @author Jakub Fortunka
 * @version 1.0
 *
 */
public class CwBrowser {

	/**
	 * lista {@link Crossword} w ktorej sa wszystkie wykorzystywane krzyzowki
	 */
	private LinkedList<Crossword> crosswords = new LinkedList<Crossword>();
	
	/**
	 * Metoda zapisujaca podana krzyzowke do pliku
	 * 
	 * @param cw krzyzowka do zapisania
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void saveCrossword(Crossword cw) throws FileNotFoundException, IOException {
		ImplementedWriter w = new ImplementedWriter();
		w.write(cw);
	}
	
	/**
	 * Metoda wczytujaca krzyzowke z pliku
	 * 
	 * @param file plik do wczytania
	 * @throws IOException
	 * @throws WordNotFoundException
	 * @throws ClassNotFoundException
	 */
	public void readCrossword(String file) throws IOException, WordNotFoundException, ClassNotFoundException {
		ImplementedReader r = new ImplementedReader(file);
		addCrossword(r.getCrossword());
	}
	
	/**
	 * Metoda wczytujaca wszystkie krzyzowki z folderu
	 * 
	 * @param file sciezka do folderu z krzyzowkami
	 * @throws IOException
	 * @throws WordNotFoundException
	 * @throws ClassNotFoundException
	 */
	public void readAllCrosswords(String file) throws IOException, WordNotFoundException, ClassNotFoundException {
		ImplementedReader r = new ImplementedReader(file);
		addAllCrosswords(r.getAllCws());
	}
	
	/**
	 * Dodaje krzyzowke do listy {@link interfaces.CwBrowser#crosswords}
	 * 
	 * @param cw krzyzowka do wpisania do listy
	 */
	public void addCrossword(Crossword cw) {
		crosswords.add(cw);
	}
	
	/**
	 * Metoda dodajaca do aktualnej listy {@link interfaces.CwBrowser#crosswords} wczytane krzyzowki
	 * 
	 * @param l lista krzyzowek do dodania do aktualnej listy
	 */
	public void addAllCrosswords(LinkedList<Crossword> l) {
		l.addAll(l);
	}
	
	/**
	 * Metoda do generowania nowej krzyzowki
	 * 
	 * @param height wysokosc krzyzowki
	 * @param width szerokosc krzyzowki
	 * @param s stategia wedlug ktorej ma zostac stworzona krzyzowka
	 * @param cwDB plik z haslami do generacji krzyzowki
	 * @return wygenerowana krzyzowka
	 * @throws FileNotFoundException
	 * @throws WordNotFoundException
	 * @throws TooBigCrosswordException 
	 */
	public Crossword generateCrossword(int height, int width,Strategy s, InteliCwDB cwDB) throws FileNotFoundException, WordNotFoundException, TooBigCrosswordException {
		if (cwDB==null) {
			cwDB = new InteliCwDB("res/cwdb.txt");
		}
    	Crossword cw = new Crossword();
    	cw.setCwDB(cwDB);
    	Board b = new Board(width,height);
    	cw.setBoard(b);
    	cw.generate(s);
    	return cw;
	}
	
	/**
	 * zwraca krzyzowke o podanym indeksie
	 * @param index indeks krzyzowki do zwrocenia
	 * @return krzyzowka o podanym indeksie
	 */
	public Crossword getCrossword(int index) throws NotGeneratedAnyCrosswordException {
		if (crosswords.isEmpty()) throw new NotGeneratedAnyCrosswordException("Nie wygenerowa³eœ ¿adnej krzy¿ówki!");
		return crosswords.get(index);
	}
	
	/**
	 * zwraca ilosc krzyzowek w {@link interfaces.CwBrowser#crosswords}
	 * @return ilosc krzyzowek
	 */
	public int getNumberOfCrosswords() {
		return crosswords.size();
	}
	
	/**
	 * zwraca ostatnia krzyzowke w {@link interfaces.CwBrowser#crosswords}
	 * @return ostatnia krzyzowka
	 */
	public Crossword getLastCrossword() {
		return crosswords.getLast();
	}
	
}
