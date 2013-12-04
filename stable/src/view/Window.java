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
import javax.swing.border.LineBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 * @author Jakub Fortunka
 *
 */
public class Window extends JFrame {

	private JPanel contentPane;
	private CwBrowser crosswords;
	private CrosswordPanel crosswordPanel;
	private JPanel cluePanel;
	private JTextArea textArea;

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
		setTitle("Program do generowania krzyzowek");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		crosswordPanel = new CrosswordPanel();
		crosswordPanel.setBackground(Color.GRAY);
		crosswordPanel.setBorder(new LineBorder(Color.black));
		contentPane.add(crosswordPanel, BorderLayout.CENTER);
		
		
		cluePanel = new JPanel();
		cluePanel.setBackground(Color.WHITE);
		contentPane.add(cluePanel, BorderLayout.SOUTH);
		
		textArea = new JTextArea();
		textArea.setBackground(Color.WHITE);
		textArea.setEditable(false);
		cluePanel.add(textArea);
		
		OptionPanel optionPanel = new OptionPanel(crosswordPanel,this,textArea);
		contentPane.add(optionPanel, BorderLayout.NORTH);
		
		//textArea.setText("Test");
		
		
	}

	/**
	 * @return the crosswords
	 */
	public CwBrowser getCrosswords() {
		return crosswords;
	}

	/**
	 * @return the contentPane
	 */
	public JPanel getContentPane() {
		return contentPane;
	}


}
