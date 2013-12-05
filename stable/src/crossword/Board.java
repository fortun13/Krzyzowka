/**
 * 
 */
package crossword;

import java.io.Serializable;
import java.util.LinkedList;


/**
 * Klasa zawiera w sobie tablice komorek, w ktorych znajduja sie poszczegolne litery hasel z krzyzowki.
 * 
 * @author Jakub Fortunka
 * @version 1.0
 * 
 *
 */
public class Board implements Serializable {

	/**
	 * pole potrzebne do serializacji obiektu klasy
	 * @see Serializable
	 *
	 * 
	 * 
	 */
	private static final long serialVersionUID = -995417858715296474L;
	
	/**
	 * szerokosc krzyzowki
	 * wysokosc krzyzowki
	 * 
	 */
	private int width,height;
	/**
	 * tablica w ktorej znajduja sie obiekty klasy BoardCell
	 * @see BoardCell
	 * 
	 */
	private BoardCell[][] board;
	/**
	 * Konstruktor domyslny - generuje tablice o wymiarach 10x10
	 */
	public Board() {
		this.board = new BoardCell[10][10];
		this.width=10;
		this.height=10;
	}
	
	/**
	 * Konstruktor generujacy tablice o podanych wymiarach
	 * 
	 * @param width szerokosc tablicy
	 * @param height wysokosc tablicy
	 */
	public Board(int width,int height) {
		this.width=width;
		this.height=height;
		this.board = new BoardCell[this.height][this.width];
	}
	/**
	 * zwraca szerokosc planszy
	 * @return szerokosc krzyzowki
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * zwraca wysokosc planszy
	 * 
	 * @return wysokosc krzyzowki
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Zwraca komorke o polozeniu (x,y)
	 * 
	 * @param x parametr x pobieranej komorki
	 * @param y parametr y pobieranej komorki
	 * @return komorka o polozeniu (x,y)
	 */
	public BoardCell getCell(int x,int y) {
		return board[y][x];
	}
	
	/**
	 * metoda ustawia komorke {@link BoardCell} w podanym polozeniu
	 * 
	 * @param x parametr okreslajacy parametr komorki ktora ma byc wpisana
	 * @param y parametr okreslajacy parametr komorki ktora ma byc wpisana
	 * @param c komorka, ktora ma byc wpisana do krzyzowki
	 */
	
	public void setCell(int x,int y,BoardCell c) {
		board[y][x]=c;
	}
	
	/**
	 * metoda zwraca komorki, od ktorych mozna zaczac krzyzowke.
	 * (przydatne, jesli ma sie opracowana trudniejsza strategie)
	 * 
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
	 * Metoda tworzy wyrazenie regularne dopasowane do aktualnego stanu krzyzowki
	 * 
	 * @param fromx od ktorego x ma zostac rozpoczete badanie wyrazu
	 * @param fromy od ktorego y ma zostac rozpoczete badanie wyrazu
	 * @param tox do ktorej wspolrzednej x ma zostac wykorzystany obszar na podstawie ktorego powstanie regex
	 * @param toy do ktorej wspolrzednej y ma zostac wykorzystany obszar z ktorego powstanie regex
	 * @return stworzone wyrazenie regularne pasujace do krzyzowki
	 */
	public String createPattern(int fromx, int fromy, int tox, int toy) {
		String pattern="";
		pattern+="^";
		/*if (fromx==tox) {
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
		}*/
		int ilosc = tox-fromx;
		String first = board[fromy][0].content;
		first = first.toLowerCase();
		pattern+=first + "[A-Za-z]{2,";
		pattern+=String.valueOf(ilosc) + "}";
		pattern+="$";
		return pattern;
	}
}
