import student.TestCase;

import java.io.FileNotFoundException;

/**
 * This class test the Parser class
 *
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 09/05/2016
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
            Parser.parse("error file");
        }
        catch (Exception exception) {
            e = exception;
            assertTrue(e instanceof FileNotFoundException);
        }

        e = null;

        try {
            Parser.parse("P1sampleInput.txt");
        }
        catch (Exception exception) {
            e = exception;
        }
        try {
            Parser.parse("corrupted-file.txt");
        }
        catch (Exception exception) {
            e = exception;
        }
        assertNull(e);

    }

}
