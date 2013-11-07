/**
 * 
 */
package crossword;

import java.util.LinkedList;


/**
 * Klasa zawiera w sobie tablice komorek, w ktorych znajduja sie poszczegolne litery hasel z krzyzowki.
 * 
 * @author Jakub Fortunka
 *
 */
public class Board {

	int width=20,height=20;
	private BoardCell[][] board = new BoardCell[height][width];
	/**
	 * 
	 */
	public Board() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return zwraca szerokosc krzyzowki
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @return zwraca wysokosc krzyzowki
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @param x parametr x pobieranej komorki
	 * @param y parametr y pobieranej komorki
	 * @return komorke o polozeniu (x,y)
	 */
	public BoardCell getCell(int x,int y) {
		return board[x][y];
	}
	
	/**
	 * @param x parametr okreslajacy parametr komorki ktora ma byc wpisana
	 * @param y parametr okreslajacy parametr komorki ktora ma byc wpisana
	 * @param c komorka, ktora ma byc wpisana do krzyzowki
	 */
	public void setCell(int x,int y,BoardCell c) {
		board[y][x]=c;
	}
	
	/**
	 * @return zwraca LinkedList wszystkich komorek, ktore moga rozpoczac wyraz
	 */
	public LinkedList<BoardCell> getStartCells() {
		LinkedList<BoardCell> zwrot = new LinkedList<BoardCell>();
		for (int i=0;i<height;i++) {
			for (int j=0;j<width;j++)
				if (board[i][j].canVertiStart || board[i][j].canHorizStart) zwrot.add(board[i][j]);			
		}
		return zwrot;
	}
	
	/**
	 * @param fromx od ktorego x ma zostac rozpoczete badanie wyrazu
	 * @param fromy od ktorego y ma zostac rozpoczete badanie wyrazu
	 * @param tox do ktorej wspolrzednej x ma zostac wykorzystany obszar na podstawie ktorego powstanie regex
	 * @param toy do ktorej wspolrzednej y ma zostac wykorzystany obszar z ktorego powstanie regex
	 * @return
	 */
	public String createPattern(int fromx, int fromy, int tox, int toy) {
		String pattern="";
		pattern+="^";
		if (fromx==tox) {
			for (int i=fromy;i<toy;i++) {
				if (board[i][fromx]==null) pattern+=".";
				else {
					if (!board[i][fromx].content.equals("!")) pattern+=board[i][fromx].content;
					else pattern+=".";
				}
			}
		}
		else {
			for (int i=fromx;i<tox;i++) {
				if (board[fromy][i] == null) pattern+=".";
				else {
					if (!board[fromy][i].content.equals("!")) pattern+=board[fromy][i].content;
					else pattern+=".";
				}
			}
		}
		pattern+="$";
		return pattern;
		/* Testowe 
		pattern+=board[fromy][fromx].getContent();
		pattern+="[a-z]*";
		pattern+="$";
		return pattern;*/
	}
	
	public void printBoard() {
		for (int i=0;i<height;i++) {
			for (int j=0;j<width;j++) {
				if (board[i][j]!= null) System.out.print(board[i][j].getContent());
				else System.out.print(" ");
			}
			System.out.println("");
		}
	}
}
