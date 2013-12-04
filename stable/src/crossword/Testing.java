/**
 * 
 */
package crossword;

import java.io.FileNotFoundException;

import dictionary.InteliCwDB;

/**
 * @author Jakub Fortunka
 *
 */
public class Testing {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			InteliCwDB db = new InteliCwDB("cwdb.txt");
			Crossword cw = new Crossword();
			cw.setCwDB(db);
			//Crossword cw = new Crossword("cwdb.txt",1);
			SimpleStrategy s = new SimpleStrategy();
			cw.generate(s);
			cw.getBoardCopy().printBoard();
		} catch (WordNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
