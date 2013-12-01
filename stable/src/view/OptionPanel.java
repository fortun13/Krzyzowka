/**
 * 
 */
package view;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.JFormattedTextField;
import javax.swing.JToolBar;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import java.awt.CardLayout;

import crossword.*;
import dictionary.*;

/**
 * @author Jakub Fortunka
 *
 */
public class OptionPanel extends JPanel implements ActionListener {
	
	CrosswordPanel cw;
	JPanel mainPanel;
	JButton btnOpenDict,btnGenCw,btnLoadCw,btnPrint,btnSolveCw;
	JFileChooser fc;
	String newline = "\n";
	JTextArea log;
	JSpinner height,width;
	
	public OptionPanel(CrosswordPanel cw, JPanel main) {
		setBackground(SystemColor.activeCaption);
		mainPanel = main;
		this.cw=cw;

		/* Panel w ktorym znajduja sie opcje generacji krzyzowki */	
		
		/*panel zajmujacy sie wczytywanie z pliku*/
		fc = new JFileChooser();
		JPanel cwSize = new JPanel();
		add(cwSize);
		JLabel lblHeight = new JLabel("Wysoko\u015B\u0107");
		height = new JSpinner();
		width = new JSpinner();
		cwSize.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		cwSize.add(lblHeight);
		cwSize.add(height);
		JLabel lblWidth = new JLabel("Szeroko\u015B\u0107");
		cwSize.add(lblWidth);
		cwSize.add(width);
		btnGenCw = new JButton("GenerujKrzyzowke");
		btnGenCw.addActionListener(this);
		cwSize.add(btnGenCw);
		
		log = new JTextArea(5,20);
		log.setMargin(new Insets(3, 3, 3, 3));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);
		add(logScrollPane);
		
		btnOpenDict = new JButton("OtworzPlikSlownika");
		btnOpenDict.addActionListener(this);
        
		/*Panel zajmujacy sie innymi opcjami krzyzowki (zapisanie, drukowanie, rozwiazanie) */
        JPanel cwOptions = new JPanel();
        add(cwOptions);
        
        btnLoadCw = new JButton("ZapiszKrzyzowke");
        cwOptions.add(btnLoadCw);
        
        btnPrint = new JButton("DrukujKrzyzowke");
        cwOptions.add(btnPrint);
        
        btnSolveCw = new JButton("RozwiazKrzyzowke");
        cwOptions.add(btnSolveCw);
        
        cwOptions.add(btnOpenDict);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOpenDict) {
			int returnVal = fc.showOpenDialog(OptionPanel.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would open the file.
                log.append("Opening: " + file.getName() + "." + newline);
            } else {
                log.append("Open command cancelled by user." + newline);
            }
            log.setCaretPosition(log.getDocument().getLength());
		}
		else if (e.getSource() == btnGenCw) {
			try {
				InteliCwDB db = new InteliCwDB("cwdb.txt");
				Crossword cross = new Crossword();
				Board b = new Board((int)width.getValue(), (int)height.getValue());
				cross.setCwDB(db);
				cross.setBoard(b);
				SimpleStrategy s = new SimpleStrategy();
				cross.generate(s);
				
				
				String[] lista = new String[(int)width.getValue()];
				for (int i=0;i<(int)width.getValue();i++) {
					lista[i] = String.valueOf(i);
				}
				String[] row = new String[(int)width.getValue()];
				DefaultTableModel tmp = new DefaultTableModel(lista,0);
				for (int i=0;i<(int)height.getValue();i++) {
					int dlugoscWyrazu = cross.getROEntryIter().next().getWord().length();
					for (int j=0;j<dlugoscWyrazu;j++) {
						row[j] = cross.getBoardCopy().getCell(j, i).getContent();
					}
					tmp.addRow(row);
				}
				cw.setTableModel(tmp);
				//cw.getTableModel().addRow(new Object[]{"Test1","Test2"});
				cw.paint2();
			}
			catch (WordNotFoundException e1) {
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}

}
