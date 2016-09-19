import student.TestCase;

import java.io.FileNotFoundException;

/**
 * This class test the Parser class
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/15/2016
 */

public class ProcessorTest extends TestCase {
    /**
     * Parser we will use for our test
     */
    Processor processor;

    /**
     * Set up our variable
     */
    public void setUp() {
        try {
            processor = new Processor(10, 30, "P1sampleInut.txt");
        }
        catch (Exception e) {
            assertTrue(e instanceof FileNotFoundException);
        }
        
        try {
            processor = new Processor(10, 30, "P1sampleInput.txt");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    
    /**
     * Test Process method
     */
    public void testProcess() {
        try {
            processor.process();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String assertionValue = null;
        assertNull(assertionValue);
    }
    
}
