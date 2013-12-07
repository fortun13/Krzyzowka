/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import crossword.Board;
import crossword.Crossword;

/**
 * Klasa zastepujaca domyslny renderer dla komorek tabeli. Dzieki temu pola do wypelnienia sa ladnie zaznaczone :)
 * 
 * @author Jakub Fortunka
 * @version 1.0
 */
public class MyTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6996892836291668555L;
	/**
	 * tablica znajdujaca sie w krzyzowce, reprezentowana w postaci obiektu klasy {@link Board}
	 */
	
	private Board b;
	boolean isSimple;
	
	/**
	 * Konstruktor klasy do renderowania komorek tabeli
	 * @param b tablica krzyzowki
	 * @param isSimple czy krzyzowka dla ktorej bedzie renderowana tabela jest wykonana prosta strategia
	 */
	public MyTableCellRenderer(Board b, boolean isSimple) {
		super();
        setOpaque(true);
        this.b=b;
        this.isSimple=isSimple;
	}
	
	/**
	 * Metoda w ktorej ustawiane sa odpowiednie kolory i obramowanie komorek krzyzowki.
	 * 
	 * {@inheritDoc}
	 */
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (isSimple) {
			if (!(b.getCell(column, row) == null) || (column != 0 && b.getCell(column, row)==null && b.getCell(column-1, row)!=null)) {
				setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
				setBackground(Color.WHITE);
			}
			else {
				setBorder(BorderFactory.createEmptyBorder());
				setBackground(Color.LIGHT_GRAY);
			}
			setHorizontalAlignment(CENTER);
			return this;
		}
		else {
			//Tutaj mozna umiescic sposob rysowania krzyzowki dla innego typu strategii (na razie do jednego, jeszcze bez pomyslu jak uniezaleznic calosc od implementacji)
			return null;
		}
			
    }
	
	

}
