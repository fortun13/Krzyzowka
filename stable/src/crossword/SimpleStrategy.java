/**
 * 
 */
package crossword;

import java.util.Iterator;
import java.util.Random;

import dictionary.Entry;
import dictionary.CwEntry;
import dictionary.Direction;

/**
 * Implementacja interfejsu strategii - jest to strategia prosta, wyrazy sie nie przecinaja
 * 
 * @author Jakub Fortunka
 * @version 1.0
 * @see Strategy
 */
public class SimpleStrategy extends Strategy {
	
	/**
	 * Implementacja metody do znajdowania hasel mozliwych do wpisania w krzyzowke.
	 * Metoda jest prosta - hasla sie nie przecinaja
	 * 
	 * {@inheritDoc}
	 * @throws TooBigCrosswordException 
	 * 
	 */
	
	@Override
	public CwEntry findEntry(Crossword cw) throws WordNotFoundException, TooBigCrosswordException {
		Iterator<CwEntry> it = cw.getROEntryIter();
		if (!it.hasNext()) {
			Entry n = cw.getCwDB().getRandom(cw.getBoardCopy().getHeight());
			CwEntry haslo = new CwEntry(n.getWord(),n.getClue(),Direction.VERT,0,0);
			return haslo;
		}
		else {
			CwEntry first = it.next();
			int i=0;
			while (it.hasNext()) {
				i++;
				it.next();
			}
			int licznik=0;
			if (i==first.getWord().length()) return null;
			Random rnd = new Random();
			int los = rnd.nextInt(cw.getBoardCopy().getWidth()-3)+2;
			String pat = cw.getBoardCopy().createPattern(first.getX(), first.getY()+i, los, first.getY()+i);
			Entry e = cw.getCwDB().getRandom(pat);
			while (e==null) {
				/*los = rnd.nextInt(10)+2;
				pat = cw.getBoardCopy().createPattern(first.getX(), first.getY()+i, los, first.getY()+i);
				e = cw.getCwDB().getRandom(pat);
				licznik++;
				if (licznik>300) throw new WordNotFoundException("Nie znaleziono wyrazu");
				if (e!=null && cw.contains(e.getWord())) e=null;*/
				try {
					los = rnd.nextInt(10)+2;
					pat = cw.getBoardCopy().createPattern(first.getX(), first.getY()+i, los, first.getY()+i);
					e = cw.getCwDB().getRandom(pat);
					if (e!=null && cw.contains(e.getWord())) e=null;
				}
				catch (WordNotFoundException exp) {
					e=null;
				}
				licznik++;
				if (licznik>300) throw new WordNotFoundException("Nie znaleziono wyrazu");
			}
			CwEntry haslo = new CwEntry(e.getWord(),e.getClue(),Direction.HORIZ,0,i);
			return haslo;
		}
	}

	/**
	 * Implementacja metody do aktualizowania zawartosci tablicy
	 * 
	 * {@inheritDoc}
	 * 
	 */
	@Override
	public void updateBoard(Board b, CwEntry e) {
		int xEntry=e.getX();
		int yEntry=e.getY();
		Direction d=e.getD();
		if (d.equals(Direction.VERT)) {
			for (int i=yEntry;i<e.getWord().length();i++) {
				BoardCell c = new BoardCell();
				c.setContent(e.getWord().substring(i-yEntry, i-yEntry+1));
				b.setCell(xEntry, i, c);
			}
		}
		else {
			for (int i=xEntry;i<e.getWord().length();i++) {
				BoardCell c = new BoardCell();
				c.setContent(e.getWord().substring(i-xEntry, i-xEntry+1));
				b.setCell(i, yEntry, c);
			}
		}
	}

}
