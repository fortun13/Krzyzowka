/**
 * 
 */
package interfaces;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import crossword.*;

/**
 * Klasa implementujaca interfejs Reader.
 * Pozwala odczytywac krzyzowki znajdujace sie w danym folderze. Odczytuje pliki zapisane z pomoc¹ serializacji,
 * która wykonywana jest poprzez klasê ImplementedWriter
 * 
 * @see Reader
 * 
 * 
 * @author Jakub Fortunka
 * @version 1.0
 *
 *
 */
public class ImplementedReader implements Reader {

	private String sciezka;
	
	/**
	 * Konstruktor. Przyjmuje sciezke do pliku badz folderu.
	 * 
	 * @param sciezka sciezka do pliku lub folderu
	 */
	public ImplementedReader(String sciezka) {
		this.sciezka=sciezka;
	}

	/**
	 * Metoda implementujaca metode interfejsu. Wczytuje wszystkie pliki z podanej do konstruktora sciezki folderu.
	 * 
	 * {@inheritDoc}
	 * 
	 * @return lista obiektow klasy Crossword z folderu o sciezce podanej do konstruktora
	 */
	@Override
	public LinkedList<Crossword> getAllCws() throws IOException, ClassNotFoundException {
		LinkedList<Crossword> list = new LinkedList<Crossword>();
			File folder = new File(sciezka);
			File[] pliki = folder.listFiles();
			for (File plik : pliki) {
				if (plik.isFile()) {
					FileInputStream fileIn = new FileInputStream(plik);
					list.add(readSerializedFile(fileIn));
					fileIn.close();
				}
			}    
		return list;
	}
	
	/**
	 * Metoda uzywana do odczytania pojedynczego pliku z krzyzowka
	 * 
	 * @return obiekt klasy Crossword z pliku o nazwie podanej do konstruktora
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Crossword getCrossword() throws ClassNotFoundException, IOException {
		Crossword cw = null;
		FileInputStream fileIn = new FileInputStream(sciezka);
	    cw = readSerializedFile(fileIn);
	    fileIn.close();
	    return cw;
	}
	
	/**
	 * Metoda zajmujaca sie obsluga wczytywania serializowanego obiektu z pliku
	 * 
	 * @param f Strumien pliku z ktorego odczytywana bedzie klasa Crossword
	 * @return wczytany z pliku obiekt klasy Crossword
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private Crossword readSerializedFile(FileInputStream f) throws IOException, ClassNotFoundException {
	       ObjectInputStream in = new ObjectInputStream(f);
	       Crossword cw = (Crossword) in.readObject();
	       in.close();
	       return cw;
	}

}
