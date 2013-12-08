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
public class InteliCwDBTest {

	/**
	 * Test method for {@link dictionary.InteliCwDB#getRandom()}.
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testGetRandom() throws FileNotFoundException {
		InteliCwDB test = new InteliCwDB("res/cwdb.txt");
		Assert.assertNotNull(test);
		String pierwszy = test.getRandom().getWord();
		String drugi = test.getRandom().getWord();
		assertNotEquals(pierwszy, drugi);
	}

	/**
	 * Test method for {@link dictionary.InteliCwDB#getRandom(int)}.
	 * @throws FileNotFoundException 
	 * @throws WordNotFoundException 
	 */
	@Test
	public void testGetRandomInt() throws FileNotFoundException, WordNotFoundException {
		InteliCwDB test = new InteliCwDB("res/cwdb.txt");
		Assert.assertNotNull(test);
		int pierwszy = test.getRandom(5).getWord().length();
		int drugi = test.getRandom(6).getWord().length();
		assertEquals(5, pierwszy);
		assertEquals(6, drugi);
	}

	/**
	 * Test method for {@link dictionary.InteliCwDB#getRandom(java.lang.String)}.
	 * @throws FileNotFoundException 
	 * @throws WordNotFoundException 
	 */
	@Test
	public void testGetRandomString() throws FileNotFoundException, WordNotFoundException {
		InteliCwDB test = new InteliCwDB("res/cwdb.txt");
		Assert.assertNotNull(test);
		String pat = "a.ora";
		String badany = test.getRandom(pat).getWord();
		assertEquals("agora", badany);
	}
	
	@Test(expected=WordNotFoundException.class)
	public void testGetRandomNotExistingWord() throws FileNotFoundException, WordNotFoundException {
		InteliCwDB test = new InteliCwDB("res/cwdb.txt");
		test.getRandom(100);
		fail();
	}
	
	@Test(expected=WordNotFoundException.class)
	public void testGetRandomStringNotExistingWord() throws FileNotFoundException, WordNotFoundException {
		InteliCwDB test = new InteliCwDB("res/cwdb.txt");
		test.getRandom(".");
		fail();
	}

}
