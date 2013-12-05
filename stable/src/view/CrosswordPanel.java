/**
 * 
 */
package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * @author Jakub Fortunka
 *
 */
public class CrosswordPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3962127721455796203L;
	
	private JTable table;
	private DefaultTableModel tableModel;
	//private MyTableModel tableModel;
	
	/**
	 * @return the tableModel
	 */
	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * Create the panel.
	 */
	public CrosswordPanel() {
		super();
		setBackground(Color.LIGHT_GRAY);
		tableModel = new DefaultTableModel(new Object[]{},0);
		//tableModel = new MyTableModel();
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        table = new JTable();
        add(table);
        table.setBackground(Color.LIGHT_GRAY);
        table.setModel(tableModel);
        MyTableCellRenderer centerRenderer = new MyTableCellRenderer();
        table.setDefaultRenderer(String.class, centerRenderer);

	}
	
	/**
	 * @param tableModel the tableModel to set
	 */
	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		table.setModel(this.tableModel);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//g.drawString("Testowy String", 20, 20);
	}
	
	public void paint2() {
		this.getGraphics();
		//table.addColumn("KolumnaTestowa");
		//super.paint(g);
		//table.setOpaque(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		UIManager.getColor("Table.gridColor");
	new MyTableCellRenderer();
		
		for (int i=0;i<tableModel.getColumnCount();i++) {
			TableColumn a = table.getColumnModel().getColumn(i);
			a.setPreferredWidth(40);
			table.setRowHeight(40);
		}
        super.repaint();
		//g.drawString("Testowy String", 50, 50);
	}
}
