/**
 * 
 */
package crossword;

import java.io.Serializable;

/**
 * Klasa ktora reprezentuje jedna komorke krzyzowki
 * 
 * @author Jakub Fortunka
 * @version 1.0
 *
 */

public class BoardCell implements Serializable{

	/**
	 * 
	 * 
	 * serialVersionUID - pole potrzebne do serializacji obiektu 
	 * @see Serializable
	 */
	
	private static final long serialVersionUID = -1205507291299789208L;
	
	/**
	 * zawartosc jednej komorki
	 */
	String content="";
	/**
	 * czy komorka moze rozpoczynac nowe haslo w poziomie
	 */
	/**
	 * czy komorka moze byc czescia nowego hasla w poziomie
	 */
	/**
	 * czy komorka moze konczyc nowe haslo w poziomie
	 */
	boolean canHorizStart,canHorizIn,canHorizEnd;
	/**
	 * czy komorka moze rozpoczynac nowe haslo w pionie
	 */
	/**
	 * czy komorka moze byc czescia nowego haslo w pionie
	 */
	/**
	 * czy komorka moze konczyc nowe haslo w pionie
	 */
	boolean canVertiStart,canVertiIn,canVertiEnd;

	/**
	 * Kontruktor domyslny. Wypelnia komorke pustym stringiem, i ustawia wszystkie opcje wykorzystania na true
	 * 
	 */
	public BoardCell() {
		content="";
		canHorizEnd=canHorizIn=canHorizStart=canVertiEnd=canVertiIn=canVertiStart=true;
	}

	/**
	 * Metoda ustawiajaca mozliwosc komorki co do tego czy moze zaczynac/konczyc/byc czescia nowego hasla
	 * (metoda nieskonczona, przy prostym algorytmie generowanie krzyzowki i tak sie nie przydaje)
	 * 
	 * @param x polozenie komorki "na osi" ox
	 * @param y polozenie komorki "na osi" oy
	 * @param b tablica z komorkami
	 */
	public void checkPossibility(int x,int y,Board b){
		//False - komorka pusta
		//True - komorka zawiera w sobie litere
		boolean[][] tablicaPomocnicza = new boolean[3][3];
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++)
				if (b.getCell(x-1+j, y-1+i).content.isEmpty()) tablicaPomocnicza[i][j]=false;
				else tablicaPomocnicza[i][j]=true;
		}
		if (x==0) {
			if (y==0) {
				canHorizEnd=canVertiEnd=canHorizIn=canVertiIn=false;
			}
			if (y==b.getHeight()) {
				canHorizEnd=canVertiStart=canVertiIn=false;
			}
			canHorizEnd=false;
		}
		if (this.content.isEmpty()) {
			if (tablicaPomocnicza[0][0]) {
				if (tablicaPomocnicza[1][0]) canVertiEnd=false;
			}
			if (tablicaPomocnicza[0][1]) {
				canVertiEnd=false;
				canVertiStart=false;
				canHorizStart=false;
				canHorizEnd=false;
			}
			if (tablicaPomocnicza[0][2]) {
				canHorizStart=false;
			}
			if (tablicaPomocnicza[1][0]) {
				canVertiEnd=false;
				canVertiStart=false;
				canHorizStart=false;
				canHorizEnd=false;
			}
			if (tablicaPomocnicza[1][2]) {
				canVertiEnd=false;
				canVertiStart=false;
				canHorizStart=false;
				canHorizEnd=false;
			}
			if (tablicaPomocnicza[2][0]) {
				canVertiStart=false;
			}
			if (tablicaPomocnicza[2][1]) {
				canVertiEnd=false;
				canVertiStart=false;
				canHorizStart=false;
				canHorizEnd=false;
			}
			if (tablicaPomocnicza[2][2]) {
				canVertiStart=false;
			}
		}
	}
	
	/**
	 * ustawia zawartosc komorki
	 * 
	 * @param _content zawartosc, ktora ma byc wpisana do komorki
	 */
	public void setContent(String _content) {
		content=_content;
	}
	
	/**
	 * @return zawartosc komorki
	 */
	public String getContent() {
		return content;
	}
}
