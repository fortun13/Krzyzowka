/**
 * 
 */
package view;

import interfaces.CwBrowser;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 * @author Jakub Fortunka
 *
 */
public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3493604451193932925L;
	
	private JPanel contentPane;
	private CwBrowser crosswords;
	private CrosswordPanel crosswordPanel;
	private JPanel cluePanel;
	private JTextArea textArea;
	private JPanel listPanel;
	private JList<String> cwList;
	private JScrollPane scrollPane;

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
		
		listPanel = new JPanel();
		contentPane.add(listPanel, BorderLayout.WEST);
		
		scrollPane = new JScrollPane();
		listPanel.add(scrollPane);
		
		cwList = new JList<String>();
		scrollPane.setViewportView(cwList);
		cwList.setVisibleRowCount(10);
		cwList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		OptionPanel optionPanel = new OptionPanel(crosswordPanel,this,textArea,cwList);
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
