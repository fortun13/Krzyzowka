/**
 * 
 */
package dictionary;

import crossword.WordNotFoundException;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;

/**
 * Klasa dziedziczaca po CwDB. Rozszerza funkcjonalnosc o losowani wyrazow oraz dodawanie wyrazow, by lista
 * zachowala kolejnosc alfabetyczna
 * 
 * @author Jakub Fortunka
 * @version 1.0
 * @see CwDB
 *
 */
public class InteliCwDB extends CwDB implements Serializable {	
	/**
	 * Pole potrzebne do wykonywania serializacji.
	 * 
	 */
	private static final long serialVersionUID = -2705552648337775200L;
	
	/**
	 * Konstruktor korzysta z konstruktora klasy bazowej.
	 * 
	 * 
	 * @see CwDB
	 * @param filename sciezka do pliku z danymi do slownika
	 * @throws FileNotFoundException
	 */
	public InteliCwDB(String filename) throws FileNotFoundException {
		super(filename);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Metoda wyszukuje wszystkie wpisy w slowniku, ktore pasuja do podanego wyrazenia regularnego
	 * 
	 * @param pattern wyrazenie regularne wedlug ktorego maja byc znalezione wyrazy
	 * @return LinkedList<{@link Entry}> zawierajaca wszystkie wyrazy odpowiadajace podanemu wyrazeniu regularnemu
	 */
	public LinkedList<Entry> findAll(String pattern) {
		LinkedList<Entry> list = new LinkedList<Entry>();
		for (Entry e : dict) {
			if (e.getWord().matches(pattern)) list.add(e);
		}
		return list;
	}

	/**
	 * Metoda zwraca (pseudo)losowy wyraz ze slownika
	 * 
	 * @return losowy wyraz ze slownika
	 */
	public Entry getRandom()	{
		int wielkosc = this.dict.size();
		Random losowa = new Random();
		return dict.get(losowa.nextInt(wielkosc));
	}
	
	/**
	 * Metoda wyszukuje i zwraca losowy wyraz (jednak o okreslonej dlugosci) ze slownika
	 * 
	 * @param length dlugosc wyrazu, ktory ma zostac wylosowany
	 * @return losowy wyraz o okreslonej dlugosci
	 * @throws WordNotFoundException
	 */
	public Entry getRandom(int length) throws WordNotFoundException	{
		String pat = "^";
		for (int i=0;i<length;i++) pat+=".";
		pat+="$";
		LinkedList<Entry> lista = findAll(pat);
		if (lista.isEmpty()) throw new WordNotFoundException("Nie znaleziono slowa");
		Random rnd = new Random();
		return lista.get(rnd.nextInt(lista.size()));
	}
	
	/**
	 * Metoda wyszukuje w slowniku i zwraca losowy wyraz, ktory pasuje do podanego wyrazenia regularnego
	 * 
	 * @param pattern wyrazenie regularne dla ktorego chcemy znalezc wyraz
	 * @return wyraz odpowiadajacy podanego wyrazeniu regularnemu, jezeli nie znajdzie, zwraca null
	 * @throws WordNotFoundException
	 */
	public Entry getRandom(String pattern) throws WordNotFoundException {
		LinkedList<Entry> lista = findAll(pattern);
		if (lista.isEmpty()) return null;
		Random rnd = new Random();
		return lista.get(rnd.nextInt(lista.size()));
	}
	/**
	 * Metoda dodaje slowo do slownika w odpowiednim miejscu (slownik bedzie poukladany alfabetycznie)
	 * 
	 * @param word slowo, ktore ma zostac wprowadzone do slownika
	 * @param clue podpowiedz dla podanego slowa
	 * 
	 */
	public void add(String word,String clue) {
		dict.add(new Entry(word,clue));
		Collections.sort(dict,new Comparator<Entry>() {
			private Collator c = Collator.getInstance(new Locale("pl","PL"));
			 
            @Override
            public int compare(Entry e1, Entry e2) {
                return c.compare(e1.getWord(),e2.getWord());
            }
		});
	}
}
