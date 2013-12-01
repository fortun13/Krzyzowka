/**
 * 
 */
package view;

import interfaces.CwBrowser;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

import javax.swing.JToolBar;
import javax.swing.JMenuBar;

/**
 * @author Jakub Fortunka
 *
 */
public class Window extends JFrame {

	private JPanel contentPane;
	private CwBrowser crosswords;
	private CrosswordPanel crosswordPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Window() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 572, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		crosswordPanel = new CrosswordPanel();
		contentPane.add(crosswordPanel, BorderLayout.CENTER);
		OptionPanel optionPanel = new OptionPanel(crosswordPanel,contentPane);
		contentPane.add(optionPanel, BorderLayout.NORTH);
		
	}


}
