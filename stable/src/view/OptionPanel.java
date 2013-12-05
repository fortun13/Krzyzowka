/**
 * 
 */
package view;

import interfaces.CwBrowser;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.SystemColor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import java.awt.FlowLayout;
import crossword.*;
import dictionary.*;

/**
 * @author Jakub Fortunka
 *
 */
public class OptionPanel extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7931596291324954652L;
	
	private JFrame frame;
	private CrosswordPanel cw;
	private JButton btnOpenDict,btnGenCw,btnLoadCw,btnPrint,btnSolveCw,btnSaveCw;
	private JFileChooser fc;
	private String newline = "\n";
	private JSpinner height,width;
	private InteliCwDB cwDB = null;
	private JTextArea clueArea;
	private CwBrowser crosswords;
	private int indexOfCrossword = -1, numberOfGeneratedCrosswords=0;
	private JPanel cwIO;
	private DefaultListModel<String> listModel;
	private JList<String> list;
	
	public OptionPanel(CrosswordPanel cw, Window main,JTextArea text, JList<String> l) {
		setBackground(SystemColor.activeCaption);
		main.getContentPane();
		this.frame = main;
		this.cw=cw;
		this.clueArea = text;
		this.crosswords = new CwBrowser();
		//this.list=l;
		listModel = new DefaultListModel<String>();
		l.setModel(listModel);
		this.list=l;
		
		/* Panel w ktorym znajduja sie opcje generacji krzyzowki */	
		
		
		fc = new JFileChooser();
		setLayout(new BorderLayout(0, 0));
		JPanel cwSize = new JPanel();
		cwSize.setBackground(new Color(143, 188, 143));
		add(cwSize, BorderLayout.NORTH);
		JLabel lblHeight = new JLabel("Wysoko\u015B\u0107");
		height = new JSpinner(new SpinnerNumberModel(5, 3, 50, 1));
		width = new JSpinner(new SpinnerNumberModel(10, 7, 50, 1));
		cwSize.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		cwSize.add(lblHeight);
		cwSize.add(height);
		JLabel lblWidth = new JLabel("Szeroko\u015B\u0107");
		cwSize.add(lblWidth);
		cwSize.add(width);
		
		btnGenCw = new JButton("GenerujKrzyzowke");
		btnGenCw.addActionListener(this);
		cwSize.add(btnGenCw);
		
		btnPrint = new JButton("DrukujKrzyzowke");
		cwSize.add(btnPrint);
		
		btnSolveCw = new JButton("RozwiazKrzyzowke");
		cwSize.add(btnSolveCw);
		btnSolveCw.addActionListener(this);
        
        /*panel zajmujacy sie wczytywanie z pliku*/
        
        cwIO = new JPanel();
        cwIO.setBackground(new Color(143, 188, 143));
        add(cwIO, BorderLayout.CENTER);
        
        btnLoadCw = new JButton("OtworzKrzyzowke");
        btnLoadCw.addActionListener(this);
        cwIO.add(btnLoadCw);
        
        btnOpenDict = new JButton("OtworzPlikSlownika");
        btnOpenDict.addActionListener(this);
        cwIO.add(btnOpenDict);
        
        btnSaveCw = new JButton("ZapiszKrzyzowke");
        btnSaveCw.addActionListener(this);
        cwIO.add(btnSaveCw);
        
        list.addMouseListener(new MouseAdapter() {
        	 public void mouseClicked(MouseEvent e) {
        	        if (e.getClickCount() == 2) {
        	        	if (list.getModel().getSize()==0) {
        	        		JOptionPane.showMessageDialog(frame, "Lista jest pusta - nie mam do wybrania zadnej krzyzowki!");
        					return ;
        	        	}
        	        	indexOfCrossword=list.getSelectedIndex();
        	        	paintCrossword(true);
        	        	writeClues();
        	         }
        	    }
		});
        
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGenCw) {
			try {
				/*Crossword cross = new Crossword();
				Board b = new Board((int)width.getValue(), (int)height.getValue());
				cross.setCwDB(cwDB);
				cross.setBoard(b);
				SimpleStrategy s = new SimpleStrategy();
				cross.generate(s);
				crosswords.addCrossword(cross);*/
				crosswords.addCrossword(crosswords.generateCrossword((int)height.getValue(), (int)width.getValue(), new SimpleStrategy(), cwDB));
				indexOfCrossword++;
				numberOfGeneratedCrosswords++;
				listModel.addElement("Krzyzowka" + String.valueOf(numberOfGeneratedCrosswords));
				/*String[] lista = new String[(int)width.getValue()];
				for (int i=0;i<(int)width.getValue();i++) {
					lista[i] = String.valueOf(i);
				}
				String[] row = new String[(int)width.getValue()+1];
				DefaultTableModel tmp = new DefaultTableModel(lista,0);
				//MyTableModel tmp = new MyTableModel(lista,0);
				int dlugoscWyrazu = 0;
				Iterator<CwEntry> it = cross.getROEntryIter();
				it.next();
				for (int i=0;i<(int)height.getValue();i++) {
					dlugoscWyrazu = it.next().getWord().length();
					for (int k=0;k<row.length;k++) row[k]="";
					for (int j=0;j<dlugoscWyrazu+1;j++){
						if (j==0) row[j]=String.valueOf(i+1);
						//else row[j] = cross.getBoardCopy().getCell(j-1, i).getContent();
						else row[j]="";
					}
					tmp.addRow(row);
				}
				cw.setTableModel(tmp);
				//cw.getTableModel().addRow(new Object[]{"Test1","Test2"});*/
				paintCrossword(true);
				writeClues();
				
				//cw.paint2();
			}
			catch (WordNotFoundException | FileNotFoundException e1) {
				e1.printStackTrace();
			}
			
		}
		else if (e.getSource() == btnOpenDict)	{
			if(fc.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION)	{
			//	JOptionPane.showMessageDialog(frame, "Blad krytyczny :(");
				return ;
			}
			
			File f = fc.getSelectedFile();
			cwDB = new InteliCwDB(f.getAbsolutePath());
		}
		else if (e.getSource() == btnLoadCw) {
			if (fc.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION) return;
			File f = fc.getSelectedFile();
			try {
				crosswords.readCrossword(f.getAbsolutePath());
				indexOfCrossword++;
				paintCrossword(true);
				writeClues();
				listModel.addElement(f.getName().substring(0, f.getName().length()-4));
			} catch (IOException | WordNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (e.getSource() == btnSaveCw) {
			if (indexOfCrossword <0) {
				JOptionPane.showMessageDialog(frame, "Nie wygenerowales zadnej krzyzowki!");
				return ;
			}
			//if (fc.showSaveDialog(frame) != JFileChooser.APPROVE_OPTION) return;
			try {
				crosswords.saveCrossword(crosswords.getCrossword(indexOfCrossword));
				JOptionPane.showMessageDialog(frame, "Zapisalem!");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		/*else if (e.getSource() == btnLoadCw) {
			JFileChooser fc = new JFileChooser();
			
			if(fc.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION)
			{
				return;
			}
			
			File f = fc.getSelectedFile();
			
			try
			{
				Crossword cw = main.getCrosswords().readCrossword(f.getAbsolutePath());
				openCrossword(cw, cw.getID() == -1 ? "Otwarta krzyżówka" : "" + cw.getID());
			}
			catch(IOException ex)
			{
				JOptionPane.showMessageDialog(frame, "Nie można wczytać tego pliku.", "Ups!", JOptionPane.ERROR_MESSAGE);
			}
		}*/
		else if (e.getSource() == btnSolveCw) {
			paintCrossword(false);
			writeClues();
		}
		
	}
	
	private void paintCrossword(boolean isGenerating) {
		String[] lista = new String[(int)width.getValue()];
		for (int i=0;i<(int)width.getValue();i++) {
			lista[i] = String.valueOf(i);
		}
		String[] row = new String[(int)width.getValue()+1];
		DefaultTableModel tmp = new DefaultTableModel(lista,0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -105499342896572860L;

			@Override
		    public boolean isCellEditable(int row, int column) {
		        if (column==0) return false;
		        else return true;
		    }
		};
		//MyTableModel tmp = new MyTableModel(lista,0);
		int dlugoscWyrazu = 0;
		Iterator<CwEntry> it = crosswords.getCrossword(indexOfCrossword).getROEntryIter();
		it.next();
		for (int i=0;i<(int)height.getValue();i++) {
			dlugoscWyrazu = it.next().getWord().length();
			for (int k=0;k<row.length;k++) row[k]="";
			for (int j=0;j<dlugoscWyrazu+1;j++){
				if (j==0) row[j]=String.valueOf(i+1);
				else if(isGenerating) row[j]="";
				else row[j] = crosswords.getCrossword(indexOfCrossword).getBoardCopy().getCell(j-1, i).getContent();
			}
			tmp.addRow(row);
		}
		
		cw.setTableModel(tmp);
		//cw.getTableModel().addRow(new Object[]{"Test1","Test2"});
		cw.paint2();
	}
	
	private void writeClues() {
		
		Iterator<CwEntry> it = crosswords.getCrossword(indexOfCrossword).getROEntryIter();
		it.next();
		String tekst = "";
		for (int i=0;i<(int)height.getValue();i++) {
			//clueArea.setText(it.next().getClue());
			tekst+= String.valueOf(i+1) + ". " + it.next().getClue() + newline;	
		}
		clueArea.setText(tekst);
		//clueArea.setText("Test");
	}

}
