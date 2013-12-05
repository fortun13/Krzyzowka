/**
 * 
 */
package interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import crossword.*;
import dictionary.InteliCwDB;

/**
 * @author Jakub Fortunka
 *
 */
public class CwBrowser {

	private LinkedList<Crossword> crosswords = new LinkedList<Crossword>();
	
	public void saveCrossword(Crossword cw) throws FileNotFoundException, IOException {
		ImplementedWriter w = new ImplementedWriter();
		w.write(cw);
	}
	
	public void readCrossword(String file) throws IOException, WordNotFoundException {
		ImplementedReader r = new ImplementedReader(file);
		addCrossword(r.getCrossword());
	}
	
	public void readAllCrosswords(String file) throws IOException, WordNotFoundException {
		ImplementedReader r = new ImplementedReader(file);
		addAllCrosswords(r.getAllCws());
	}
	
	public void addCrossword(Crossword cw) {
		crosswords.add(cw);
	}
	
	public void addAllCrosswords(LinkedList<Crossword> l) {
		this.crosswords=l;
	}
	
	public Crossword generateCrossword(int height, int width,Strategy s, InteliCwDB cwDB) throws FileNotFoundException, WordNotFoundException {
		if (cwDB==null) {
			cwDB = new InteliCwDB("cwdb.txt");
		}
    	Crossword cw = new Crossword();
    	cw.setCwDB(cwDB);
    	Board b = new Board(width,height);
    	cw.setBoard(b);
    	cw.generate(s);
    	return cw;
	}
	
	public Crossword getCrossword(int index) {
		return crosswords.get(index);
	}
	
	public int getNumberOfCrosswords() {
		return crosswords.size();
	}
	
	public Crossword getLastCrossword() {
		return crosswords.getLast();
	}
	
}
