import java.io.FileNotFoundException;
import java.io.IOException;

import student.TestCase;

/**
 * This class test the Parser class
 * 
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/05/2016
 *
 */

public class ParserTest extends TestCase {
	
	/**
	 * Parser we will use for our test
	 */
	Parser parser;
	
	/**
	 * Set up our variable
	 */
	public void setUp() {
		parser = new Parser();
	}
	
	/**
	 * Test parse method
	 */
	public void testParse() {
		
		
		Exception e = null;
		
        try {
        	parser.parse("error file");
        } 
        catch (Exception exception) {
            e = exception;
            assertTrue(e instanceof FileNotFoundException);
        }
        
        
        e = null;
        
        try {
        	parser.parse("P1sampleInput.txt");
        } 
        catch (Exception exception) {
            e = exception;
        }
        
        assertNull(e);
		
	}
	
}
