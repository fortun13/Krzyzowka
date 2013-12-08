/**
 * 
 */
package view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Iterator;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import crossword.Crossword;
import crossword.SimpleStrategy;
import dictionary.CwEntry;
import exception.TooBigCrosswordException;

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
	 * Kontruktor panelu. Tworzy tabele oraz model tabeli
	 */
	public CrosswordPanel() {
		super();
		setBorder(null);
		setBackground(Color.LIGHT_GRAY);
		tableModel = new DefaultTableModel(new Object[]{},0);
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        table = new JTable();
        
        add(table);
       
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
	 * @param cw obiekt krzyzowki ktora chcemy zareprezentowac na ekranie
	 * @throws TooBigCrosswordException 
	 */
	public void paint2(Crossword cw) throws TooBigCrosswordException {
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setTableCellRenderer(cw);
		
		for (int i=0;i<tableModel.getColumnCount();i++) {
			TableColumn a = table.getColumnModel().getColumn(i);
			a.setPreferredWidth(40);
			table.setRowHeight(40);
		}
        super.repaint();
	}
	
	/**
	 * Metoda pozwala ustawic odpowiedni Renderer dla komorek tabeli w zaleznosci od strategii krzyzowki
	 * @param cw krzyzowka ktora ma zostac "namalowana"
	 * @throws TooBigCrosswordException {@link TooBigCrosswordException}
	 */
	private void setTableCellRenderer(Crossword cw) throws TooBigCrosswordException {
		if (cw.getStrategy() instanceof SimpleStrategy) {
			Iterator<CwEntry> it = cw.getROEntryIter();
			int[] e = new int[cw.getBoardCopy().getHeight()];
			int counter = 0;
			it.next();
			while (it.hasNext()) {
				e[counter] = it.next().getWord().length();
				counter++;
			}
			SimpleCwTableCellRenderer r = new SimpleCwTableCellRenderer(e);
			table.setDefaultRenderer(Object.class, r);
		}
	}
	
	/**
	 * zwraca model krzyzowki
	 * @return the tableModel
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	
	/**
	 * zwraca tabele z krzyzowka
	 * @return tabela z krzyzowka
	 */
	public JTable getTable() {
		return table;
	}

}
