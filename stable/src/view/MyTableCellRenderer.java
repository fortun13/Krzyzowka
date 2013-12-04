/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * @author Jakub Fortunka
 *
 */
public class MyTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	
	boolean isBordered = true;
	
	public MyTableCellRenderer() {
		// TODO Auto-generated constructor stub
		super();
		//setVerticalAlignment(CENTER);
		//setHorizontalAlignment(LEFT);
        //Color color = UIManager.getColor("Table.gridColor");
        //setBorder(new MatteBorder(2,2,2,2,color));
	}
	
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
    //    Color color = UIManager.getColor("Table.gridColor");
    //    setBorder(new MatteBorder(2,2,2,2,color));
      //  setBorder(new LineBorder(Color.RED));
        //setBackground(Color.red);
        setForeground(Color.WHITE);
        setVerticalAlignment(CENTER);
        setHorizontalAlignment(CENTER);
        setHorizontalTextPosition(CENTER);
        
        
      //  setForeground(Color.black);
        return this;
    }
	
	

}
