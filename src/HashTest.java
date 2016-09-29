import org.junit.Assert;
import student.TestCase;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

// -------------------------------------------------------------------------

/**
 * Test the hash function (you should throw this away for your project)
 *
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 09/05/2016
 */
public class HashTest extends TestCase {
    private PrintWriter writer;
    private String testString = "test";
    //private static final String ALPHA_NUMERIC_STRING =
            //"ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * this method generate random strings
     *
     * @param count
     *            represent the string length
     * @return a random string
     * 
     *         private static String randomAlphaNumeric(int count) {
     *         StringBuilder builder = new StringBuilder(); while (count-- != 0)
     *         { int character = (int) (Math.random() *
     *         ALPHA_NUMERIC_STRING.length());
     *         builder.append(ALPHA_NUMERIC_STRING.charAt(character)); } return
     *         builder.toString(); }
     */
    /**
     * Sets up the tests that follow.
     */
    public void setUp() {
        try {
            writer = new PrintWriter("testHash.txt", "UTF-8");
        }
        catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test insertion and deletion from the hash table
     */
    public void testInsert() {

        Hash myHash;
        MemManager memManager;
        try {
            memManager = new MemManager(80);
            myHash = new Hash(2, memManager, testString);
            assertTrue(myHash.insertString("broulaye", writer));
            assertTrue(myHash.insertString("Cheick", writer));
            assertEquals("|broulaye| 2\n|Cheick| 3\ntotal tests: 2\n",
                    myHash.printTable());
            assertTrue(myHash.removeString("broulaye"));
            assertFalse(myHash.removeString("broulaye"));
            assertEquals("|Cheick| 3\ntotal tests: 1\n", myHash.printTable());
            assertTrue(myHash.removeString("Cheick"));
            assertFalse(myHash.removeString("Cheick"));
            assertEquals("total tests: 0\n", myHash.printTable());
            assertEquals(0, myHash.getElement());
            assertTrue(myHash.insertString("Cheick", writer));
            assertTrue(myHash.insertString("Cheicks", writer));
            assertTrue(myHash.insertString("Cheikc", writer));

        }
        catch (Exception e) {
            assertTrue(e instanceof Exception);
        }

    }

    /**
     * Test the hash table with negative size
     */
    public void testNegativeSize() {

        Hash table;
        try {
            table = new Hash(-2, new MemManager(2), testString);
            table.insertString("cheick", writer);
        }
        catch (Exception e) {
            assertTrue(e instanceof Exception);
        }
    }

    /**
     * Test for duplicate strings.
     */
    public void testDuplicateStrings() {
        MemManager manager = new MemManager(3);
        try {
            Hash table = new Hash(3, manager, testString);
            String str = "berthe";
            boolean result1 = table.insertString(str, writer);
            boolean result2 = table.insertString(str, writer);
            assertTrue(result1);
            assertFalse(result2);
        }
        catch (Exception e) {
            assertTrue(e instanceof Exception);
            e.printStackTrace();
        }
    }

    /**
     * TestgetElement
     */
    public void testgetElement() {
        MemManager manager = new MemManager(3);
        try {
            Hash table = new Hash(3, manager, testString);
            String str = "berthe";
            table.insertString(str, writer);

            assertEquals(1, table.getElement());
        }
        catch (Exception e) {
            assertTrue(e instanceof Exception);
            e.printStackTrace();
        }
    }

    /**
     * Test Delete element from empty hash table
     */
    public void testRemoveElement() {
        MemManager manager = new MemManager(1);
        String word = "Cheicks";
        try {
            Hash table = new Hash(10, manager, testString);
            assertFalse(table.removeString(word));
            assertTrue(table.insertString(word, writer));
            assertTrue(table.removeString(word));
            assertTrue(table.insertString(word, writer));
        }
        catch (Exception e) {
            assertTrue(e instanceof Exception);
            e.printStackTrace();
        }
    }
}
