package interfaces;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Iterator;

import crossword.Crossword;
import dictionary.CwEntry;
import dictionary.Direction;

/**
 * @author Jakub Fortunka
 * 
 *Struktura pliku z ktorego nalezy czytac:
 *pierwsza linia: wysokosc szerokosc rodzajAlgorytmu(true - simple; false - complicated)
 *np. 30 30 false
 *kolejne linie - wpis pozycjaPrzestrzenna x y
 *np. kabina HORIZ 10 10
 *
 */
public class ImplementedWriter implements Writer {

	@Override
	public void write(Crossword cw) throws FileNotFoundException,IOException {
		// TODO Auto-generated method stub

		/*File plik = new File(String.valueOf(this.getUniqueID()) + ".txt");
		if (!plik.exists()) plik.createNewFile();
		FileWriter zapis = new FileWriter(plik.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(zapis);
		boolean simpleMethod=true;
		Iterator<CwEntry> it = cw.getROEntryIter();
		if (it.next().getD().equals(Direction.HORIZ)) simpleMethod=false;
		while (it.hasNext()) {
			if (it.next().getD().equals(Direction.VERT)) simpleMethod=false;
		}
		if (simpleMethod) 	bw.write(String.valueOf(cw.getBoardCopy().getHeight()) + " " + String.valueOf(cw.getBoardCopy().getWidth()) + " " + "true" + "\n");
		else bw.write(String.valueOf(cw.getBoardCopy().getHeight()) + " " + String.valueOf(cw.getBoardCopy().getWidth()) + " " + "false" + "\n");
		it = cw.getROEntryIter();
		it.next();
		while (it.hasNext()) {
			CwEntry wpis = it.next();
			bw.write(wpis.getWord() + " " + wpis.getD().toString() + " " + wpis.getX() + " " + wpis.getY() + "\n");
		}
		bw.close();*/
		
		try
	      {
	         FileOutputStream fileOut = new FileOutputStream(String.valueOf(this.getUniqueID()) + ".txt");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(cw);
	         out.close();
	         fileOut.close();
	    //     System.out.printf("Serialized data is saved in /tmp/employee.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	@Override
	public long getUniqueID() {
		// TODO Auto-generated method stub
		return new Date().getTime();
	}

}
