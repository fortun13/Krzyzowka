/**
 * 
 */
package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import crossword.Board;

import javax.swing.JScrollPane;

import java.awt.BorderLayout;

/**
 * Klasa reprezentujaca panel w ktorym rysowana jest krzyzowka. Znajduje sie tutaj metoda odpowiedzialna za narysowanie krzyzowki
 * 
 * @author Jakub Fortunka
 * @version 1.0
 */
public class CrosswordPanel extends JPanel {
	/**
	 *
	 */
	private static final long serialVersionUID = 3962127721455796203L;
	
	/**
	 * tabela ktora reprezentuje krzyzowke
	 */
	private JTable table;
	/**
	 * model tabeli - zapewnia m.in. dynamiczny rozmiar krzyzowki
	 */
	private DefaultTableModel tableModel;
	
	/**
	 * zwraca model krzyzowki
	 * @return the tableModel
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * Kontruktor panelu. Tworzy tabele oraz model tabeli
	 */
	public CrosswordPanel() {
		super();
		setBackground(Color.LIGHT_GRAY);
		tableModel = new DefaultTableModel(new Object[]{},0);
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        table = new JTable();
        table.setFont(new Font("Serif", Font.BOLD, 20));
        add(table);
        //scrollPane.setViewportView(table);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(false);
        table.setBackground(Color.LIGHT_GRAY);
        table.setModel(tableModel);
        
        table.setShowGrid(false);
        table.setBorder(BorderFactory.createEmptyBorder());

	}
	
	/**
	 * Ustawia tableModel dla wykorzystywanej tabeli
	 * 
	 * @param tableModel model tabeli do ustawienia 
	 */
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		table.setModel(this.tableModel);
	}

	
	/**
	 * Metoda zajmujaca sie "namalowaniem" krzyzowki w panelu
	 * 
	 * @param b tablica reprezentujaca krzyzowke
	 */
	public void paint2(Board b) {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	//	MyTableCellRenderer r = new MyTableCellRenderer(e);
		MyTableCellRenderer r = new MyTableCellRenderer(b,true);
		table.setDefaultRenderer(Object.class, r);
		
		for (int i=0;i<tableModel.getColumnCount();i++) {
			TableColumn a = table.getColumnModel().getColumn(i);
			a.setPreferredWidth(40);
			table.setRowHeight(40);
		}
        super.repaint();
	}
}
