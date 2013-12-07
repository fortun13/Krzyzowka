/**
 * Pakiet w którym znajduja sie klasy odpowiedzialne za graficzne wyswietlanie krzyzowki
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
	
	/**
	 * Glowne okno programu
	 */
	private JFrame frame;
	/**
	 * Panel w ktorym rysowana jest krzyzowka
	 */
	private CrosswordPanel cw;
	/**
	 * przycisk do otwierania slownika
	 */
	/**
	 * przycisk do generowania krzyzowki
	 */
	/**
	 * przycisk do otwierania krzyzowki/krzyzowek z pliku/folderu
	 */
	/**
	 * przycisk do drukowania krzyzowki
	 */
	/**
	 * przycisk do rozwiazywania krzyzowki
	 */
	/**
	 * przycisk do zapisywania krzyzowki
	 */
	private JButton btnOpenDict,btnGenCw,btnLoadCw,btnPrint,btnSolveCw,btnSaveCw;
	/**
	 * zajmuje sie wczytywanie plikow
	 */
	private JFileChooser fc;
	/**
	 * symbol nowej linii
	 */
	private String newline = "\n";
	/**
	 * pole do wprowadzania wysokosci krzyzowki
	 */
	/**
	 * pole do wprowadzania szerokosci krzyzowki
	 */
	private JSpinner height,width;
	/**
	 * baza hasel krzyzowki (jezeli ustawiona na null, brany jest domyslny slownik)
	 */
	private InteliCwDB cwDB = null;
	/**
	 * pole tekstowe na ktorym wyswietlane sa podpowiedzi do hasel
	 */
	private JTextArea clueArea;
	/**
	 * obiekt klasy zajmujacej sie zarzadzaniem krzyzowkami
	 */
	private CwBrowser crosswords;
	/**
	 * numer obecnie wyswietlanej krzyzowki 
	 */
	/**
	 * ilosc wygenerowanych krzyzowek
	 */
	private int indexOfCrossword = -1, numberOfGeneratedCrosswords=0;
	/**
	 * Panel w ktorym jest lista do zarzadzania wczytanymi krzyzowkami
	 */
	private JPanel cwIO;
	/**
	 * Model listy
	 */
	private DefaultListModel<String> listModel;
	/**
	 * lista na ktorej pojawiaja sie wygenerowane/wczytane krzyzowki
	 */
	private JList<String> list;
	
	/**
	 * Kontruktor panelu
	 * 
	 * @param cw Panel w ktorym bedzie rysowana krzyzowka {@link #cw}
	 * @param main glowne okno {@link #frame}
	 * @param text pole w ktorym beda wypisywane podpowiedzi {@link #clueArea}
	 * @param l lista krzyzowek, ktora bedzie zarzadzana z tej klasy {@link #list}
	 */
	public OptionPanel(CrosswordPanel cw, Window main,JTextArea text, JList<String> l) {
		setBackground(SystemColor.activeCaption);
		main.getContentPane();
		this.frame = main;
		this.cw=cw;
		this.clueArea = text;
		this.crosswords = new CwBrowser();
		listModel = new DefaultListModel<String>();
		l.setModel(listModel);
		this.list=l;
		
		/* Panel w ktorym znajduja sie opcje generacji krzyzowki */	
		
		fc = new JFileChooser();
		setLayout(new BorderLayout(0, 0));
		JPanel cwSize = new JPanel();
		cwSize.setBackground(new Color(102, 204, 204));
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
        cwIO.setBackground(new Color(102, 204, 204));
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
	
	/**
	 * Metoda zajmuje sie przechwytywaniem zdarzen i wykonywaniem wtedy odpowiednich operacji
	 * Korzysta z prywatnych metod wewnetrznych
	 * {@link #generateNewCrossword()}
	 * {@link #openDictionary()}
	 * {@link #loadCrossword()}
	 * {@link #saveCrossword()}
	 * {@link #paintCrossword(boolean)}
	 * {@link #writeClues()}
	 * 
	 * @param e rodzaj wykonanej akcji 
	 * 
	 * {@inheritDoc}
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGenCw) {
			generateNewCrossword();
		}
		else if (e.getSource() == btnOpenDict)	{
			openDictionary();
		}
		else if (e.getSource() == btnLoadCw) {
			loadCrossword();
		}
		else if (e.getSource() == btnSaveCw) {
			saveCrossword();
		}
		else if (e.getSource() == btnSolveCw) {
			paintCrossword(false);
			writeClues();
		}
	}
	
	/**
	 * Metoda wykonujaca rysowanie na {@link CrosswordPanel}. Wykorzystuje jej wewnetrzna metode {@link CrosswordPanel#paint2(Board)};
	 * 
	 * @param isGenerating jesli true to krzyzowka nie wyswietla wpisanych hasel (pozostawione sa puste kwadraty)
	 * @throws TooBigCrosswordException 
	 */
	private void paintCrossword(boolean isGenerating) {
	//	String[] lista = new String[(int)width.getValue()];
		int width;
		try {
			width = crosswords.getCrossword(indexOfCrossword).getBoardCopy().getWidth();
			int height = crosswords.getCrossword(indexOfCrossword).getBoardCopy().getHeight();
			String[] lista = new String[width];
			for (int i=0;i<width;i++) {
				lista[i] = String.valueOf(i);
			}
			String[] row = new String[width+1];
			int[] entriesLength = new int[height];
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
			int dlugoscWyrazu = 0;
			Iterator<CwEntry> it = crosswords.getCrossword(indexOfCrossword).getROEntryIter();
			Board boardCopy = crosswords.getCrossword(indexOfCrossword).getBoardCopy();
			it.next();
			for (int i=0;i<height;i++) {
				dlugoscWyrazu = it.next().getWord().length();
				entriesLength[i] = dlugoscWyrazu;
				for (int k=0;k<row.length;k++) row[k]="";
				for (int j=0;j<dlugoscWyrazu+1;j++){
					if (j==0) row[j]=String.valueOf(i+1);
					else if(isGenerating) row[j]="";
					else row[j] = boardCopy.getCell(j-1, i).getContent();
				}
				tmp.addRow(row);
			}
			
			cw.setTableModel(tmp);
			//cw.paint2(entriesLength);
			cw.paint2(crosswords.getCrossword(indexOfCrossword).getBoardCopy());
		} catch (TooBigCrosswordException e) {
			JOptionPane.showMessageDialog(frame,
					e.getMessage(),
					"B³¹d generowania krzy¿ówki",
					JOptionPane.ERROR_MESSAGE);
			return ;
		}
	}
	
	/**
	 * Metoda wypisuje na odpowiednim panelu podpowiedzi do hasel krzyzowki
	 */
	private void writeClues() {
		int height;
		try {
			height = crosswords.getCrossword(indexOfCrossword).getBoardCopy().getHeight();
			Iterator<CwEntry> it = crosswords.getCrossword(indexOfCrossword).getROEntryIter();
			it.next();
			String tekst = "";
			for (int i=0;i<height;i++) {
				tekst+= String.valueOf(i+1) + ". " + it.next().getClue() + newline;	
			}
			clueArea.setText(tekst);
		} catch (TooBigCrosswordException e) {
			JOptionPane.showMessageDialog(frame,
					e.getMessage(),
					"B³¹d generowania krzy¿ówki",
					JOptionPane.ERROR_MESSAGE);
			return ;
		}
	}
	
	/**
	 * Wewnetrzna metoda generujaca nowa krzyzowke
	 * 
	 */
	private void generateNewCrossword() {
		try {
			crosswords.addCrossword(crosswords.generateCrossword((int)height.getValue(), (int)width.getValue(), new SimpleStrategy(), cwDB));
			indexOfCrossword++;
			numberOfGeneratedCrosswords++;
			listModel.addElement("Krzyzowka" + String.valueOf(numberOfGeneratedCrosswords));
			paintCrossword(true);
			writeClues();
		}
		catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(frame,
					"Program nie znalaz³ pliku s³ownika (wczyta³eœ go?)",
					"Brak pliku slownika",
					JOptionPane.INFORMATION_MESSAGE);
			return ;
		}
		catch (WordNotFoundException ex) {
			JOptionPane.showMessageDialog(frame,
					"Program wylosowal takie haslo, ze nie potrafi znalezc hasel.\n Sprobuj wygenerowac krzyzowke jeszcze raz :)",
					"B³¹d generowania krzy¿ówki",
					JOptionPane.INFORMATION_MESSAGE);
			return ;
		} catch (TooBigCrosswordException e) {
			JOptionPane.showMessageDialog(frame,
					e.getMessage(),
					"B³¹d generowania krzy¿ówki",
					JOptionPane.ERROR_MESSAGE);
			return ;
		}
	}
	
	/**
	 * Wewnetrzna metoda zajmujaca sie wczytywaniem pliku slownika
	 */
	private void openDictionary() {
		if(fc.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION)	{
			JOptionPane.showMessageDialog(frame,
				    "Nie jestem w stanie zainicjowaæ okna wyboru pliku (coœ siê mocno popsu³o :()",
				    "B³¹d inicjalizacji okna",
				    JOptionPane.ERROR_MESSAGE);
			return ;
		}
		
		File f = fc.getSelectedFile();
		try {
			cwDB = new InteliCwDB(f.getAbsolutePath());
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(frame,
				    "Program nie znalaz³ pliku s³ownika (wczyta³eœ go?)",
				    "B³ad wczytywania krzy¿ówki",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Wewnetrzna metoda obslugujaca wczytywanie krzyzowki ze wskazanego pliku
	 */
	private void loadCrossword() {
		if (fc.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(frame,
				    "Nie jestem w stanie zainicjowaæ okna wyboru pliku (coœ siê mocno popsu³o :()",
				    "B³¹d inicjalizacji okna",
				    JOptionPane.ERROR_MESSAGE);
			return ;
		}
		File f = fc.getSelectedFile();
		try {
			crosswords.readCrossword(f.getAbsolutePath());
			indexOfCrossword++;
			paintCrossword(true);
			writeClues();
			listModel.addElement(f.getName().substring(0, f.getName().length()-4));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(frame,
				    "Problemy z wczytywaniem (spróbuj ponownie, mo¿e siê uda ;))",
				    "B³ad wczytywania krzy¿ówki",
				    JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(frame,
				    "Plik, który poda³eœ do wczytania jest najprawdopodobniej b³êdny",
				    "B³ad wczytywania krzy¿ówki",
				    JOptionPane.ERROR_MESSAGE);
		} catch (WordNotFoundException e) {
			JOptionPane.showMessageDialog(frame,
				    "Nie znaleziono pliku do wczytania (co dziwne, bo go wskaza³eœ...)",
				    "B³ad wczytywania krzy¿ówki",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Metoda zajmuje sie zapisywaniem wyswietlanej krzyzowki do pliku
	 */
	private void saveCrossword() {
		if (indexOfCrossword <0) {
			JOptionPane.showMessageDialog(frame,
				    "Nie wygenerowa³eœ ¿adnej krzy¿ówki, jak mam cokolwiek zapisaæ cwaniaku?",
				    "B³¹d zapisywania krzy¿ówki",
				    JOptionPane.WARNING_MESSAGE);
			return ;
		}
		try {
			crosswords.saveCrossword(crosswords.getCrossword(indexOfCrossword));
			JOptionPane.showMessageDialog(frame, "Zapisa³em!");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
}
