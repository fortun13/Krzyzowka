/**
 * 
 */
package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;

import java.awt.BorderLayout;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import net.miginfocom.swing.MigLayout;

import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.awt.GridLayout;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Jakub Fortunka
 *
 */
public class Test {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblPodajSwojeImie = new JLabel("Podaj swoje imie");
		lblPodajSwojeImie.setBounds(200, 92, 163, 14);
		layeredPane.add(lblPodajSwojeImie);
		
		textField = new JTextField();
		textField.setBounds(198, 120, 86, 20);
		layeredPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTwojeImieTo = new JLabel("Twoje imie to:");
		lblTwojeImieTo.setBounds(199, 225, 85, 14);
		layeredPane.add(lblTwojeImieTo);
		
		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(200, 262, 46, 14);
		layeredPane.add(lblNewLabel);
		//lblNewLabel.setText("Test");
		
		JButton btnJuz = new JButton("Juz");
		btnJuz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setText("Test");
				System.exit(0);
			}
		});
		btnJuz.setBounds(195, 161, 89, 23);
		layeredPane.add(btnJuz);
		
	}
}
