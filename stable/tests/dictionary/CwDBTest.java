/**
 * 
 */
package dictionary;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import exception.WordNotFoundException;

/**
 * @author Jakub Fortunka
 *
 */
public class CwDBTest {

	/**
	 * Test method for {@link dictionary.CwDB#createDB(java.lang.String)}. Checking if loading file is working
	 * @throws FileNotFoundException 
	 */
	@Test(expected=FileNotFoundException.class)
	public void testCreateDBFileNotFound() throws FileNotFoundException {
		CwDB test = new CwDB("nieistniejacyPlik.txt");
		fail();
	}
	
	@Test
	public void testCreateDB() throws FileNotFoundException {
		CwDB test = new CwDB("res/cwdb.txt");
	}
	
	
	/**
	 * Test method for {@link dictionary.CwDB#get(java.lang.String)}.
	 * @throws FileNotFoundException 
	 * @throws WordNotFoundException 
	 */
	@Test
	public void testGet() throws FileNotFoundException, WordNotFoundException {
		CwDB test = new CwDB("res/cwdb.txt");
		Assert.assertNotNull(test);
		assertEquals("agora", test.get("agora").getWord());
	}
	
	/**
	 * Test method for {@link dictionary.CwDB#get(java.lang.String)}.
	 * @throws FileNotFoundException 
	 * @throws WordNotFoundException 
	 */
	@Test(expected=WordNotFoundException.class)
	public void testGetNotExistingWord() throws FileNotFoundException, WordNotFoundException {
		CwDB test = new CwDB("res/cwdb.txt");
		Assert.assertNotNull(test);
		String t = test.get("Nie istniejace slowo").getWord();
		fail();
	}
	
	/**
	 * Test method for {@link dictionary.CwDB#remove(java.lang.String)}.
	 * @throws FileNotFoundException 
	 * @throws WordNotFoundException 
	 */
	@Test(expected=WordNotFoundException.class)
	public void testRemove() throws FileNotFoundException, WordNotFoundException {
		CwDB test = new CwDB("res/cwdb.txt");
		Assert.assertNotNull(test);
		test.remove("agora");
		test.get("agora");
		fail();
	}
	
	
	
}
