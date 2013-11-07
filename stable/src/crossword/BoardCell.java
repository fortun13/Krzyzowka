/**
 * 
 */
package crossword;

/**
 * Klasa ktora reprezentuje jedna komorke krzyzowki
 * 
 * @author Jakub Fortunka
 *
 */
public class BoardCell {

	String content="";
	boolean canHorizStart,canHorizIn,canHorizEnd;
	boolean canVertiStart,canVertiIn,canVertiEnd;
	/**
	 * 
	 */
	public BoardCell() {
		// TODO Auto-generated constructor stub
		content="";
		canHorizEnd=canHorizIn=canHorizStart=canVertiEnd=canVertiIn=canVertiStart=true;
	}

	/**
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
	 * @param _content zawartosc, ktora ma byc wpisana do komorki
	 */
	public void setContent(String _content) {
		content=_content;
	}
	
	public String getContent() {
		return content;
	}
}
