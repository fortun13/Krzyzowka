/**
 * 
 */
package dictionary;

import crossword.WordNotFoundException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Scanner;


/**
 * Klasa obs³ugujaca "prymitywna" (bazowa) baze danych. Slownik realizowany jest poprzez LinkedList<Entry>.
 * Moze wyrzucac wyjatek FileNotFoundException.
 * 
 * @author Jakub Fortunka
 * @version 1.0
 *
 */
public class CwDB implements Serializable {

	/**
	 * pole niezbedne do serializacji obiektu klasy
	 */
	private static final long serialVersionUID = -1855598157340500387L;
	
	/**
	 * Lista {@link Entry} w ktorej przechowywane sa wszystkie hasla z ktorych bedziemy korzystac do tworzenia krzyzowek
	 */
	protected LinkedList<Entry> dict = new LinkedList<Entry>();
	
	/**
	 * Metoda do stworzenia bazy korzysta z metody createDB.
	 * 
	 * @param filename sciezka do pliku z danymi do slownika
	 * @throws FileNotFoundException
	 */
	public CwDB(String filename) throws FileNotFoundException {
		this.createDB(filename);
	}

	/**
	 * dodaje slowo do slownika
	 * 
	 * @param word slowo ktore ma byc wprowadzone do slownika
	 * @param clue podpowiedz do wprowadzanego slowa
	 */
	public void add(String word,String clue)	{
		dict.add(new Entry(word,clue));
	}
	
	/**
	 * Metoda wyszukuje podane slowo w slowniku. Jezeli tego slowa nie ma w slowniku, rzucany jest wyjatek WordNotFoundException.
	 * 
	 * @param word slowo, ktore jest szukane w bazie
	 * @return metoda zwraca obiekt Entry, ktory zawiera w sobie podane slowo
	 * @throws WordNotFoundException
	 */
	public Entry get(String word) throws WordNotFoundException {
		for (Entry el : dict) 	if (el.getWord().equals(word)) return el;
		throw new WordNotFoundException("Nie znaleziono slowa");
	}
	
	/**
	 * Metoda, korzystajac z metody get, wyszukuje slowo w slowniku i je usuwa.
	 * Jezeli slowo nie zostaje znalezione, metoda rzuca wyjatek WordNotFoundException
	 * 
	 * @param word slowo do usuniecia z bazy
	 * @throws WordNotFoundException
	 */
	public void remove(String word) throws WordNotFoundException {
		Entry nowy = this.get(word);
		dict.remove(dict.indexOf(nowy));
	}
	
	/**
	 * Metoda pozwalajaca zapisac obecnny slownik do pliku
	 * 
	 * @param filename sciezka do pliku w ktorym ma zostac zapisany slownik
	 * @throws IOException
	 */
	public void saveDB(String filename) throws IOException {
		File plik = new File(filename);
		if (!plik.exists()) plik.createNewFile();
		FileWriter zapis = new FileWriter(plik.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(zapis);
		for (Entry e : dict) {
			bw.write(e.getWord() + "\n");
			bw.write(e.getClue() + "\n");
		}
		bw.close();
	}
	
	/**
	 * zwraca wielkosc slownika
	 * 
	 * @return zwraca obecna wielkosc slownika
	 */
	public int getSize()	{
		return dict.size();
	}
	
	/**
	 * tworzy baze hasel z podanego pliku
	 * 
	 * @param filename sciezka do pliku zawierajacego dane dla slownika
	 * @throws FileNotFoundException
	 */
	protected void createDB(String filename) throws FileNotFoundException {
		try {
			Scanner plik;
			plik = new Scanner(new FileInputStream(filename));
			String word,clue;
			while (plik.hasNext()) {
				word = plik.nextLine();
				clue = plik.nextLine();
				add(word, clue);
			}
			plik.close();
		} catch (FileNotFoundException e) {
			throw e;
		}
		
	}
}
