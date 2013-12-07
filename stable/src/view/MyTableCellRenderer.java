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
	 * kolejne dlugosci wpisow krzyzowki
	 */
	
	//int[] entries;
	private Board b;
	
	/**
	 * Konstruktor klasy do renderowania komorek tabeli
	 * @param e tablica z dlugosciami kolejnych wpisow krzyzowki
	 */
	public MyTableCellRenderer(Board b) {
		super();
        setOpaque(true);
        //entries=e;
        this.b=b;
	}
	
	/**
	 * Metoda w ktorej ustawiane sa odpowiednie kolory i obramowanie komorek krzyzowki.
	 * 
	 * {@inheritDoc}
	 */
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		//if (entries[row]>column-1) setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		//else setBorder(BorderFactory.createEmptyBorder());
		if (!(b.getCell(column, row) == null) || (column != 0 && b.getCell(column, row)==null && b.getCell(column-1, row)!=null)) {
			setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
			setBackground(Color.WHITE);
		}
		else {
			setBorder(BorderFactory.createEmptyBorder());
			setBackground(Color.LIGHT_GRAY);
		}
		//setBackground(Color.WHITE);
		setHorizontalAlignment(CENTER);
		return this;
    }
	
	

}
