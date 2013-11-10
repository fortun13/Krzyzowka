/**
 * 
 */
package dictionary;

import crossword.WordNotFoundException;

import java.io.FileNotFoundException;
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
 *
 */
public class InteliCwDB extends CwDB {

	/**
	 * Konstruktor korzysta z konstruktora klasy bazowej.
	 * 
	 * @param filename sciezka do pliku z danymi do slownika
	 * @throws FileNotFoundException
	 */
	public InteliCwDB(String filename) throws FileNotFoundException {
		super(filename);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param pattern wyrazenie regularne wedlug ktorego maja byc znalezione wyrazy
	 * @return zwraca LinkedList<Entry> zawierajaca wszystkie wyrazy odpowiadajace podanemu wyrazeniu regularnemu
	 */
	public LinkedList<Entry> findAll(String pattern) {
		LinkedList<Entry> list = new LinkedList<Entry>();
		for (Entry e : dict) {
			if (e.getWord().matches(pattern)) list.add(e);
		}
		return list;
	}

	/**
	 * @return zwraca losowy wyraz ze slownika
	 */
	public Entry getRandom()	{
		int wielkosc = this.dict.size();
		Random losowa = new Random();
		return dict.get(losowa.nextInt(wielkosc));
	}
	
	/**
	 * @param length dlugosc wyrazu, ktory ma zostac wylosowany
	 * @return zwraca losowy wyraz o okreslonej dlugosci
	 * @throws WordNotFoundException
	 */
	public Entry getRandom(int length) throws WordNotFoundException	{
		/*int size = this.getSize();
		int counter = 0;
		Random rnd = new Random();
		int losowa = rnd.nextInt(dict.size());
		while(counter<size){
			if (dict.get(losowa).getWord().length()==length) return dict.get(losowa);
			else losowa = rnd.nextInt(dict.size());
			counter++;
		}
		throw new WordNotFoundException()*/
		String pat = "^";
		for (int i=0;i<length;i++) pat+=".";
		pat+="$";
		LinkedList<Entry> lista = findAll(pat);
		if (lista.isEmpty()) throw new WordNotFoundException();
		Random rnd = new Random();
		return lista.get(rnd.nextInt(lista.size()));
	}
	
	/**
	 * @param pattern wyrazenie regularne dla ktorego chcemy znalezc wyraz
	 * @return zwraca wyraz odpowiadajacy podanego wyrazeniu regularnemu
	 * @throws WordNotFoundException
	 */
	public Entry getRandom(String pattern) throws WordNotFoundException {
		/*int size = this.getSize();
		int counter = 0;
		Entry e = this.getRandom();
		while(counter<size) {
			if (e.getWord().matches(pattern)) return e;
			e = this.getRandom();
			counter++;
		}
		throw new WordNotFoundException();*/
		LinkedList<Entry> lista = findAll(pattern);
		if (lista.isEmpty()) throw new WordNotFoundException();
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