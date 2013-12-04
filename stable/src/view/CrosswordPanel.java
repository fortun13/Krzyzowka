/**
 * 
 */
package view;

import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.CardLayout;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.GridLayout;

import javax.swing.JScrollPane;

/**
 * @author Jakub Fortunka
 *
 */
public class CrosswordPanel extends JPanel {
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
		Graphics g = this.getGraphics();
		//table.addColumn("KolumnaTestowa");
		//super.paint(g);
		//table.setOpaque(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		Color color = UIManager.getColor("Table.gridColor");
	//	MatteBorder border = new MatteBorder(2, 2, 2, 2, color);
	//	table.setBorder(border);
		//not working
	//	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	//	centerRenderer.setHorizontalAlignment( JLabel.LEFT );
		MyTableCellRenderer centerRenderer = new MyTableCellRenderer();
	//	centerRenderer.setBorder(border);
		table.setDefaultRenderer(String.class, centerRenderer);
		//table.getColumnModel().getColumn(0).
		
		for (int i=0;i<tableModel.getColumnCount();i++) {
			TableColumn a = table.getColumnModel().getColumn(i);
			a.setPreferredWidth(40);
			table.setRowHeight(40);
		}
        super.repaint();
		//g.drawString("Testowy String", 50, 50);
	}
}
