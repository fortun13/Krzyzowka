/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Klasa zastepujaca domyslny renderer dla komorek tabeli. Dzieki temu pola do wypelnienia sa ladnie zaznaczone :)
 * 
 * @author Jakub Fortunka
 * @version 1.0
 */
public class SimpleCwTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6996892836291668555L;
	/**
	 * tablica w ktorej znajduja sie dlugosci wszystkich wpisow w krzyzowce
	 */
	
	int[] entriesLength;
	
	/**
	 * Konstruktor klasy do renderowania komorek tabeli
	 * @param entries tablice z dlugosciami wszyskich wpisow krzyzowki
	 */
	public SimpleCwTableCellRenderer(int[] entries) {
		super();
        setOpaque(true);
        entriesLength=entries;
	}
	
	/**
	 * Metoda w ktorej ustawiane sa odpowiednie kolory i obramowanie komorek krzyzowki.
	 * 
	 * {@inheritDoc}
	 */
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (column==1) {
			setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
			setBackground(Color.WHITE);
		}
		else if (entriesLength[row]>column-1 && column!=1) {
        	setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
        	setBackground(Color.WHITE);
        }
        else {
        	setBorder(BorderFactory.createEmptyBorder());
        	setBackground(Color.WHITE);
        }
		table.setFont(new Font("Serif", Font.BOLD, 20));
        setHorizontalAlignment(CENTER);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        table.setBackground(Color.WHITE);
        return this;
    }
	
	

}
